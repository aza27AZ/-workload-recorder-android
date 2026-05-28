# 贡献指南

感谢您有兴趣为这个项目作出贡献！

## 开发环境设置

### 必备条件

- Android Studio Hedgehog (2023.1.1) 或更高
- JDK 17
- Android SDK API 34
- Git

### 克隆仓库

```bash
git clone <repository-url>
cd WorkloadRecorder-Android
```

### 本地开发

1. 在Android Studio中打开项目
2. 等待Gradle同步完成
3. 选择设备并运行应用

## 代码贡献流程

### 1. 选择问题

- 查找带有`good first issue`或`help wanted`标签的问题
- 创建新问题讨论您想要实现的功能

### 2. Fork仓库

- 点击GitHub上的"Fork"按钮
- 将您的fork克隆到本地

### 3. 创建分支

```bash
git checkout -b feature/your-feature-name
# 或
git checkout -b fix/issue-description
```

### 4. 开发更改

- 遵循现有的代码风格
- 添加适当的测试
- 确保代码可以正常构建

### 5. 提交更改

使用清晰和描述性的提交信息：

```
type: Short description (72 chars max)

Optional longer description, explaining the what and why of this change.

Closes: #issue-number
```

**类型**：
- `feat`: 新功能
- `fix`: Bug修复
- `docs`: 文档更新
- `style`: 代码格式
- `refactor`: 重构
- `test`: 测试相关
- `chore`: 构建/工具相关

### 6. 推送并创建PR

```bash
git push origin your-branch
```

然后在GitHub上创建Pull Request。

## PR提交检查清单

提交PR之前，请确保：

- [ ] 代码已在本地测试
- [ ] 所有现有测试通过
- [ ] 代码风格遵循项目规范
- [ ] 文档已相应更新
- [ ] 提交信息格式正确
- [ ] PR关联了相关问题
- [ ] 添加了适当的标签

## 代码审查

所有PR将由维护者审查。可能需要一些调整才能合并。

## 代码风格

### Kotlin

遵循官方Kotlin代码风格指南：https://kotlinlang.org/docs/coding-conventions.html

### Git提交信息

- 使用英文提交信息
- 首字母大写
- 简短、清晰的描述
- 使用语义化格式

## 报告问题

### Bug报告

请提供以下信息：

- 操作系统和版本
- Android设备型号和版本
- 应用版本
- 复现步骤
- 预期行为
- 实际行为
- 屏幕截图（如果有）
- 日志（如果有）

### 功能请求

请描述：

- 功能的详细描述
- 为什么需要这个功能
- 可能的实现思路

## 获取帮助

如果您在开发过程中遇到问题：

1. 查看现有文档
2. 搜索已有的Issue
3. 创建新的问题
4. 在Discussions中提问

## 发布流程

当准备发布新版本时：

1. 更新`app/build.gradle.kts`中的版本号
2. 确保CHANGELOG.md已更新
3. 创建Git标签
4. 推送标签触发自动发布

## 行为准则

### 我们的承诺

我们致力于为每个人提供友好和安全的环境。

### 可接受的行为

- 友好和专业
- 尊重不同的意见
- 接受建设性的批评
- 关注项目的最佳利益

### 不可接受的行为

- 骚扰或歧视
- 人身攻击
- 发布他人的私人信息
- 其他不专业或不恰当的行为

## 许可证

通过贡献代码，您同意您的贡献将在项目的LICENSE下许可。

---

再次感谢您的贡献！
