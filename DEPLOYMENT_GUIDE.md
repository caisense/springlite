# SpringLite Framework 发布指南

## 概述

本文档说明如何将 SpringLite Framework 发布到不同的 Maven 仓库，使其他开发者和项目能够方便地使用。

## 当前状态

✅ **本地仓库**：所有模块已编译并安装到本地 Maven 仓库  
📍 **位置**：`D:\Env\Java\apache-maven-3.9.3\repository\com\handa\springlite\`

## 发布方案对比

| 方案 | 适用场景 | 难度 | 费用 |
|------|--------|------|------|
| **本地仓库** | 单机开发、内网使用 | ⭐ 简单 | 免费 |
| **文件系统共享** | 小团队内部共享 | ⭐⭐ 中等 | 免费 |
| **私有 Nexus** | 企业内部仓库 | ⭐⭐⭐ 复杂 | 自建成本 |
| **Maven Central** | 开源公开发布 | ⭐⭐⭐⭐ 很复杂 | 免费 |

---

## 方案 1️⃣：使用本地仓库（推荐用于本机开发）

### 特点
- ✅ 无需任何配置
- ✅ 即插即用
- ❌ 仅限本机使用
- ❌ 其他开发者无法使用

### 使用方式

只需在其他项目的 `pom.xml` 中添加依赖即可：

```xml
<dependency>
    <groupId>com.handa.springlite</groupId>
    <artifactId>springlite-boot</artifactId>
    <version>1.0.0</version>
</dependency>
```

Maven 会自动从本地仓库 `~/.m2/repository` 中查找依赖。

---

## 方案 2️⃣：文件系统共享（推荐用于小团队）

### 特点
- ✅ 配置简单
- ✅ 无需额外成本
- ✅ 可支持局域网共享
- ❌ 需要共享的文件系统（如 NFS、共享驱动器）

### 步骤 1：配置共享目录

在 Windows 上创建共享文件夹，例如：`\\server\maven-repo`

### 步骤 2：修改 pom.xml

在 `springlite-parent/pom.xml` 中配置 `distributionManagement`：

```xml
<distributionManagement>
    <repository>
        <id>file-repo</id>
        <url>file:///\\server\maven-repo</url>
    </repository>
    <snapshotRepository>
        <id>file-repo-snapshots</id>
        <url>file:///\\server\maven-repo-snapshots</url>
    </snapshotRepository>
</distributionManagement>
```

### 步骤 3：发布框架

```powershell
cd D:\Projects\SpringLite\framework\springlite-build
mvn clean deploy -DskipTests
```

### 步骤 4：其他开发者配置

在 `~/.m2/settings.xml` 中添加：

```xml
<repositories>
    <repository>
        <id>file-repo</id>
        <url>file:///\\server\maven-repo</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
```

---

## 方案 3️⃣：使用 Nexus Repository Manager（推荐用于企业）

### 特点
- ✅ 企业级解决方案
- ✅ 支持权限管理
- ✅ 提供 Web 界面管理
- ✅ 支持代理公开仓库
- ⚠️ 需要部署和维护服务器

### 安装 Nexus

#### 选项 A：Docker 方式（推荐）

```powershell
docker pull sonatype/nexus3
docker run -d `
  --name nexus `
  -p 8081:8081 `
  -v nexus-data:/nexus-data `
  sonatype/nexus3
```

#### 选项 B：本地安装

1. 下载 Nexus 3：https://www.sonatype.com/download-oss-sonatype-nexus-repository-manager
2. 解压到本地目录，例如 `D:\Tools\nexus-3.x.x`
3. 运行 `D:\Tools\nexus-3.x.x\bin\nexus.exe /run`

### 首次使用

1. 访问 `http://localhost:8081`
2. 默认用户名：`admin`，密码查看 `nexus-data\admin.password` 文件
3. 修改密码并完成初始化

### 配置 Nexus（在管理后台）

1. 创建两个仓库：
   - **springlite-releases**：Hosted，Release 类型
   - **springlite-snapshots**：Hosted，Snapshot 类型

2. 创建用户或使用 admin 用户

### 修改 pom.xml

在 `springlite-parent/pom.xml` 中：

```xml
<distributionManagement>
    <repository>
        <id>nexus-releases</id>
        <name>Nexus Release Repository</name>
        <url>http://nexus.company.com:8081/repository/springlite-releases/</url>
    </repository>
    <snapshotRepository>
        <id>nexus-snapshots</id>
        <name>Nexus Snapshot Repository</name>
        <url>http://nexus.company.com:8081/repository/springlite-snapshots/</url>
    </snapshotRepository>
</distributionManagement>
```

### 配置本地 Maven 凭证

编辑 `~/.m2/settings.xml`（Windows：`C:\Users\YourUsername\.m2\settings.xml`）：

```xml
<servers>
    <server>
        <id>nexus-releases</id>
        <username>admin</username>
        <password>your-password</password>
    </server>
    <server>
        <id>nexus-snapshots</id>
        <username>admin</username>
        <password>your-password</password>
    </server>
</servers>
```

### 发布框架

```powershell
cd D:\Projects\SpringLite\framework\springlite-build
mvn clean deploy -DskipTests
```

### 其他开发者配置

在各自的 `~/.m2/settings.xml` 中添加：

```xml
<repositories>
    <repository>
        <id>nexus-releases</id>
        <name>Nexus Release Repository</name>
        <url>http://nexus.company.com:8081/repository/springlite-releases/</url>
    </repository>
    <repository>
        <id>nexus-snapshots</id>
        <name>Nexus Snapshot Repository</name>
        <url>http://nexus.company.com:8081/repository/springlite-snapshots/</url>
    </repository>
</repositories>
```

