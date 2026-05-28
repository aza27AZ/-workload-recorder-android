# 🚀 三分钟部署指南

这个文件夹包含一个完整的团队工作量记录系统，是一个 **PWA（渐进式Web应用）**。

## 🎯 终极目标：修改代码 → 生成APK

```
修改 workload-app.html → 保存 → 刷新浏览器看到效果 → 部署到Vercel → PWA Builder生成APK
```

整个过程只需要 **3分钟**！

---

## 第一步：预览应用（立刻试）

### 方式A：直接双击打开
双击 `workload-app.html` 即可在浏览器中运行

### 方式B：启动本地服务器（推荐）
```bash
cd d:\下载缓存\移动端工作量记录
python -m http.server 8000
```
然后访问：http://localhost:8000/workload-app.html

---

## 第二步：修改代码（超级简单）

用 **记事本** 或任何文本编辑器打开 `workload-app.html`：

- 改文字：直接搜索替换
- 改颜色：修改 `:root` 中的CSS变量
- 改功能：找到对应的JS函数
- **保存后刷新浏览器即可看到效果**

---

## 第三步：部署到Vercel（免费，1分钟）

1. 访问 https://vercel.com/new
2. 选择「Deploy without Git」
3. 选中 `workload-app.html`、`manifest.json`、`sw.js`
4. 点击 Deploy
5. 获得一个 HTTPS 网址（如：`wl-app.vercel.app`）

**以后改完代码，重新上传替换文件即可**

---

## 第四步：生成APK（10秒）

1. 访问 https://www.pwabuilder.com/
2. 输入你的Vercel网址
3. 点击「Generate APK」
4. 下载APK并安装到手机

**每次修改代码 → 重新部署到Vercel → 刷新PWA Builder → 下载新APK**

---

## 📂 文件说明

| 文件 | 用途 | 你会修改它吗？ |
|------|------|--------------|
| `workload-app.html` | **核心应用**（所有代码都在这里） | ✅ 经常改 |
| `manifest.json` | PWA配置（应用名、图标） | ✅ 有时改 |
| `sw.js` | 离线支持 | ❌ 不需要改 |
| `icon-192.svg` | 应用图标 | ✅ 想换图标时改 |
| `icon-512.svg` | 大图标 | ✅ 想换图标时改 |
| `generate-icons.html` | 图标生成工具 | ❌ 一次性的 |
| `app-prototype.html` | 旧原型 | ❌ 已废弃 |

---

## 💡 使用技巧

### 安装到手机桌面
用手机浏览器打开应用 → 分享 → 添加到主屏幕

### 数据备份
管理 → 导出数据 → 得到JSON文件

### 数据恢复
管理 → 导入数据 → 选择JSON文件

---

## 🔧 常见修改

### 改应用名称
打开 `manifest.json`，修改 `name` 和 `short_name`

### 改主题色
打开 `workload-app.html`，搜索 `:root`，修改 `--accent:#0071e3`

### 添加默认功能区
打开 `workload-app.html`，搜索 `['游泳区','篮球场','羽毛球场','乒乓球室']` 修改

### 添加默认课程类型
打开 `workload-app.html`，搜索 `['私教课','团课','体验课','公开课']` 修改

---

**尽情享受这种极致的开发体验吧！** 🎉