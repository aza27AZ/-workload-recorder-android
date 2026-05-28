# 工作量记录 - APK构建指南

## 项目简介

这是一个使用 Kotlin + Jetpack Compose 开发的 Android 团队工作量记录应用。

## 在线APK构建方法

### 方法一：使用 GitHub Actions（推荐）

#### 步骤：

1. **将项目上传到 GitHub**
   ```bash
   # 先将 WorkloadRecorder 文件夹初始化为 Git 仓库
   cd WorkloadRecorder
   git init
   git add .
   git commit -m "Initial commit"
   ```

2. **创建 GitHub Actions 工作流**
   在项目根目录创建 `.github/workflows/build.yml` 文件：

```yaml
name: Build APK

on:
  push:
    branches: [ main, master ]
  workflow_dispatch:

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v4

      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Grant execute permission for gradlew
        run: chmod +x gradlew

      - name: Build with Gradle
        run: ./gradlew assembleDebug

      - name: Upload APK
        uses: actions/upload-artifact@v4
        with:
          name: app-debug
          path: app/build/outputs/apk/debug/app-debug.apk
```

3. **推送到 GitHub**
   ```bash
   git remote add origin <你的GitHub仓库地址>
   git push -u origin main
   ```

4. **触发构建**
   - 访问你的 GitHub 仓库
   - 点击 "Actions" 标签
   - 选择 "Build APK" workflow
   - 点击 "Run workflow"

5. **下载APK**
   - 构建完成后，在 workflow 页面底部找到 "Artifacts"
   - 下载 `app-debug.zip`，解压即可得到 APK

---

### 方法二：使用 AppVeyor

1. 访问 https://www.appveyor.com
2. 注册账号并登录
3. 导入你的 GitHub 项目
4. 创建 `appveyor.yml` 配置文件：

```yaml
image: Ubuntu2004

stack: jdk 17

build_script:
  - chmod +x gradlew
  - ./gradlew assembleDebug

artifacts:
  - path: app/build/outputs/apk/debug/app-debug.apk
    name: app-debug
```

---

### 方法三：使用 Bitrise

1. 访问 https://www.bitrise.io
2. 注册并导入项目
3. 自动识别 Android 项目配置
4. 在 workflow 中添加 "Android Build" 步骤
5. 运行构建并下载 APK

---

### 方法四：使用 Cirrus CI

1. 访问 https://cirrus-ci.org
2. 与 GitHub 集成
3. 创建 `.cirrus.yml`：

```yaml
container:
  image: cirrusci/android-sdk:30
  cpu: 8
  memory: 16G

android_task:
  install_script: ./gradlew dependencies
  assemble_script: ./gradlew assembleDebug
  apk_artifacts:
    path: app/build/outputs/apk/debug/app-debug.apk
```

---

## 本地开发

如果你后来想在本地构建，需要：

1. 安装 Android Studio
2. 克隆项目
3. 在 Android Studio 中打开项目
4. 等待 Gradle 同步
5. 连接 Android 设备或启动模拟器
6. 点击运行按钮

## 项目结构

```
WorkloadRecorder/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/workload/recorder/
│   │       │   ├── data/           # 数据层
│   │       │   ├── ui/             # UI层
│   │       │   ├── MainActivity.kt
│   │       │   └── WorkloadApp.kt
│   │       └── res/                # 资源文件
│   └── build.gradle.kts
├── gradle/
│   └── wrapper/
├── gradle.properties
├── settings.gradle.kts
├── build.gradle.kts
└── gradlew / gradlew.bat
```

## 功能特性

- ✅ 值班记录（早班、晚班、通班）
- ✅ 授课记录（课时统计）
- ✅ 预约日历（月视图）
- ✅ 统计报表
- ✅ 管理设置
- ✅ Jetpack Compose UI
- ✅ Room 数据库支持

## 注意事项

⚠️ **重要提示**：
- 当前版本是 Debug 版本，适合测试使用
- 正式发布需要配置签名密钥
- 首次构建可能需要较长时间下载依赖

📱 **最低系统要求**：
- Android 8.0 (API 26) 或更高
- 支持 Android 14 (API 34)

---

## 技术支持

如果构建遇到问题，检查：
1. JDK 版本是否为 17
2. Gradle wrapper 是否可执行
3. 网络连接是否正常（需要下载依赖）
