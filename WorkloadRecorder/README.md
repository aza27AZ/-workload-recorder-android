# 工作量记录 App

一个基于 Kotlin 和 Jetpack Compose 的 Android 应用，用于记录团队工作量。

## 功能特性

- **工作记录管理**
  - 值班记录（早班、晚班、通班）
  - 授课记录（课程类型、人数、课时）
  - 支持按日期筛选（全部/今日/本周/本月）

- **预约日历**
  - 月视图日历展示
  - 预约记录管理
  - 多功能区支持

- **统计报表**
  - 值班统计（按人员、班次）
  - 授课统计（课时、人数）
  - 运营数据统计
  - 支持季度/年度视图
  - 数据导出功能

- **管理设置**
  - 工作人员管理
  - 功能区配置
  - 上课类型配置
  - 数据备份与恢复

## 技术栈

- **UI**: Jetpack Compose + Material 3
- **架构**: MVVM
- **数据库**: Room
- **导航**: Navigation Compose
- **依赖注入**: 手动注入（可通过 Hilt 扩展）
- **异步**: Kotlin Coroutines + Flow

## 项目结构

```
app/src/main/java/com/workload/recorder/
├── data/
│   ├── database/     # Room 数据库和 DAO
│   ├── model/         # 数据模型
│   └── repository/    # 仓库层
├── ui/
│   ├── components/   # 可复用 UI 组件
│   ├── screens/       # 页面组件
│   └── theme/         # 主题配置
├── MainActivity.kt
└── WorkloadApp.kt
```

## 开发环境要求

- **Android Studio**: Hedgehog (2023.1.1) 或更高版本
- **JDK**: 17
- **Android SDK**: API 34 (Android 14)
- **Kotlin**: 1.9.20
- **Gradle**: 8.2

## 开始开发

### 1. 安装 Android Studio

下载并安装 [Android Studio](https://developer.android.com/studio)

### 2. 打开项目

1. 启动 Android Studio
2. 选择 "Open an existing project"
3. 选择 `WorkloadRecorder` 文件夹
4. 等待 Gradle 同步完成

### 3. 运行应用

1. 连接 Android 设备或启动模拟器
2. 点击 Android Studio 工具栏中的 Run 按钮
3. 选择目标设备
4. 应用将自动安装并运行

### 4. 构建调试 APK

```bash
./gradlew assembleDebug
```

APK 文件将生成在 `app/build/outputs/apk/debug/` 目录

### 5. 构建发布 APK

```bash
./gradlew assembleRelease
```

## 配置说明

### local.properties

确保配置正确的 Android SDK 路径：

```properties
sdk.dir=D\:\\Android\\Sdk
```

### Gradle 版本

如需更新 Gradle 版本，修改 `gradle/wrapper/gradle-wrapper.properties` 中的 `distributionUrl`。

## 数据模型

### 工作人员 (Staff)
- `id`: 唯一标识
- `name`: 姓名
- `remark`: 备注（值班人员/顶班人员）

### 值班记录 (DutyRecord)
- `id`: 唯一标识
- `staffId`: 工作人员ID
- `date`: 日期
- `shiftType`: 班次类型（早班/晚班/通班）
- `startTime`: 开始时间
- `endTime`: 结束时间

### 授课记录 (TeachingRecord)
- `id`: 唯一标识
- `staffId`: 教练ID
- `courseType`: 课程类型
- `participantCount`: 参与人数
- `date`: 日期
- `hours`: 课时数

### 预约记录 (BookingRecord)
- `id`: 唯一标识
- `bookingSubject`: 预约主体
- `activityPlan`: 活动方案
- `date`: 预约日期
- `startTime`: 开始时间
- `endTime`: 结束时间
- `zoneNames`: 功能区
- `participantCount`: 参与人数
- `contactPerson`: 对接人

### 运营记录 (OperationRecord)
- `id`: 唯一标识
- `weekNumber`: 周数
- `year`: 年份
- `totalVisitors`: 总访客数

## 后续开发建议

1. **完善 ViewModel**: 实现数据层与 UI 层的连接
2. **添加数据导入导出**: 实现 Excel 导出功能
3. **云备份**: 集成云同步功能
4. **通知提醒**: 添加预约和值班提醒
5. **数据可视化**: 添加图表展示统计数据

## 许可证

MIT License
