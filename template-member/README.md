# Template-Member 会员管理模板

## 模板说明

这是一个会员管理业务模板，提供会员管理的基础代码框架（约60%完整度）。

## 功能清单

### 已实现 ✅

1. **会员基础管理**
   - 会员 CRUD 接口
   - 会员分页查询
   - 会员状态管理

2. **会员等级管理**
   - 等级 CRUD 接口
   - 等级配置

3. **数据库设计**
   - member 表（会员表）
   - member_level 表（会员等级表）

4. **前端页面**
   - 会员列表页
   - 会员表单
   - 会员等级管理

### 待实现 ⚠️

1. **会员注册流程**（需要你实现）
   - 集成 yuheng-core 的 AdminUserService 创建系统用户
   - 发送欢迎短信（集成 SmsService）
   - 初始化会员积分

2. **会员等级升级**（需要你实现）
   - 根据积分自动升级
   - 升级通知（短信/站内信）
   - 升级日志记录

3. **积分管理**（需要你实现）
   - 积分获取规则
   - 积分消费
   - 积分兑换
   - 积分历史记录

4. **高级功能**（可选）
   - 会员标签
   - 会员分组
   - 会员统计报表

## 使用步骤

### 1. 拷贝代码到你的项目

```bash
# 假设你的项目是 yuheng-demo
cd your-project
cp -r /path/to/template-member/src/main/java/com/yuheng/template/member \
      src/main/java/com/yuheng/demo/member
```

### 2. 修改包名

```bash
find src/main/java/com/yuheng/demo/member -name "*.java" \
  -exec sed -i '' 's/com.yuheng.template.member/com.yuheng.demo.member/g' {} +
```

### 3. 运行数据库脚本

```sql
-- 在你的数据库中执行
source src/main/resources/sql/member-init.sql
```

### 4. 实现业务逻辑

#### 实现会员注册

在 `MemberServiceImpl` 中：

```java
@Override
@Transactional(rollbackFor = Exception.class)
public Long registerMember(MemberRegisterReqVO reqVO) {
    // TODO: 1. 创建系统用户（集成 AdminUserService）
    UserSaveReqVO userReqVO = new UserSaveReqVO();
    userReqVO.setUsername(reqVO.getMobile());
    userReqVO.setNickname(reqVO.getNickname());
    Long userId = adminUserService.createUser(userReqVO);
    
    // TODO: 2. 创建会员档案
    MemberDO member = BeanUtils.toBean(reqVO, MemberDO.class);
    member.setUserId(userId);
    member.setLevelId(1L); // 默认等级
    member.setPoint(100);  // 注册送积分
    memberMapper.insert(member);
    
    // TODO: 3. 发送欢迎短信（集成 SmsService）
    smsService.sendSingleSms(reqVO.getMobile(), "WELCOME_TEMPLATE", 
        Map.of("nickname", reqVO.getNickname()));
    
    return member.getId();
}
```

#### 实现等级升级

```java
@Override
@Transactional(rollbackFor = Exception.class)
public void upgradeMemberLevel(Long memberId) {
    // TODO: 1. 查询会员当前积分
    MemberDO member = memberMapper.selectById(memberId);
    
    // TODO: 2. 根据积分确定新等级
    MemberLevelDO newLevel = memberLevelMapper.selectByRequiredPoint(member.getPoint());
    
    // TODO: 3. 更新会员等级
    if (newLevel != null && !Objects.equals(newLevel.getId(), member.getLevelId())) {
        member.setLevelId(newLevel.getId());
        memberMapper.updateById(member);
        
        // TODO: 4. 发送升级通知
        smsService.sendSingleSms(member.getMobile(), "LEVEL_UPGRADE_TEMPLATE",
            Map.of("level", newLevel.getName()));
    }
}
```

### 5. 集成平台服务

需要注入以下服务：

```java
@Service
public class MemberServiceImpl implements MemberService {
    
    @Resource
    private MemberMapper memberMapper;
    
    @Resource
    private AdminUserService adminUserService; // 来自 yuheng-module-system
    
    @Resource
    private SmsService smsService; // 来自 yuheng-module-system
    
    @Resource
    private FileService fileService; // 来自 yuheng-module-infra（可选）
}
```

### 6. 配置权限

在数据库中添加菜单和权限：

```sql
-- 会员管理菜单
INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, status)
VALUES ('会员管理', '', 1, 10, 0, '/member', 'user', '', 0);

-- 会员列表
INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, status)
VALUES ('会员列表', 'demo:member:query', 2, 1, 上面的ID, 'list', '', 'demo/member/index', 0);

-- 会员操作权限
INSERT INTO system_menu (name, permission, type, sort, parent_id)
VALUES ('会员创建', 'demo:member:create', 3, 1, 上面的ID);
INSERT INTO system_menu (name, permission, type, sort, parent_id)
VALUES ('会员更新', 'demo:member:update', 3, 2, 上面的ID);
INSERT INTO system_menu (name, permission, type, sort, parent_id)
VALUES ('会员删除', 'demo:member:delete', 3, 3, 上面的ID);
```

