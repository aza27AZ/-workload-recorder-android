# Workload Recorder Android App - Project Memory

## 项目概况
移动端工作量记录系统：PWA → Android WebView壳应用（GitHub Actions云构建）

## 架构核心
- 单文件SPA（index.html ~1500行） + Android WebView壳
- STORE对象（localStorage持久化 + 自动渲染触发）
- JavaScriptInterface桥接（JS↔原生互调）

## 关键模式

### Android WebView壳工程
- Kotlin + WebView + JavaScriptInterface桥接
- ProGuard规则必须保留`@JavascriptInterface`方法
- `onShowFileChooser`必须重写支持Native文件选择器
- `lateinit`属性在`apply{}`块内使用局部变量模式
- 沉浸式状态栏：`enableEdgeToEdge()` + 透明主题 + CSS env()变量

### GitHub Actions云构建
- `gradle/actions/setup-gradle@v3` + `actions/setup-java@v4` + `actions/cache@v4`
- 签名密钥通过GitHub Secrets管理（KEYSTORE_BASE64, KEYSTORE_PASSWORD, KEY_ALIAS, KEY_PASSWORD）
- `gradle/actions/wrapper-validation@v3`验证Gradle wrapper安全性
- `workflow_dispatch`支持手动触发构建
- 自动Release：Git标签v*触发

### 数据层（MVVM式）
```javascript
STORE.set(key, val) → localStorage + markDirty('page') → requestAnimationFrame → renderXXX()
```
- 每个模块独立STORE.xxx.get/set
- set包装后自动触发render
- 删除操作支持撤销（__g._undo）
- 导入失败自动恢复（__g._importBackup）

### Android自适应图标
- adaptive-icon XML + 各密度PNG回退（mipmap-{hdpi..xxxhdpi}）
- 不同目录避免duplicate资源冲突

### 安全要求
- 所有用户输入必须escapeHtml转义（XSS防御）
- 密钥库从不提交到Git仓库（.gitignore + GitHub Secrets）
- 最小权限原则配置GitHub Actions

## 已知限制和注意事项
1. PowerShell不支持`&&`、`||`、`which`（用`;`、`Get-Command`替代）
2. minSdk=24时不能使用API 26+特性
3. !window.AndroidBridge判断区分PWA和Native环境
4. 文件选择器需要Web端DOM挂载（document.body.appendChild(input)）

## 已实现的核心功能
- 工作记录（值班、授课）→ 智能班次推荐
- 预约日历（功能区多选）
- 请假记录（年假/病假/事假/婚假/产假）
- 考勤记录（正常/迟到/早退/缺勤/请假）
- 请假→考勤自动联动
- 统计报表（值班/授课/运营）
- 数据导入/导出（含撤销恢复）
- 人员管理（删除关联提示）
- 标签栏徽章（当日数量显示）