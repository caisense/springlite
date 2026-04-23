# 📚 SpringLite Framework 文档索引

欢迎来到 SpringLite Framework！这是一份完整的文档导航指南。

## 🎯 根据场景选择文档

### 🚀 我想快速开始使用 Framework

**推荐文档**：`QUICK_REFERENCE.md`

- 5 分钟快速开始指南
- 常用注解和配置示例
- 常见问题排查表
- 依赖引入方式

**预计时间**：5-10 分钟

---

### 📖 我想详细了解 Framework 的所有功能

**推荐文档**：`USAGE_GUIDE.md`

- 完整的框架模块说明
- 4 种使用方案详解
- 完整的 POM 配置示例
- Controller、Service 示例代码
- 本地仓库位置和文件列表

**预计时间**：15-20 分钟

---

### 📤 我想在公司/开源社区发布 Framework

**推荐文档**：`DEPLOYMENT_GUIDE.md`

- 4 种发布方案详解：
  1. 本地仓库（单机开发）
  2. 文件系统共享（小团队）
  3. Nexus 仓库（企业）
  4. Maven Central（开源）
- 每种方案的完整配置步骤
- 版本管理策略
- 日常开发流程

**预计时间**：20-30 分钟（根据选择的方案）

---

### 🔧 我需要配置 Maven 仓库

**推荐文档**：`MAVEN_CONFIG_GUIDE.md`

- Maven 本地仓库配置
- settings.xml 完整示例
- 常用 Maven 命令速查
- 镜像加速配置（国内用户���
- 离线开发模式
- JAR 文件目录结构

**预计时间**：10-15 分钟

---

### 📋 我想检查项目完成状态

**推荐文档**：`COMPLETION_CHECKLIST.md`

- 已完成的工作清单
- JAR 文件位置和大小
- 下一步建议
- 关键特性总结
- 技术统计

**预计时间**：5 分钟

---

### 💡 我想快速复制 POM 配置

**推荐文件**：`springlite-demo-template.pom.xml`

- 可直接复制的 Maven POM 文件
- 包含完整的依赖和插件配置
- 适合创建新项目

**操作**：直接复制内容到新项目的 `pom.xml`

---

## 📑 文档完整列表

| # | 文档名 | 大小 | 用途 | 难度 |
|----|--------|------|------|------|
| 1 | **QUICK_REFERENCE.md** | 8KB | 快速参考和入门 | ⭐ 简单 |
| 2 | **USAGE_GUIDE.md** | 12KB | 详细使用指南 | ⭐⭐ 中等 |
| 3 | **DEPLOYMENT_GUIDE.md** | 18KB | 发布和部署 | ⭐⭐⭐ 复杂 |
| 4 | **MAVEN_CONFIG_GUIDE.md** | 10KB | Maven 配置 | ⭐⭐ 中等 |
| 5 | **COMPLETION_CHECKLIST.md** | 8KB | 完成状态检查 | ⭐ 简单 |
| 6 | **springlite-demo-template.pom.xml** | 2KB | POM 模板 | ⭐ 简单 |
| 7 | **README_INDEX.md** | 本文件 | 文档导航 | ⭐ 简单 |

---

## 🎓 学习路径建议

### 新手（第一次接触 SpringLite）

```
1. 快速参考 (QUICK_REFERENCE.md)
   ↓
2. 完整使用指南 (USAGE_GUIDE.md)
   ↓
3. 创建示例项目并运行
   ↓
4. 查看 API 文档
```

**预计时间**：1-2 小时

---

### 开发者（需要在项目中使用）

```
1. 快速参考 (QUICK_REFERENCE.md) - 快速上手
   ↓
2. Maven 配置指南 (MAVEN_CONFIG_GUIDE.md) - 处理依赖问题
   ↓
3. 使用指南 (USAGE_GUIDE.md) - 深��了解功能
   ↓
4. 开始开发
```

**预计时间**：30-45 分钟

---

### 架构师（需要发布和维护）

```
1. 完成状态清单 (COMPLETION_CHECKLIST.md) - 了解现状
   ↓
2. 部署指南 (DEPLOYMENT_GUIDE.md) - 选择发布方案
   ↓
3. Maven 配置指南 (MAVEN_CONFIG_GUIDE.md) - 配置仓库
   ↓
4. 执行发布和维护
```

**预计时间**：1-2 小时

---

## 🔑 关键概念速查

### Framework 模块结构

```
springlite-boot ⭐ 推荐使用（完整功能）
├── springlite-web（Web MVC）
├── springlite-jdbc（数据库）
│   └── springlite-aop（AOP）
│       └── springlite-context（核心）
```

### 快速引入依赖

```xml
<!-- 完整框架 -->
<dependency>
    <groupId>com.handa.springlite</groupId>
    <artifactId>springlite-boot</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 本地仓库位置

```
Windows: C:\Users\YourUsername\.m2\repository
Linux:   ~/.m2/repository
Mac:     ~/.m2/repository
```

### 常用命令

```bash
# 编译 Framework
mvn clean install -DskipTests