## 代码结构

```
template-member/
├── src/main/java/com/yuheng/template/member/
│   ├── controller/
│   │   ├── MemberController.java           # 会员管理 API
│   │   └── MemberLevelController.java      # 会员等级 API
│   ├── service/
│   │   ├── MemberService.java              # 会员服务接口
│   │   ├── MemberLevelService.java         # 等级服务接口
│   │   └── impl/
│   │       ├── MemberServiceImpl.java      # 会员服务实现（需完善）
│   │       └── MemberLevelServiceImpl.java # 等级服务实现
│   ├── dal/
│   │   ├── dataobject/
│   │   │   ├── MemberDO.java               # 会员数据对象
│   │   │   └── MemberLevelDO.java          # 等级数据对象
│   │   └── mysql/
│   │       ├── MemberMapper.java           # 会员 Mapper
│   │       └── MemberLevelMapper.java      # 等级 Mapper
│   └── vo/
│       ├── MemberCreateReqVO.java          # 创建请求
│       ├── MemberUpdateReqVO.java          # 更新请求
│       ├── MemberPageReqVO.java            # 分页查询
│       ├── MemberRespVO.java               # 响应对象
│       └── ... (等级相关 VO)
│
├── src/main/resources/sql/
│   └── member-init.sql                     # 数据库初始化脚本
│
└── ui/
    ├── api/member.ts                       # 前端 API 封装
    └── views/member/
        ├── index.vue                       # 会员列表页
        ├── MemberForm.vue                  # 会员表单
        └── components/
            └── MemberLevelDialog.vue       # 等级对话框
```

## 数据库设计

### member 表

| 字段 | 类型 | 说明 |
|-----|------|-----|
| id | BIGINT | 会员ID |
| user_id | BIGINT | 关联的系统用户ID |
| nickname | VARCHAR(64) | 会员昵称 |
| mobile | VARCHAR(11) | 手机号 |
| avatar | VARCHAR(255) | 头像 |
| level_id | BIGINT | 会员等级ID |
| point | INT | 积分 |
| status | TINYINT | 状态（0正常 1禁用） |
| birthday | DATE | 生日 |
| gender | TINYINT | 性别（1男 2女） |

### member_level 表

| 字段 | 类型 | 说明 |
|-----|------|-----|
| id | BIGINT | 等级ID |
| name | VARCHAR(32) | 等级名称 |
| required_point | INT | 所需积分 |
| discount | DECIMAL(3,2) | 折扣率 |
| icon | VARCHAR(255) | 等级图标 |

## API 接口

### 会员管理

```
POST   /demo/member/create     创建会员
PUT    /demo/member/update     更新会员
DELETE /demo/member/delete     删除会员
GET    /demo/member/get        查询会员详情
GET    /demo/member/page       分页查询会员
```

### 会员等级

```
POST   /demo/member-level/create     创建等级
PUT    /demo/member-level/update     更新等级
DELETE /demo/member-level/delete     删除等级
GET    /demo/member-level/get        查询等级详情
GET    /demo/member-level/list       查询等级列表
```

## 前端页面

### 会员列表页 (index.vue)

- 搜索表单（昵称、手机号、等级、状态）
- 数据表格（展示会员信息）
- 操作按钮（新增、编辑、删除）
- 分页组件

### 会员表单 (MemberForm.vue)

- 基本信息（昵称、手机号、头像）
- 会员信息（等级、积分、状态）
- 个人信息（生日、性别）

## 注意事项

1. **包名替换**: 务必将所有的 `com.yuheng.template.member` 替换为你的包名
2. **平台服务**: 正确注入 yuheng-core 提供的服务
3. **权限控制**: 为每个 API 添加 `@PreAuthorize` 注解
4. **事务管理**: 涉及多表操作的方法要加 `@Transactional`
5. **参数校验**: 使用 `@Valid` 和 Bean Validation
6. **错误码**: 在 `ErrorCodeConstants` 中定义业务错误码
7. **操作日志**: 重要操作添加 `@OperateLog` 注解

## 完整度

- ✅ 数据结构设计: 100%
- ✅ 基础 CRUD: 100%
- ⚠️ 业务逻辑: 30%（需要你实现）
- ✅ 前端页面: 80%
- ⚠️ 平台集成: 0%（需要你实现）

**总体完整度**: 约 60%

## 参考资源

- **yuheng-core 文档**: 查看平台提供的服务和能力
- **yuheng-demo**: 查看完整的会员管理实现示例
- **代码规范**: 遵循 yuheng-core 的代码规范

## 获取帮助

- 阅读 yuheng-demo 的会员管理完整实现
- 查看 yuheng-core 的 API 文档
- 使用 AI 助手完善业务逻辑

---

**模板版本**: v0.1.0  
**适用平台**: yuheng-core v0.1.0+  
**最后更新**: 2024-11-09
