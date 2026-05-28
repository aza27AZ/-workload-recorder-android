# 📱 部署与安装指南

## ✅ 首选方案（无需 APK，最简单！）

**不需要 APK，直接添加到手机桌面！

### iPhone（iOS）
1. 用 Safari 打开 Netlify 网址
2. 点底部分享按钮 → 「添加到主屏幕」
3. 桌面出现图标，直接打开即用

### Android
1. 用 Chrome 打开 Netlify 网址
2. 点右上角菜单 → 「添加到主屏幕」 / 「安装应用」
3. 桌面出现图标，全屏使用

---

## 📦 备选方案：生成 APK

如果一定要 APK，按以下操作：

### 重新部署（必须）
1. 删除旧的 Netlify 站点
2. 重新拖拽 `工作记录/` 文件夹到 https://app.netlify.com/drop
3. 确认新站点打开正常后继续

### PWA Builder 生成 APK
1. 打开 https://www.pwabuilder.com/
2. 粘贴新 Netlify 网址
3. 检测完成后点击 **"Package for Android"**
4. **注意：
   - 如果要安装失败，尝试勾选 **"Use Bubblewrap"** 选项
   - 或选择 **"Download"** → 下载 Android Studio 项目自己打包

---

## 📁 当前文件清单

```
工作记录/
├── index.html              ← 应用
├── manifest.json         ← 完整PWA配置（已更新）
├── sw.js               ← 离线缓存
├── netlify.toml         ← 部署规则
├── icon-36.png
├── icon-48.png
├── icon-72.png
├── icon-96.png
├── icon-144.png
├── icon-168.png
├── icon-192.png
├── icon-512.png
└── generate_icons.py     ← （可删除）
```

---

## 📝 更新数据

- 备份：管理 → 导出数据（下载 JSON）
- 恢复：管理 → 导入数据（上传 JSON）