---

## 方案 4️⃣：发布到 Maven Central（开源项目）

### 要求
- ✅ 开源项目
- ✅ 申请 JIRA 账户
- ✅ 配置 GPG 签名
- ✅ 符合中央仓库要求

### 详细步骤

#### 1. 申请账户

访问 https://issues.sonatype.org，创建账户

#### 2. 创建发布工单

填写项目信息，例如：
- GroupId：`com.handa.springlite`
- ProjectURL：`https://github.com/caisense/springlite-framework`
- SCM URL：`https://github.com/caisense/springlite-framework.git`

#### 3. 配置 GPG

```powershell
# 下载并安装 GnuPG
# https://www.gnupg.org/download/

# 生成密钥
gpg --gen-key

# 上传公钥到服务器
gpg --keyserver hkp://keys.openpgp.org --send-keys YOUR-KEY-ID

# 查看密钥列表
gpg --list-keys
```

#### 4. 配置 Maven

编辑 `~/.m2/settings.xml`：

```xml
<servers>
    <server>
        <id>ossrh</id>
        <username>your-jira-username</username>
        <password>your-jira-password</password>
    </server>
</servers>
```

#### 5. 修改 pom.xml

确保配置了 `distributionManagement`（当前已配置）：

```xml
<distributionManagement>
    <snapshotRepository>
        <id>ossrh</id>
        <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    </snapshotRepository>
    <repository>
        <id>ossrh</id>
        <url>https://oss.sonatype.org/service/local/staging/deploy/maven2/</url>
    </repository>
</distributionManagement>
```

同时修改 `springlite-parent/pom.xml` 中的 GPG 配置：

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-gpg-plugin</artifactId>
    <version>3.0.1</version>
    <executions>
        <execution>
            <id>sign-artifacts</id>
            <phase>verify</phase>
            <goals>
                <goal>sign</goal>
            </goals>
            <configuration>
                <keyname>YOUR-KEY-ID</keyname>
                <passphraseServerId>gpg.passphrase</passphraseServerId>
            </configuration>
        </execution>
    </executions>
</plugin>
```

#### 6. 发布

```powershell
cd D:\Projects\SpringLite\framework\springlite-build
mvn clean deploy -DskipTests -Dgpg.passphrase=your-gpg-passphrase
```

#### 7. 在 Sonatype 关闭并发布

```powershell
mvn nexus-staging:release
```

详细指南：https://central.sonatype.org/publish/publish-maven/

---

## 版本管理建议

### 版本命名规则（语义化版本）

- `1.0.0`：首个正式版本
- `1.1.0`：新增功能，向后兼容
- `1.0.1`：bug 修复，向后兼容
- `1.0.0-SNAPSHOT`：开发中的版本
- `1.0.0-RC1`：发布候选版本

### 修改版本号

所有模块的版本号集中在 `springlite-parent/pom.xml` 中（使用 `${project.version}` 属性）。

更新版本：

```powershell
cd D:\Projects\SpringLite\framework\springlite-build
mvn versions:set -DnewVersion=2.0.0
mvn versions:commit
```

---

## 日常开发流程

### 当改动 Framework 代码时：

```powershell
# 1. 提交代码到版本控制
git add .
git commit -m "Feature: Add xxx"

# 2. 重新编译并安装到本地仓库
cd D:\Projects\SpringLite\framework\springlite-build
mvn clean install -DskipTests

# 3. 其他项目立即可使用最新版本（无需任何操作）
```

### 当发布新版本时：

```powershell
# 1. 更新版本号
mvn versions:set -DnewVersion=1.1.0
mvn versions:commit

# 2. 打标签（如果使用 Git）
git tag -a v1.1.0 -m "Release version 1.1.0"

# 3. 编译并发布到仓库
mvn clean deploy -DskipTests

# 4. 推送标签
git push origin v1.1.0
```

---

## 常见问题

### Q: 本地修改后其他项目立即可用吗？
**A**: 是的。只要重新执行 `mvn clean install`，其他项目引用的依赖会自动更新（如果 IDE 的依赖解析已刷新）。

### Q: 如何强制重新下载依赖？
**A**: 删除本地仓库的相应目录，或使用 `-U` 参数：
```powershell
mvn clean compile -U
```

### Q: 如何在离线状态下使用本地仓库？
**A**: 使用离线模式：
```powershell
mvn clean compile -o
```

### Q: 多人开发时如何共享依赖？
**A**: 
- 小团队：使用 **方案 2**（文件系统共享）
- 大团队：使用 **方案 3**（Nexus）
- 开源项目：使用 **方案 4**（Maven Central）

### Q: 如何创建快照版本用于开发？
**A**: 在 pom.xml 中添加 `-SNAPSHOT` 后缀：
```xml
<version>1.1.0-SNAPSHOT</version>
```
发布快照版本：
```powershell
mvn clean deploy -DskipTests
```

---

## 参考资源

- [Maven 官方文档](https://maven.apache.org/guides/index.html)
- [Sonatype Nexus 文档](https://help.sonatype.com/repomanager3)
- [Maven Central Repository 发布指南](https://central.sonatype.org/publish/publish-maven/)
- [语义化版本 2.0.0](https://semver.org/lang/zh-CN/)

---

**最后更新**：2026-04-23  
**文档版本**：1.0  
**作者**：Caisense