# 查看依赖树
mvn dependency:tree

# 在其他项目使用
# 直接在 pom.xml 中添加依赖，Maven 自动从本地仓库查找
```

---

## ❓ 常见问题速查

### Q1: 如何快速集成 SpringLite？
**A**: 参考 `QUICK_REFERENCE.md` 的"快速开始"部分（5 分钟）

### Q2: JAR 文件在哪里？
**A**: 参考 `MAVEN_CONFIG_GUIDE.md` 的"本地仓库位置"部分

### Q3: 如何发布到公司仓库？
**A**: 参考 `DEPLOYMENT_GUIDE.md` 的"方案 3：Nexus"部分

### Q4: 如何发布开源？
**A**: 参考 `DEPLOYMENT_GUIDE.md` 的"方案 4：Maven Central"部分

### Q5: 项目现在的状态是什么？
**A**: 参考 `COMPLETION_CHECKLIST.md` 的"完成清单"部分

### Q6: Maven 配置有问题？
**A**: 参考 `MAVEN_CONFIG_GUIDE.md` 的"常见问题"部分

### Q7: 想要创建新项目？
**A**: 使用 `springlite-demo-template.pom.xml` 模板，参考 `QUICK_REFERENCE.md`

---

## 🛠️ 快速操作参考

### 场景 1：修改 Framework 代码后其他项目如何使用？

1. 修改 Framework 代码
2. 执行编译：
   ```powershell
   cd D:\Projects\SpringLite\framework\springlite-build
   mvn clean install -DskipTests
   ```
3. 在 IDE 中刷新依赖
4. 其他项目立即使用最新版本

详见：`USAGE_GUIDE.md` → "快速开始示例"

---

### 场景 2：在新项目中使用 SpringLite

1. 创建 Maven 项目
2. 复制 `springlite-demo-template.pom.xml` 中的依赖部分
3. 执行 `mvn clean compile` 下载依赖
4. 参考 `QUICK_REFERENCE.md` 创建代码

详见：`QUICK_REFERENCE.md` → "快速开始"

---

### 场景 3：发布 Framework 到企业仓库

1. 安装和配置 Nexus 仓库服务器
2. 修改 `springlite-parent/pom.xml` 中的 `distributionManagement`
3. 在 `~/.m2/settings.xml` 中配置服务器凭证
4. 执行发布：
   ```powershell
   mvn clean deploy -DskipTests
   ```

详见：`DEPLOYMENT_GUIDE.md` → "方案 3：Nexus Repository Manager"

---

### 场景 4：解决 Maven 依赖问题

1. 检查本地仓库配置
2. 查看 `settings.xml` 中的 `<repositories>` 配置
3. 尝试重新下载依赖：
   ```bash
   mvn clean compile -U
   ```
4. 查看镜像配置（如果在中国）

详见：`MAVEN_CONFIG_GUIDE.md` → "常见问题"

---

## 📈 文档更新历史

| 版本 | 日期 | 更新内容 |
|------|------|--------|
| v1.0 | 2026-04-23 | 初始版本，Framework 首次打包完成 |

---

## 🎯 版本信息

- **Framework 版本**：1.0.0
- **Java 版本**：25
- **Maven 版本**：3.9.3+
- **文档版本**：1.0
- **生成时间**：2026-04-23

---

## 📞 技术支持

- **维护者**：Caisense
- **邮箱**：caisense0823@gmail.com
- **开源协议**：GNU General Public License v3.0
- **项目主页**：https://caisense.github.io/CS-Note/

---

## 💡 提示

💡 **新手建议**：
1. 先读 `QUICK_REFERENCE.md`
2. 然后看 `USAGE_GUIDE.md`
3. 最后实践创建示例项目

💡 **开发建议**：
1. 将本文件放在项目根目录便于访问
2. 定期参考文档，特别是常见问题部分
3. 遇到问题时，先检查 "常见问题速查" 部分

💡 **维护建议**：
1. 每次发布时更新版本号
2. 记录在 DEPLOYMENT_GUIDE.md 中
3. 保持文档与实际代码同步

---

## 🎉 开始使用

选择你的角色，开始阅读对应的文档：

- 👨‍💻 **开发者** → `QUICK_REFERENCE.md` + `USAGE_GUIDE.md`
- 🏢 **架构师** → `DEPLOYMENT_GUIDE.md` + `MAVEN_CONFIG_GUIDE.md`
- 🎓 **学生** → `QUICK_REFERENCE.md` → `USAGE_GUIDE.md`
- 🔧 **运维** → `MAVEN_CONFIG_GUIDE.md` + `DEPLOYMENT_GUIDE.md`

祝您使用愉快！🚀

---

**最后更新**：2026-04-23  
**文档版本**：1.0  
**维护者**：Caisense

