# Maven 仓库配置指南

## 快速查看本地仓库

### Windows

```powershell
# 查看本地仓库路径
echo $env:USERPROFILE\.m2

# 打开 Maven 仓库文件夹
start C:\Users\YourUsername\.m2\repository
```

### Linux/Mac

```bash
# 查看本地仓库路径
echo ~/.m2

# 打开 Maven 仓库文件夹
open ~/.m2/repository
```

## SpringLite 框架 JAR 文件位置

```
~/.m2/repository/
└── com/
    └── handa/
        └── springlite/
            ├── springlite-parent/
            │   └── 1.0.0/
            │       └── springlite-parent-1.0.0.pom
            ├── springlite-context/
            │   └── 1.0.0/
            │       ├── springlite-context-1.0.0.jar
            │       ├── springlite-context-1.0.0-sources.jar
            │       └── springlite-context-1.0.0-javadoc.jar
            ├── springlite-aop/
            │   └── 1.0.0/
            │       ├── springlite-aop-1.0.0.jar
            │       ├── springlite-aop-1.0.0-sources.jar
            │       └── springlite-aop-1.0.0-javadoc.jar
            ├── springlite-jdbc/
            │   └── 1.0.0/
            │       ├── springlite-jdbc-1.0.0.jar
            │       ├── springlite-jdbc-1.0.0-sources.jar
            │       └── springlite-jdbc-1.0.0-javadoc.jar
            ├── springlite-web/
            │   └── 1.0.0/
            │       ├── springlite-web-1.0.0.jar
            │       ├── springlite-web-1.0.0-sources.jar
            │       └── springlite-web-1.0.0-javadoc.jar
            └── springlite-boot/
                └── 1.0.0/
                    ├── springlite-boot-1.0.0.jar
                    ├── springlite-boot-1.0.0-sources.jar
                    └── springlite-boot-1.0.0-javadoc.jar
```

## Maven settings.xml 配置

### 基本配置文件位置

```
Windows: C:\Users\<YourUsername>\.m2\settings.xml
Linux:   ~/.m2/settings.xml
Mac:     ~/.m2/settings.xml
```

### 最小化配置示例

```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                              http://maven.apache.org/xsd/settings-1.0.0.xsd">

    <!-- 本地仓库位置（默认为 ~/.m2/repository） -->
    <localRepository>${user.home}/.m2/repository</localRepository>

    <!-- 仅使用本地仓库（推荐用于离线开发） -->
    <offline>false</offline>

</settings>
```

### 完整配置示例（支持多个仓库）

```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                              http://maven.apache.org/xsd/settings-1.0.0.xsd">

    <localRepository>${user.home}/.m2/repository</localRepository>
    <interactiveMode>true</interactiveMode>
    <offline>false</offline>

    <!-- 配置代理（如果在公司网络需要） -->
    <proxies>
        <proxy>
            <id>company-proxy</id>
            <active>true</active>
            <protocol>http</protocol>
            <host>proxy.company.com</host>
            <port>8080</port>
            <username>proxyuser</username>
            <password>proxypass</password>
            <nonProxyHosts>*.company.com|localhost</nonProxyHosts>
        </proxy>
    </proxies>

    <!-- 服务器凭证（用于私有仓库） -->
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
        <server>
            <id>github</id>
            <username>your-github-username</username>
            <password>your-github-token</password>
        </server>
    </servers>

    <!-- 公开仓库配置 -->
    <repositories>
        <!-- 阿里云仓库（加速下载，推荐中国用户） -->
        <repository>
            <id>aliyun</id>
            <name>Aliyun Repository</name>
            <url>https://maven.aliyun.com/repository/public</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>

        <!-- Maven Central -->
        <repository>
            <id>central</id>
            <name>Maven Central Repository</name>
            <url>https://repo.maven.apache.org/maven2</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>

        <!-- 本地私有仓库（可选） -->
        <repository>
            <id>local-repo</id>
            <name>Local Repository</name>
            <url>file:///D:\maven-repo</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
    </repositories>

    <!-- 插件仓库配置 -->
    <pluginRepositories>
        <!-- 阿里云仓库 -->
        <pluginRepository>
            <id>aliyun-plugin</id>
            <name>Aliyun Plugin Repository</name>
            <url>https://maven.aliyun.com/repository/public</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </pluginRepository>

        <!-- Maven Central -->
        <pluginRepository>
            <id>central</id>
            <name>Maven Central Plugin Repository</name>
            <url>https://repo.maven.apache.org/maven2</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </pluginRepository>
    </pluginRepositories>

    <!-- 激活的配置 -->
    <activeProfiles>
        <activeProfile>default</activeProfile>
    </activeProfiles>

    <!-- 具体的配置集 -->
    <profiles>
        <profile>
            <id>default</id>
            <repositories>
                <repository>
                    <id>aliyun</id>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                </repository>
            </repositories>
        </profile>
    </profiles>

</settings>
```

