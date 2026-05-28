# 快速开始 - 5分钟构建APK

## 最简单的方法

### 🚀 使用 GitHub Actions（免费，无需本地环境）

#### 第1步：准备项目

```bash
# 进入项目目录
cd WorkloadRecorder

# 初始化Git
git init
git add .
git commit -m "Initial commit"
```

#### 第2步：创建GitHub仓库

1. 访问 https://github.com/new
2. 创建新仓库（不要初始化，保持空白）
3. 按照提示将代码推送到GitHub

#### 第3步：添加构建配置

在项目根目录创建 `.github/workflows/build.yml`，内容：

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

      - name: Build APK
        run: |
          chmod +x gradlew
          ./gradlew assembleDebug

      - name: Upload APK
        uses: actions/upload-artifact@v4
        with:
          name: workload-app
          path: app/build/outputs/apk/debug/app-debug.apk
```

#### 第4步：推送到GitHub

```bash
git add .github/workflows/build.yml
git commit -m "Add build workflow"
git push origin main
```

#### 第5步：触发构建并下载

1. 访问你的GitHub仓库
2. 点击 **Actions** 标签
3. 等待构建完成（约2-5分钟）
4. 点击最新的 workflow run
5. 向下滚动到 **Artifacts** 部分
6. 下载 `workload-app.zip`
7. 解压得到 `app-debug.apk`

---

## ✅ 完成！

现在你有了APK文件，可以安装到Android设备上测试了！

## 📱 安装APK

1. 将 `app-debug.apk` 传输到手机
2. 在手机上打开文件
3. 允许安装未知来源应用
4. 点击安装
5. 完成！

---

## 🔧 故障排除

### 如果构建失败：

1. **检查gradle wrapper权限**
   - 确保 `gradlew` 文件有执行权限

2. **检查JDK版本**
   - 确保使用JDK 17

3. **查看构建日志**
   - 在GitHub Actions页面查看详细错误信息

---

## 📌 其他在线构建服务

如果GitHub Actions不工作，试试：
- **AppVeyor**: https://www.appveyor.com
- **Bitrise**: https://www.bitrise.io
- **Cirrus CI**: https://cirrus-ci.org

详细说明请查看 `BUILD_GUIDE.md`
