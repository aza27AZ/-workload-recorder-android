# 版本发布指南

本文档详细介绍如何发布新版本的Android应用。

## 发布前检查清单

在发布新版本前，请检查以下内容：

### 代码检查
- [ ] 所有测试通过
- [ ] 代码审查已完成
- [ ] CHANGELOG已更新
- [ ] 文档已更新
- [ ] 依赖项安全性已检查

### 功能检查
- [ ] 新功能已验证
- [ ] Bug已修复并验证
- [ ] 性能测试通过
- [ ] 兼容性测试通过

### 发布准备
- [ ] 版本号已更新
- [ ] 版本名称已确定
- [ ] 发布说明已准备
- [ ] 发布标签已准备

## 发布流程

### 1. 更新版本号

编辑 `app/build.gradle.kts`：

```kotlin
android {
    defaultConfig {
        versionCode = 2        // 每次发布+1
        versionName = "1.2.0"   // MAJOR.MINOR.PATCH
    }
}
```

#### 版本号规则

- **versionCode**: 整数，每次发布递增1
- **versionName**: 语义化版本 `MAJOR.MINOR.PATCH`
  - MAJOR: 重大功能变更
  - MINOR: 新增功能
  - PATCH: Bug修复

**示例**：
- `1.0.0` → 初始发布
- `1.0.1` → Bug修复
- `1.1.0` → 新增功能
- `2.0.0` → 重大更新

### 2. 更新CHANGELOG

确保CHANGELOG.md中添加新版本：

```markdown
## [1.2.0] - 2026-05-28

### Added
- 新功能1
- 新功能2

### Fixed
- 修复Bug A
- 修复Bug B
```

### 3. 提交更改

```bash
git add .
git commit -m "chore: Prepare release v1.2.0"
```

### 4. 创建Git标签

```bash
git tag -a v1.2.0 -m "Release v1.2.0"
git push origin v1.2.0
```

### 5. 自动发布

GitHub Actions会自动：
- 构建应用
- 生成APK
- 创建GitHub Release
- 上传构建的APK到Release

## 签名发布构建

如需签名发布，请按以下步骤：

### 准备工作

1. 确保已配置GitHub Secrets：
   - `KEYSTORE_BASE64`
   - `KEYSTORE_PASSWORD`
   - `KEY_ALIAS`
   - `KEY_PASSWORD`

### 触发签名构建

1. 进入GitHub仓库Actions页面
2. 选择"Build Android APK" workflow
3. 点击"Run workflow"
4. 勾选"Build signed release APK"
5. 点击"Run workflow"

## 发布后的检查

发布成功后，请检查：

### 验证项目已成功发布✅：
- [ ] GitHub Release创建成功
- [ ] 所有APK文件已上传
- [ ] 发布说明生成正确
- [ ] tag已发布说明检查：
- [ ] 用户可以下载APK
- [ ] 可以正常安装
- [ ] 应用功能正常

## 预发布版本

对于beta或rc版本：

```bash
git tag -a v1.2.0-beta -m "Pre-release v1.2.0-beta"
git push origin v1.2.0-beta
```

GitHub会将自动标记为预发布。

## 紧急修复

如果发现严重问题需要紧急修复：

```bash
git checkout v1.2.0
# 修复问题
git commit -m "fix: Critical bug fix"
git tag -a v1.2.1 -m "Hotfix release v1.2.1"
git push origin v1.2.1
```

## 回滚发布

如果新版本有严重问题：

1. 在GitHub Releases页面删除有问题的Release
2. 撤销Git标签：
```bash
git tag -d v1.2.0
git push origin :v1.2.0
```
3. 紧急修复并重新发布

## 发布最佳实践

1. **发布频率
- 每周小补丁发布
- 每月小版本发布
- 视情况需要大版本发布

2. **沟通
- 提前预告重大变更
- 更新用户提前通知用户
- 提供升级注意事项
- 更新说明详细变更

3. **文档
- 升级指南
- 更新说明
- 安装步骤
- 更新迁移
