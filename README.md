# Workload Recorder Android App

移动端工作量记录系统的Android原生应用，基于WebView构建。

## 功能特性

- ✅ 工作记录管理（值班、授课）
- ✅ 预约日历管理
- ✅ 请假记录管理
- ✅ 考勤记录管理
- ✅ 统计报表
- ✅ 数据导入导出
- ✅ 离线使用
- ✅ 请假与考勤自动联动
- ✅ 智能班次推荐

## 技术栈

- **Android**: WebView + Native Bridge
- **Kotlin**: 主要开发语言
- **Gradle**: 构建工具
- **GitHub Actions**: CI/CD

## 快速开始

### 前置要求

- Android Studio Hedgehog (2023.1.1) 或更高版本
- JDK 17
- Android SDK (API 24+)
- Gradle 8.5

### 本地构建

1. 克隆项目
```bash
git clone <repository-url>
cd WorkloadRecorder-Android
```

2. 同步Gradle依赖
```bash
./gradlew clean build
```

3. 构建Debug APK
```bash
./gradlew assembleDebug
```

4. 构建Release APK
```bash
./gradlew assembleRelease
```

## CI/CD (GitHub Actions)

### 自动构建

- 推送到 `main` 或 `master` 分支时自动构建
- Pull Request时自动验证和构建
- 打标签 `v*` 时自动创建发布

### 手动触发构建

在GitHub Actions页面可以手动触发构建，支持：
- 普通构建（Debug + 未签名Release）
- 签名发布构建（需要配置Secrets）

## 签名配置

### GitHub Actions签名发布

1. 生成密钥库（keystore）
```bash
keytool -genkey -v -keystore keystore.jks -keyalg RSA -keysize 2048 -validity 10000 -alias workload
```

2. 将keystore转为Base64
```bash
base64 -i keystore.jks > keystore-base64.txt
```

3. 在GitHub仓库的Settings > Secrets and variables > Actions中添加以下Secret：
   - `KEYSTORE_BASE64`: Base64编码的keystore文件内容
   - `KEYSTORE_PASSWORD`: 密钥库密码
   - `KEY_ALIAS`: 密钥别名
   - `KEY_PASSWORD`: 密钥密码

4. 触发workflow时选择"Build signed release APK"

### 本地签名

创建 `keystore.properties` 文件：
```properties
storeFile=keystore.jks
storePassword=your_keystore_password
keyAlias=your_key_alias
keyPassword=your_key_password
```

## 版本发布

### 发布新版本

1. 更新版本号
```kotlin
versionCode = 2
versionName = "1.2.0"
```

2. 创建Git标签
```bash
git tag -a v1.2.0 -m "Release v1.2.0"
git push origin v1.2.0
```

3. GitHub Actions会自动构建并创建Release

### 版本号规范

- 格式：`v{MAJOR}.{MINOR}.{PATCH}`
- MAJOR: 重大功能变更
- MINOR: 新增功能
- PATCH: Bug修复
- 示例：`v1.1.0`, `v1.1.1-beta`

## 安全指南

### 重要安全提醒

⚠️ **绝不**将以下文件提交到Git仓库：
- `*.jks` / `*.keystore`
- `keystore.properties`
- `local.properties`
- `.env` 等包含密钥的文件
- `google-services.json`

### GitHub Security最佳实践

1. 使用GitHub Secrets存储敏感信息
2. 开启仓库的Security alerts
3. 定期更新依赖包
4. 使用Dependabot自动更新
5. 审查依赖项的安全漏洞

## 项目结构

```
WorkloadRecorder-Android/
├── .github/
│   └── workflows/
│       └── build-apk.yml          # GitHub Actions工作流
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── assets/
│   │       │   └── www/           # WebView资源
│   │       ├── java/              # Kotlin代码
│   │       └── res/               # Android资源
│   └── build.gradle.kts           # App构建配置
├── gradle/
│   └── wrapper/
├── build.gradle.kts               # 根构建配置
├── settings.gradle.kts
└── gradle.properties
```

## 贡献指南

### 开发流程

1. Fork仓库
2. 创建功能分支
3. 提交更改
4. 创建Pull Request
5. 代码审查和合并

### 代码规范

- Kotlin官方代码风格
- 提交信息使用语义化格式
- 重要功能更新CHANGELOG

## 故障排除

### 构建问题

```bash
# 清理构建缓存
./gradlew clean

# 更新Gradle依赖
./gradlew build --refresh-dependencies
```

### 常见错误

1. **Gradle权限问题**
   ```bash
   chmod +x gradlew
   ```

2. **SDK版本不匹配**
   - 检查 `local.properties` 中的SDK路径
   - 更新到Android SDK Platform 34

## License

请查看项目根目录的LICENSE文件

## 支持

如有问题，请提交Issue或联系维护者。
