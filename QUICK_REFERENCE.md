# SpringLite Framework 快速参考

## 📦 框架概述

| 项目 | 版本 | 说明 |
|------|------|------|
| springlite-parent | 1.0.0 | 父 POM，定义所有依赖版本 |
| springlite-context | 1.0.0 | 核心容器，Bean 管理和配置 |
| springlite-aop | 1.0.0 | AOP 框架，基于 ByteBuddy |
| springlite-jdbc | 1.0.0 | 数据库框架，HikariCP 连接池 |
| springlite-web | 1.0.0 | Web MVC 框架，Servlet/Tomcat |
| springlite-boot | 1.0.0 | 启动器，整合所有功能 |

## 🚀 快速开始（5 分钟）

### 1️⃣ 创建新项目

```bash
mkdir my-springlite-app
cd my-springlite-app
```

### 2️⃣ 创建 pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0.0</version>
    <properties>
        <maven.compiler.source>25</maven.compiler.source>
        <maven.compiler.target>25</maven.compiler.target>
    </properties>
    <dependencies>
        <dependency>
            <groupId>com.handa.springlite</groupId>
            <artifactId>springlite-boot</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>
</project>
```

### 3️⃣ 创建启动类 `src/main/java/com/example/Application.java`

```java
import com.handa.springlite.boot.SpringLiteApplication;

public class Application {
    public static void main(String[] args) {
        SpringLiteApplication.run(Application.class, args);
    }
}
```

### 4️⃣ 创建控制器 `src/main/java/com/example/HelloController.java`

```java
import com.handa.springlite.web.annotation.Controller;
import com.handa.springlite.web.annotation.RequestMapping;
import com.handa.springlite.web.annotation.ResponseBody;

@Controller
public class HelloController {
    
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello from SpringLite!";
    }
}
```

### 5️⃣ 创建配置文件 `src/main/resources/application.yml`

```yaml
springlite:
  application:
    name: my-app
  server:
    port: 8080
```

### 6️⃣ 编译并运行

```bash
mvn clean package
java -jar target/my-app-1.0.0.jar
```

访问：http://localhost:8080/hello

## 📚 常用依赖引入方式

### 只用数据库功能

```xml
<dependency>
    <groupId>com.handa.springlite</groupId>
    <artifactId>springlite-jdbc</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 只用 Web 框架

```xml
<dependency>
    <groupId>com.handa.springlite</groupId>
    <artifactId>springlite-web</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 只用核心容器

```xml
<dependency>
    <groupId>com.handa.springlite</groupId>
    <artifactId>springlite-context</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 要用 AOP

```xml
<dependency>
    <groupId>com.handa.springlite</groupId>
    <artifactId>springlite-aop</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 🔧 开发指令

### 在 Framework 目录操作

```bash
# 编译所有模块
cd D:\Projects\SpringLite\framework\springlite-build
mvn clean install -DskipTests

# 编译特定模块
mvn clean install -pl springlite-context -DskipTests

# 运行测试
mvn clean test

# 生成 Javadoc
mvn javadoc:javadoc

# 查看依赖树
mvn dependency:tree
```

## 📋 配置文件示例

### application.yml - Web 应用

```yaml
springlite:
  application:
    name: my-web-app
    
  server:
    port: 8080
    servlet:
      context-path: /api
      
  mvc:
    view-prefix: /WEB-INF/views/
    view-suffix: .jsp
    
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: root
    password: 123456
    driver-class-name: com.mysql.cj.jdbc.Driver
    pool-size: 10
```

### application.yml - 仅数据库应用

```yaml
springlite:
  application:
    name: my-batch-app
    
  datasource:
    url: jdbc:sqlite:data.db
    driver-class-name: org.sqlite.JDBC
```

## 🎯 常见注解

### 类注解

```java
@Controller              // Web 控制器
@Service                // 业务服务
@Repository             // 数据仓库
@Component              // 通用组件
@Configuration          // 配置类
```

### 方法/字段注解

```java
@Bean                   // Bean 定义
@Autowired              // 依赖注入
@RequestMapping         // 请求映射
@PostMapping            // POST 请求
@GetMapping             // GET 请求
@RequestParam           // 请求参数
@PathVariable           // 路径变量
@RequestBody            // 请求体
@ResponseBody           // 响应体
@Transactional          // 事务管理
@Aspect                 // AOP 切面
```

## 📊 依赖关系图

```
springlite-boot
├── springlite-web
│   └── springlite-context
├── springlite-jdbc
│   └── springlite-aop
│       └── springlite-context
└── (隐含依赖)
    ├── jackson-databind        (JSON 序列化)
    ├── logback-classic         (日志)
    ├── snakeyaml              (YAML 配置)
    ├── byte-buddy             (动态代理)
    ├── HikariCP               (连接池)
    ├── tomcat-embed-*         (Web 服务器)
    └── jakarta.servlet-api    (Servlet API)
```

## 🛠️ 调试技巧

### 启用调试日志

在 `application.yml` 中添加：

```yaml
logging:
  level:
    com.handa.springlite: DEBUG
    root: INFO
```

### 查看 Bean 信息

```java
ApplicationContext context = SpringLiteApplication.run(...);
String[] beanNames = context.getBeanDefinitionNames();
for (String name : beanNames) {
    System.out.println("Bean: " + name);
}
```

### 检查依赖

```bash
mvn dependency:tree > dependencies.txt
```

## 🐛 常见问题排查

| 问题 | 解决方案 |
|------|--------|
| 依赖找不到 | 检查本地仓库，重新编译 Framework |
| 版本冲突 | 检查 pom.xml 中的版本号，确保一致 |
| 编译失败 | 检查 Java 版本（需要 25+），Maven 版本（3.8.1+） |
| 运行时错误 | 检查配置文件，查看日志输出 |
| 端口占用 | 修改 application.yml 中的 server.port |

## 📈 性能优化建议

1. **连接池**：调整 HikariCP 参数
   ```yaml
   datasource:
     pool-size: 20
     max-lifetime: 1800000
   ```

2. **日志等级**：生产环境使用 WARN 以上
   ```yaml
   logging:
     level:
       root: WARN
   ```

3. **缓存**：对频繁访问的数据进行缓存

4. **异步处理**：使用线程池处理耗时操作

## 📞 获取帮助

- **文档**：查看 `USAGE_GUIDE.md`
- **发布**：查看 `DEPLOYMENT_GUIDE.md`
- **问题**：检查日志，Google 搜索相关错误
- **联系**：caisense0823@gmail.com

## 版本更新记录

- **v1.0.0** (2026-04-23)
  - ✅ 初始版本发布
  - ✅ 支持 Web、JDBC、AOP 核心功能
  - ✅ 基于 Java 25
  - ✅ 嵌入式 Tomcat 10.1.47

---

**更新时间**：2026-04-23  
**作者**：Caisense  
**License**：GPLv3

