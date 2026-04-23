# SpringLite Framework 使用指南

## 📦 框架已打包完成

所有 SpringLite 框架模块已经编译并安装到本地 Maven 仓库，可以在其他 Java 项目中使用。

## 🏗️ 框架模块结构

```
SpringLite Framework v1.0.0
├── springlite-context     (核心上下文)
│   └── 依赖: 无
├── springlite-aop         (AOP 面向切面)
│   └── 依赖: springlite-context
├── springlite-jdbc        (JDBC 数据库)
│   └── 依赖: springlite-aop
├── springlite-web         (Web MVC)
│   └── 依赖: springlite-context
└── springlite-boot        (启动程序 - All-in-One)
    └── 依赖: springlite-web + springlite-jdbc
```

## 🚀 在其他项目中使用

### 方案 1: 使用完整框架（推荐新项目）

如果要创建新项目并使用完整的 SpringLite 框架功能（Web + JDBC + AOP）：

```xml
<dependency>
    <groupId>com.handa.springlite</groupId>
    <artifactId>springlite-boot</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 方案 2: 选择性使用模块

如果只需要特定功能，可以单独引入需要的模块：

#### 仅使用核心容器（最小化）
```xml
<dependency>
    <groupId>com.handa.springlite</groupId>
    <artifactId>springlite-context</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### 使用 Web 功能
```xml
<dependency>
    <groupId>com.handa.springlite</groupId>
    <artifactId>springlite-web</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### 使用数据库功能
```xml
<dependency>
    <groupId>com.handa.springlite</groupId>
    <artifactId>springlite-jdbc</artifactId>
    <version>1.0.0</version>
</dependency>
<!-- 会自动依赖 springlite-aop 和 springlite-context -->
```

#### 使用 AOP 功能
```xml
<dependency>
    <groupId>com.handa.springlite</groupId>
    <artifactId>springlite-aop</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 📋 完整示例项目结构

创建一个新项目并使用 SpringLite Framework：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>my-springlite-app</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <name>My SpringLite Application</name>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>25</maven.compiler.source>
        <maven.compiler.target>25</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- SpringLite 核心框架 -->
        <dependency>
            <groupId>com.handa.springlite</groupId>
            <artifactId>springlite-boot</artifactId>
            <version>1.0.0</version>
        </dependency>

        <!-- 测试依赖 -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.9.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>25</source>
                    <target>25</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

## 🎯 快速开始示例

### 1. 创建启动类

```java
import com.handa.springlite.boot.SpringLiteApplication;

public class MyApp {
    public static void main(String[] args) {
        SpringLiteApplication.run(MyApp.class, args);
    }
}
```

### 2. 创建 Controller

```java
import com.handa.springlite.web.annotation.Controller;
import com.handa.springlite.web.annotation.RequestMapping;
import com.handa.springlite.web.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class HelloController {
    
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello from SpringLite!";
    }
}
```

### 3. 配置应用（application.yml）

```yaml
springlite:
  application:
    name: my-springlite-app
  server:
    port: 8080
    servlet:
      context-path: /
  datasource:
    url: jdbc:sqlite:test.db
    driver-class-name: org.sqlite.JDBC
```

## 📦 本地仓库位置

所有编译好的 JAR 文件存储在：
```
D:\Env\Java\apache-maven-3.9.3\repository\com\handa\springlite\
```

### 可用的 JAR 文件：
- `springlite-context-1.0.0.jar` (核心库)
- `springlite-aop-1.0.0.jar` (AOP 库)
- `springlite-jdbc-1.0.0.jar` (数据库库)
- `springlite-web-1.0.0.jar` (Web 库)
- `springlite-boot-1.0.0.jar` (完整框架)

## 🔄 更新框架

当框架代码更新时，重新编译并安装到本地仓库：

```powershell
cd D:\Projects\SpringLite\framework\springlite-build
mvn clean install -DskipTests
```

## 📤 发布到远程仓库（可选）

如果要发布到 Maven Central 或私有仓库，修改 `springlite-parent/pom.xml` 中的 `distributionManagement` 节点，然后执行：

```powershell
cd D:\Projects\SpringLite\framework\springlite-build
mvn clean deploy -DskipTests
```

**注意**：需要配置 GPG 密钥和 Maven 仓库凭证。

## 🏢 发布到本地 Nexus 仓库

如果在公司内部使用，可以搭建 Nexus 仓库服务器，然后修改 `distributionManagement`：

```xml
<distributionManagement>
    <snapshotRepository>
        <id>nexus-snapshots</id>
        <url>http://nexus.company.com/repository/snapshots</url>
    </snapshotRepository>
    <repository>
        <id>nexus-releases</id>
        <url>http://nexus.company.com/repository/releases</url>
    </repository>
</distributionManagement>
```

然后在 `~/.m2/settings.xml` 中配置凭证：

```xml
<servers>
    <server>
        <id>nexus-snapshots</id>
        <username>your-username</username>
        <password>your-password</password>
    </server>
    <server>
        <id>nexus-releases</id>
        <username>your-username</username>
        <password>your-password</password>
    </server>
</servers>
```

然后发布：

```powershell
mvn clean deploy -DskipTests
```

## 📚 框架主要特性

根据依赖关系，不同的模块提供以下功能：

### springlite-context
- Bean 容器和依赖注入
- YAML 配置加载
- 生命周期管理

### springlite-aop
- 动态代理（基于 ByteBuddy）
- 切面编程支持

### springlite-jdbc
- 数据源管理（HikariCP）
- ORM 框架
- 事务管理

### springlite-web
- 请求路由和分发
- 控制器注解支持
- JSON 序列化（Jackson）

### springlite-boot
- 嵌入式 Tomcat 服务器
- 一键启动应用
- 自动化配置

## ✅ 验证安装

创建测试项目，运行以下 Maven 命令验证依赖是否正确解析：

```powershell
mvn dependency:tree
```

如果能看到所有 SpringLite 依赖都已正确解析，说明安装成功！

## 🤝 问题排查

### JAR 文件找不到？
确保本地仓库位置正确，检查 `~/.m2/settings.xml` 中的 `localRepository` 配置。

### 版本不匹配？
确保所有依赖都使用相同的版本 `1.0.0`。

### 编译错误？
确保 Java 版本为 25 或更新版本，并且 Maven 版本为 3.8.1+。

---

**最后更新**：2026-04-23  
**框架版本**：1.0.0  
**维护者**：Caisense (caisense0823@gmail.com)

