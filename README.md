# 🚀 SpringLite Framework

一个轻量级、高性能的 Java Web 框架，提供 IoC、AOP、MVC、JDBC 等企业级功能。

**当前版本**：1.0.0 | **Java 版本**：25 | **打包状态**：✅ 完成

---

## 📦 框架特性

```
✅ IoC 容器         - Bean 管理和依赖注入
✅ AOP 框架        - 基于 ByteBuddy 的动态代理
✅ Web MVC         - RESTful 路由和控制器支持
✅ 数据库框架      - HikariCP 连接池和事务管理
✅ 嵌入式 Tomcat   - 开箱即用的 Web 服务器
✅ YAML 配置       - 灵活的应用配置管理
✅ 日志系统        - Logback + SLF4J 集成
```

---

## ⚡ 快速开始

### 1️⃣ 在 pom.xml 中添加依赖

详见boot-text/pom.xml

### 2️⃣ 创建启动类、配置类

详见com.handa.hello下Main.java 和 HelloConfiguration.java

### 3️⃣ 创建控制器、服务类

详见com.handa.hello.web和com.handa.hello.service

### 4️⃣ 创建配置文件、静态资源

详见src/main/resource 和 src/main/webapp

### 5️⃣ 运行应用

```bash
mvn clean package
java -jar target/app.jar
```

访问：`http://localhost:8080/hello`

---

## 📚 文档导航

| 文档 | 用途 | 读者 |
|------|------|------|
| **README_INDEX.md** | 文档导航中心 | 所有人（必读） |
| **QUICK_REFERENCE.md** | 快速参考手册 | 开发者 |
| **USAGE_GUIDE.md** | 详细使用指南 | 学生/新手 |
| **DEPLOYMENT_GUIDE.md** | 发布部署指南 | 架构师/运维 |
| **MAVEN_CONFIG_GUIDE.md** | Maven 配置 | Maven 用户 |
| **COMPLETION_CHECKLIST.md** | 完成状态 | 项目管理 |
| **FRAMEWORK_SUMMARY.md** | 项目总结 | 快速了解 |

## 🎯 根据你的角色选择

### 👨‍💻 开发者
1. 阅读 `README_INDEX.md` (2 分钟)
2. 阅读 `QUICK_REFERENCE.md` (10 分钟)
3. 创建示例项目并运行 (5 分钟)
4. 开始开发 ✅

### 🎓 学生/新手  
1. 阅读 `QUICK_REFERENCE.md` (10 分钟)
2. 阅读 `USAGE_GUIDE.md` (15 分钟)
3. 按照示例创建项目 (20 分钟)
4. 查看 API 文档

### 🏢 架构师
1. 阅读 `FRAMEWORK_SUMMARY.md` (5 分钟)
2. 阅读 `DEPLOYMENT_GUIDE.md` (20 分钟)
3. 选择合适的发布方案
4. 执行部署 ✅

### 🔧 运维
1. 阅读 `MAVEN_CONFIG_GUIDE.md` (15 分钟)
2. 配置本地/远程仓库
3. 设置依赖镜像
4. 建立发布流程

---

## 📋 模块结构

```
springlite-boot (推荐使用)
│
├─ springlite-web
│  └─ springlite-context
│
└─ springlite-jdbc
   └─ springlite-aop
      └─ springlite-context
```




---

## 🔧 系统要求

| 项 | 要求 |
|----|------|
| **Java** | 25+ |
| **Maven** | 3.8.1+ |
| **IDE** | IntelliJ IDEA / Eclipse / VSCode |
| **OS** | Windows / Linux / macOS |

---

## 📦 可用的 JAR 包

所有 JAR 包已安装到本地 Maven 仓库：

```
~/.m2/repository/com/handa/springlite/
├── springlite-context-1.0.0.jar
├── springlite-aop-1.0.0.jar
├── springlite-jdbc-1.0.0.jar
├── springlite-web-1.0.0.jar
└── springlite-boot-1.0.0.jar
```

每个 JAR 还包含：
- `-sources.jar` - 源代码
- `-javadoc.jar` - API 文档

