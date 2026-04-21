package com.handa.springlite.boot;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.nio.file.Paths;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.catalina.Context;
import org.apache.catalina.Server;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.handa.springlite.io.PropertyResolver;
import com.handa.springlite.utils.ClassPathUtils;
import com.handa.springlite.web.ContextLoaderInitializer;
import com.handa.springlite.web.utils.WebUtils;

public class SpringLiteApplication {

    static final String CONFIG_APP_YAML = "/application.yml";
    static final String CONFIG_APP_PROP = "/application.properties";

    final Logger logger = LoggerFactory.getLogger(SpringLiteApplication.class);

    public static void run(String webDir, String baseDir, Class<?> configClass, String... args) throws Exception {
        new SpringLiteApplication().start(webDir, baseDir, configClass, args);
    }

    public void start(String webDir, String baseDir, Class<?> configClass, String... args) throws Exception {
        printBanner();

        // start info:
        final long startTime = System.currentTimeMillis();
        final int javaVersion = Runtime.version().feature();
        final long pid = ManagementFactory.getRuntimeMXBean().getPid();
        final String user = System.getProperty("user.name");
        final String pwd = Paths.get("").toAbsolutePath().toString();
        logger.info("Starting {} using Java {} with PID {} (started by {} in {})", configClass.getSimpleName(), javaVersion, pid, user, pwd);

        // 读取application.yml配置:
        var propertyResolver = WebUtils.createPropertyResolver();
        // 创建Tomcat服务器:
        var server = startTomcat(webDir, baseDir, configClass, propertyResolver);

        // started info:
        final long endTime = System.currentTimeMillis();
        final String appTime = String.format("%.3f", (endTime - startTime) / 1000.0);
        final String jvmTime = String.format("%.3f", ManagementFactory.getRuntimeMXBean().getUptime() / 1000.0);
        logger.info("Started {} in {} seconds (process running for {})", configClass.getSimpleName(), appTime, jvmTime);
        // 等待服务器结束:
        server.await();
    }

    /**
     * 启动嵌入式Tomcat
     * @param webDir
     * @param baseDir
     * @param configClass
     * @param propertyResolver
     * @return
     * @throws Exception
     */
    protected Server startTomcat(String webDir, String baseDir, Class<?> configClass, PropertyResolver propertyResolver) throws Exception {
        int port = propertyResolver.getProperty("${server.port:8080}", int.class);
        logger.info("starting Tomcat at port {}...", port);
        // 实例化Tomcat Server:
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        // 设置Connector:
        tomcat.getConnector().setThrowOnFailure(true);
        // 添加一个默认的Webapp，挂载在'/':
        Context ctx = tomcat.addWebapp("", new File(webDir).getAbsolutePath());
        // 设置应用程序的目录:
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", new File(baseDir).getAbsolutePath(), "/"));
        ctx.setResources(resources);
        // 设置ServletContainerInitializer监听器:
        ctx.addServletContainerInitializer(new ContextLoaderInitializer(configClass, propertyResolver), Set.of());
        // 启动服务器:
        tomcat.start();
        logger.info("Tomcat started at port {}...", port);
        return tomcat.getServer();
    }

    protected void printBanner() {
        String banner = ClassPathUtils.readString("/banner.txt");
        // 解析颜色标记
        banner = AnsiColor.resolve(banner);
        banner.lines().forEach(System.out::println);
    }

    /**
     * ANSI 颜色工具类
     */
    static class AnsiColor {
        public static final String RESET = "\033[0m";
        public static final String BLACK = "\033[30m";
        public static final String RED = "\033[31m";
        public static final String GREEN = "\033[32m";
        public static final String YELLOW = "\033[33m";
        public static final String BLUE = "\033[34m";
        public static final String MAGENTA = "\033[35m";
        public static final String CYAN = "\033[36m";
        public static final String WHITE = "\033[37m";
        public static final String BRIGHT_BLACK = "\033[90m";
        public static final String BRIGHT_RED = "\033[91m";
        public static final String BRIGHT_GREEN = "\033[92m";
        public static final String BRIGHT_YELLOW = "\033[93m";
        public static final String BRIGHT_BLUE = "\033[94m";
        public static final String BRIGHT_MAGENTA = "\033[95m";
        public static final String BRIGHT_CYAN = "\033[96m";
        public static final String BRIGHT_WHITE = "\033[97m";

        private static final java.util.regex.Pattern COLOR_PATTERN = java.util.regex.Pattern.compile("\\$\\{AnsiColor\\.([A-Z_]+)\\}");

        /**
         * 解析字符串中的颜色标记
         */
        public static String resolve(String text) {
            Matcher matcher = COLOR_PATTERN.matcher(text);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                String colorName = matcher.group(1);
                String colorCode = getColorCode(colorName);
                matcher.appendReplacement(sb, colorCode);
            }
            matcher.appendTail(sb);
            return sb.toString();
        }

        /**
         * 根据颜色名称获取颜色代码
         */
        private static String getColorCode(String colorName) {
            try {
                return (String) AnsiColor.class.getField(colorName).get(null);
            } catch (Exception e) {
                return "";
            }
        }
    }
}