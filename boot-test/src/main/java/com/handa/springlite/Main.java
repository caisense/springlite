package com.handa.springlite;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws Exception {
        // 判定是否从jar/war启动:
        String jarFile = Main.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        boolean isJarFile = jarFile.endsWith(".war") || jarFile.endsWith(".jar");
        // 定位webapp根目录:
        String webDir = isJarFile ? "tmp-webapp" : "src/main/webapp";
        final Path baseDir;
        if (isJarFile) {
            // 解压到tmp-webapp:
            baseDir = Paths.get(webDir).normalize().toAbsolutePath();
            if (Files.isDirectory(baseDir)) {
                Files.delete(baseDir);
            }
            Files.createDirectories(baseDir);
            System.out.println("extract to: " + baseDir);
            try (JarFile jar = new JarFile(jarFile)) {
                List<JarEntry> entries = jar.stream().sorted(Comparator.comparing(JarEntry::getName)).collect(Collectors.toList());
                for (JarEntry entry : entries) {
                    Path res = baseDir.resolve(entry.getName());
                    if (!entry.isDirectory()) {
                        System.out.println(res);
                        Files.createDirectories(res.getParent());
                        Files.copy(jar.getInputStream(entry), res);
                    }
                }
            }
            // Create a URLClassLoader with all extracted JARs
            URLClassLoader classLoader = createClassLoaderWithJars(baseDir);
            Thread.currentThread().setContextClassLoader(classLoader);

            // JVM退出时自动删除tmp-webapp:
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try (var walk = Files.walk(baseDir)) {
                    walk.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
        } else {
            baseDir = null;
        }

        // Use reflection to load and call SpringLiteApplication
        invokeSpringLiteApplication(webDir, isJarFile ? "tmp-webapp" : "target/classes", args);
    }

    private static URLClassLoader createClassLoaderWithJars(Path baseDir) throws Exception {
        Path libDir = baseDir.resolve("WEB-INF/lib");
        List<URL> urls = new ArrayList<>();

        // Add all JARs from WEB-INF/lib
        if (Files.exists(libDir)) {
            try (var stream = Files.list(libDir)) {
                stream.filter(p -> p.toString().endsWith(".jar"))
                      .forEach(jar -> {
                          try {
                              urls.add(jar.toUri().toURL());
                          } catch (Exception e) {
                              throw new RuntimeException(e);
                          }
                      });
            }
        }

        // Also add classes directory
        urls.add(baseDir.toUri().toURL());

        return new URLClassLoader(urls.toArray(new URL[0]), ClassLoader.getSystemClassLoader());
    }

    private static void invokeSpringLiteApplication(String webDir, String classPath, String[] args) throws Exception {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        Class<?> springLiteApp = cl.loadClass("com.handa.springlite.boot.SpringLiteApplication");
        Class<?> helloConfig = cl.loadClass("com.handa.springlite.HelloConfiguration");

        Method runMethod = springLiteApp.getMethod("run", String.class, String.class, Class.class, String[].class);
        runMethod.invoke(null, webDir, classPath, helloConfig, args);
    }
}