---

## 🎓 示例代码

### 创建一个简单的 REST API

```java
@Controller
@RequestMapping("/api")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/users")
    @ResponseBody
    public List<User> getUsers() {
        return userService.findAll();
    }
    
    @PostMapping("/users")
    @ResponseBody
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }
}
```

### 使用 AOP 添加日志

```java
@Aspect
@Component
public class LoggingAspect {
    
    @Before("execution(* com.example..*.*(..))")
    public void logBefore(JoinPoint jp) {
        System.out.println("执行方法: " + jp.getSignature());
    }
}
```

### 数据库操作

```java
@Service
public class UserService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }
}
```

---

## 🚀 常用命令

```bash
# 编译 Framework（仅在修改框架代码后）
cd D:\Projects\SpringLite\framework\springlite-build
mvn clean install -DskipTests

# 在项目中编译和打包
mvn clean package

# 启动应用
java -jar target/app.jar

# 查看依赖树
mvn dependency:tree

# 重新下载依赖（如果有问题）
mvn clean compile -U
```

---

## 🌍 发布框架（可选）

### 本地使用
自动安装到 `~/.m2/repository`，无需任何配置

### 企业内部使用
1. 部署 Nexus 仓库服务器
2. 按照 `DEPLOYMENT_GUIDE.md` 配置
3. 执行 `mvn clean deploy`
4. 团队成员配置仓库地址

### 开源社区
1. 申请 Sonatype 账户
2. 配置 GPG 签名
3. 发布到 Maven Central
4. 全球开发者可使用

详见 `DEPLOYMENT_GUIDE.md`

---

## 💡 常见问题

### Q: 如何快速上手？
**A**: 阅读 `QUICK_REFERENCE.md` 的"快速开始"部分（5 分钟）

### Q: JAR 文件在哪里？
**A**: 本地仓库 `~/.m2/repository/com/handa/springlite/`

### Q: 如何修改 Framework？
**A**: 修改代码后重新执行 `mvn clean install`，其他项目会自动使用最新版本

### Q: 支持什么数据库？
**A**: 任何 JDBC 支持的数据库（MySQL、PostgreSQL、Oracle、SQLite 等）

### Q: 能和 Spring Framework 混用吗？
**A**: 可以，但不推荐。SpringLite 是独立的轻量级框架

更多问题见 `QUICK_REFERENCE.md` 或 `MAVEN_CONFIG_GUIDE.md`

---

## 📈 项目统计

```
模块数量：      6 个
代码行数：      6000 行
文档数量：      7 份
```

---

## 🎯 下一步

- [ ] 阅读 `README_INDEX.md` 了解全貌
- [ ] 按照 `QUICK_REFERENCE.md` 快速上手
- [ ] 创建测试项目验证框架功能
- [ ] 在实际项目中集成使用
- [ ] 根据需要发布到远程仓库

---

## ©️版权声明

- **参考**：https://github.com/michaelliao/summer-framework
- **License**：GNU General Public License v3.0

---

## 🎉 特别致谢

廖雪峰大佬

**祝你使用愉快！** 🚀

---

**最后更新**：2026-04-23  
**框架版本**：1.0.0  
**文档版本**：1.0

---

## 📚 完整文档列表

📖 **入门必读**：
- `README_INDEX.md` - 文档导航中心

📖 **快速上手**：
- `QUICK_REFERENCE.md` - 快速参考手册（推荐5分钟内阅读）

📖 **深度学习**：
- `USAGE_GUIDE.md` - 详细使用指南
- `FRAMEWORK_SUMMARY.md` - 项目总结报告

📖 **进阶内容**：
- `DEPLOYMENT_GUIDE.md` - 发布和部署指南
- `MAVEN_CONFIG_GUIDE.md` - Maven 仓库配置指南
- `COMPLETION_CHECKLIST.md` - 项目完成状态清单

💾 **项目模板**：
- `springlite-demo-template.pom.xml` - Maven POM 模板

---

**开始你的 SpringLite 之旅吧！** ✨

