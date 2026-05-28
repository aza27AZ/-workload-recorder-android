# 🚀 三分钟部署与APK生成指南

## 📂 部署文件清单

```
工作记录/
├── index.html          ← 全部功能（值班/授课/日历/统计/管理）
├── manifest.json       ← PWA配置（已含PNG图标）
├── sw.js               ← 离线缓存支持
├── netlify.toml        ← Netlify部署规则
├── icon-192.png        ← 应用图标 192x192
└── icon-512.png        ← 应用图标 512x512
```

---

## 第一步：部署到 Netlify Drop

1. 访问 https://app.netlify.com/drop
2. **拖拽整个 `工作记录/` 文件夹** 进去
3. 等待几秒 → 获得 `https://xxx.netlify.app` 网址 → 复制保存

---

## 第二步：本地预览（可选）

```bash
cd 工作记录
npx serve .        # 方式一
python -m http.server 8000  # 方式二
```

浏览器打开 `http://localhost:8000/`

---

## 第三步：用 PWA Builder 生成 APK

1. 访问 https://www.pwabuilder.com/
2. 粘贴 Netlify 网址
3. 等几秒检测通过（图标、manifest、Service Worker 都已配置好）
4. 点 **"Package for Android"** → **"Generate APK"**
5. 下载 APK → 安装到手机

---

## 以后修改

1. **改代码**：记事本打开 `index.html`，改完保存
2. **本地看效果**：双击文件或 `npx serve .`
3. **重新部署**：拖拽整个文件夹到 Netlify Drop（覆盖更新）
4. **重新生成APK**：PWA Builder 刷新网址 → 下载新版

---

## 数据备份

- **备份**：管理 → 导出数据（下载 JSON 文件）
- **恢复**：管理 → 导入数据（上传 JSON 文件）