## 常用 Maven 命令

### 本地开发

```bash
# 编译项目
mvn clean compile

# 编译 + 打包
mvn clean package

# 编译 + 打包 + 安装到本地仓库
mvn clean install

# 跳过测试
mvn clean install -DskipTests

# 只编译不打包
mvn clean verify

# 查看依赖树
mvn dependency:tree

# 查看依赖树并输出到文件
mvn dependency:tree > dependencies.txt

# 更新依赖
mvn clean compile -U
```

### 仓库操作

```bash
# 将本地 JAR 安装到仓库
mvn install:install-file \
  -DgroupId=com.handa.springlite \
  -DartifactId=springlite-boot \
  -Dversion=1.0.0 \
  -Dpackaging=jar \
  -Dfile=springlite-boot-1.0.0.jar

# 发布到远程仓库
mvn clean deploy

# 发布快照版本
mvn clean deploy -DskipTests

# 只发布不运行测试
mvn deploy -DskipTests
```

### 清理操作

```bash
# 清理构建文件
mvn clean

# 删除本地仓库中的某个依赖
rm -rf ~/.m2/repository/com/handa/springlite/springlite-boot

# 清理本地仓库中的过期文件
mvn dependency:purge-local-repository
```

## 离线开发模式

### 启用离线模式

```bash
# 运行 Maven 离线命令
mvn clean compile -o

# 或在 settings.xml 中配置
<offline>true</offline>
```

### 确保依赖已下载

```bash
# 在有网络时提前下载所有依赖
mvn dependency:resolve

# 验证依赖完整性
mvn dependency:go-offline
```

## 镜像配置（加速下载）

### 中国用户推荐配置

在 `settings.xml` 的 `<mirrors>` 部分添加：

```xml
<mirrors>
    <!-- 阿里云镜像 -->
    <mirror>
        <id>aliyun</id>
        <name>Aliyun Repository</name>
        <url>https://maven.aliyun.com/repository/public</url>
        <mirrorOf>central</mirrorOf>
    </mirror>
</mirrors>
```

### 其他镜像选项

```xml
<!-- 腾讯云镜像 -->
<mirror>
    <id>tencent</id>
    <name>Tencent Repository</name>
    <url>http://mirrors.cloud.tencent.com/nexus/repository/maven-public/</url>
    <mirrorOf>central</mirrorOf>
</mirror>

<!-- 华为云镜像 -->
<mirror>
    <id>huaweicloud</id>
    <name>Huawei Cloud Repository</name>
    <url>https://repo.huaweicloud.com/repository/maven/</url>
    <mirrorOf>central</mirrorOf>
</mirror>
```

## 常见问题

### Q: 如何查看 Maven 使用的哪个 settings.xml？

```bash
mvn --debug install 2>&1 | grep -i settings
```

### Q: 如何重置到默认配置？

```bash
# 删除本地配置（重新生成默认配置）
rm ~/.m2/settings.xml
```

### Q: 如何解决"无法下载依赖"的问题？

```bash
# 1. 清除缓存并重新下载
mvn clean compile -U

# 2. 检查网络连接和代理设置
mvn -X clean compile

# 3. 更换镜像源
# 编辑 settings.xml 中的 <mirrors> 部分
```

### Q: 如何在离线状态下使用本地仓库？

```bash
# 方式 1：启用离线模式
mvn clean compile -o

# 方式 2：修改 settings.xml
<offline>true</offline>
```

### Q: 如何手动添加本地 JAR 到仓库？

```bash
mvn install:install-file \
  -DgroupId=com.example \
  -DartifactId=my-lib \
  -Dversion=1.0.0 \
  -Dpackaging=jar \
  -Dfile=C:\path\to\my-lib-1.0.0.jar
```

## 仓库文件夹清理

### 清理未使用的依赖

```bash
# 删除 30 天未使用的本地仓库文件
mvn dependency:purge-local-repository \
  -DreResolved=false \
  -DactTransitively=false \
  -DincludeTransitive=true \
  -Dverbose=false \
  -DdaysInCache=30
```

### 完整清理

```bash
# Windows
rmdir /s /q C:\Users\YourUsername\.m2\repository
# 重新运行 Maven 命令会自动重新下载依赖

# Linux/Mac
rm -rf ~/.m2/repository
# 重新运行 Maven 命令会自动重新下载依赖
```

---

**更新时间**：2026-04-23  
**版本**：1.0  
**维护者**：Caisense

