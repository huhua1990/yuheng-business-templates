# Yuheng Business Templates

## ⚠️ 这不是依赖库！

本仓库提供**业务模板代码**，用于快速启动业务开发。

## 使用方式

### ❌ 不要这样做

```xml
<!-- 不要作为 Maven 依赖引入 -->
<dependency>
    <groupId>com.yuheng.templates</groupId>
    <artifactId>template-member</artifactId>
</dependency>
```

### ✅ 正确做法

```bash
# 1. 拷贝模板到你的项目
cp -r template-member your-project/your-member-module

# 2. 修改包名
find your-member-module -name "*.java" \
  -exec sed -i 's/com.yuheng.template.member/com.yourcompany.member/g' {} +

# 3. 根据业务需求自由开发
```

## 模板列表

| 模板 | 说明 | 完整度 | 适用场景 |
|-----|------|-------|---------|
| **template-member** | 会员管理 | 60% | 需要会员体系的应用 |
| template-bpm | 工作流 | 50% | 审批流程场景 |
| template-mall | 电商商城 | 40% | B2C商城起点 |
| template-crm | 客户管理 | 40% | CRM系统起点 |

**注**: 目前只实现了 template-member，其他模板待添加。

## 模板结构

每个模板包含：

```
template-xxx/
├── README.md                         # 模板说明文档
├── src/main/java/com/yuheng/template/xxx/
│   ├── controller/                   # API层
│   ├── service/                      # 业务逻辑
│   ├── dal/                          # 数据访问
│   └── vo/                           # 视图对象
├── src/main/resources/sql/
│   └── xxx-init.sql                  # 数据库脚本
└── ui/
    ├── api/xxx.ts                    # 前端API
    └── views/xxx/                    # 前端页面
```

## template-member 会员管理模板

### 功能清单

已实现（60%）：
- ✅ 会员基础CRUD
- ✅ 会员等级管理
- ✅ 数据库表结构
- ✅ 基础前端页面

待实现（40%）：
- ⚠️ 会员注册流程（需集成用户服务）
- ⚠️ 会员等级升级逻辑
- ⚠️ 积分管理
- ⚠️ 短信通知集成

### 使用步骤

1. **拷贝到你的项目**
   ```bash
   cp -r template-member ../your-project/src/main/java/com/yourcompany/member
   ```

2. **修改包名**
   ```bash
   cd ../your-project
   find . -name "*.java" -exec sed -i '' \
     's/com.yuheng.template.member/com.yourcompany.member/g' {} +
   ```

3. **实现业务逻辑**
   - 在 `MemberServiceImpl` 中实现完整的业务逻辑
   - 集成 yuheng-core 的平台服务（AdminUserService、SmsService等）
   - 完善前端页面

4. **运行数据库脚本**
   ```bash
   mysql -u root -p your_database < src/main/resources/sql/member-init.sql
   ```

## 模板代码来源

从 [ruoyi-vue-pro](https://gitee.com/zhijiantianya/ruoyi-vue-pro) 提取，经过以下调整：

1. 包名改为 `com.yuheng.template.*`
2. 移除对 ruoyi 特定依赖
3. 适配 yuheng-core 框架
4. 标注未完成部分（TODO）
5. 添加详细注释

## 与 yuheng-demo 的关系

- **yuheng-business-templates**: 提供起点代码（40-60%）
- **yuheng-demo**: 展示完整实现（100%）

工作流程：
```
模板库 → 拷贝代码 → 定制开发 → 完整业务
```

## 贡献模板

欢迎贡献新的业务领域模板！

### 模板要求

1. **完整度**: 提供 40-60% 的基础代码
2. **标注**: 使用 TODO 标注待实现部分
3. **文档**: 包含详细的 README.md
4. **规范**: 遵循 yuheng-core 的代码规范
5. **独立**: 不依赖其他业务模块

### 提交流程

1. Fork 本仓库
2. 创建模板目录 `template-xxx/`
3. 按照标准结构组织代码
4. 编写 README.md
5. 提交 Pull Request

## 常见问题

### Q: 模板更新了，我的项目需要同步吗？

**A**: 不需要。拷贝后就是你的代码了，独立维护。

### Q: 模板可以直接用于生产吗？

**A**: 不能。模板只是起点代码，需要根据实际业务完善。

### Q: 如何选择合适的模板？

**A**: 
1. 查看模板列表的"适用场景"
2. 阅读模板的 README.md
3. 查看模板的功能清单
4. 参考 yuheng-demo 中的完整实现

### Q: 模板支持哪些技术栈？

**A**: 基于 yuheng-core v0.1.0
- Spring Boot 3.5.15
- Java 17
- MyBatis Plus
- Vue 3 + TypeScript

## 相关资源

- **yuheng-core**: https://github.com/your-org/yuheng-core
- **yuheng-demo**: https://github.com/your-org/yuheng-demo
- **架构文档**: 查看 yuheng 项目根目录的文档

## 许可证

[根据实际情况填写]

---

**维护者**: Yuheng 团队  
**版本**: v0.1.0  
**最后更新**: 2024-11-09
