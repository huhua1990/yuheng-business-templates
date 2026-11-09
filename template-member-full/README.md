# Template-Member-Full 完整会员管理模板

## ⚠️ 这是完整的业务模块迁移

本模板是从 ruoyi-vue-pro 完整迁移的会员管理模块（100%功能）。

## 模块说明

### 完整度：100%

**后端代码**（185个Java文件）：
- 会员用户管理
- 会员等级系统
- 会员积分系统
- 会员签到功能
- 会员地址管理
- 会员标签系统
- 会员分组管理
- 会员配置管理
- 会员认证功能
- API接口层
- 消息队列集成

**前端代码**（32个Vue文件）：
- 完整的管理后台页面
- 会员列表、详情、编辑
- 等级管理、积分管理
- 签到配置、地址管理
- 标签管理、分组管理

## 使用方式

### 方式 1: 完整拷贝（推荐用于新项目）

```bash
# 拷贝整个模块到你的项目
cp -r template-member-full/. your-project/src/main/java/com/yourcompany/member/

# 批量替换包名
find your-project/src/main/java/com/yourcompany/member -name "*.java" \
  -exec sed -i 's/com.yuheng.template.member/com.yourcompany.member/g' {} +

# 替换其他依赖的包名
find your-project/src/main/java/com/yourcompany/member -name "*.java" \
  -exec sed -i 's/com.yuheng.platform/your.platform.package/g' {} +
```

### 方式 2: 选择性使用

根据需要选择部分功能模块：
- 只要用户管理：拷贝 service/user, controller/admin/user
- 只要等级系统：拷贝 service/level, controller/admin/level
- 只要积分系统：拷贝 service/point, controller/admin/point

## 目录结构

```
template-member-full/
├── api/                          # API接口层（供其他模块调用）
│   ├── address/
│   ├── config/
│   ├── level/
│   ├── point/
│   ├── user/
│   └── ...
│
├── controller/                   # 控制器层
│   ├── admin/                    # 管理后台接口
│   │   ├── address/              # 地址管理
│   │   ├── config/               # 配置管理
│   │   ├── group/                # 分组管理
│   │   ├── level/                # 等级管理
│   │   ├── point/                # 积分管理
│   │   ├── signin/               # 签到管理
│   │   ├── tag/                  # 标签管理
│   │   └── user/                 # 用户管理
│   │
│   └── app/                      # 移动端/前台接口
│       ├── auth/                 # 认证接口
│       ├── config/               # 配置接口
│       ├── point/                # 积分接口
│       ├── signin/               # 签到接口
│       ├── social/               # 社交登录
│       └── user/                 # 用户接口
│
├── service/                      # 业务逻辑层
│   ├── address/                  # 地址服务
│   ├── auth/                     # 认证服务
│   ├── config/                   # 配置服务
│   ├── group/                    # 分组服务
│   ├── level/                    # 等级服务
│   ├── point/                    # 积分服务
│   ├── signin/                   # 签到服务
│   ├── tag/                      # 标签服务
│   └── user/                     # 用户服务
│
├── dal/                          # 数据访问层
│   ├── dataobject/               # 数据对象
│   │   ├── address/
│   │   ├── config/
│   │   ├── group/
│   │   ├── level/
│   │   ├── point/
│   │   ├── signin/
│   │   ├── tag/
│   │   └── user/
│   │
│   └── mysql/                    # Mapper接口
│       ├── address/
│       ├── config/
│       ├── group/
│       ├── level/
│       ├── point/
│       ├── signin/
│       ├── tag/
│       └── user/
│
├── convert/                      # 对象转换
│   ├── address/
│   ├── config/
│   ├── group/
│   ├── level/
│   ├── point/
│   ├── signin/
│   ├── tag/
│   └── user/
│
├── enums/                        # 枚举类
│   ├── DictTypeConstants.java
│   ├── ErrorCodeConstants.java
│   └── ...
│
├── mq/                           # 消息队列
│   ├── consumer/                 # 消费者
│   └── producer/                 # 生产者
│
├── framework/                    # 框架配置
│
└── ui/                           # 前端代码
    ├── views/member/             # 32个Vue页面
    └── api/member/               # API封装
```

## 功能清单

### 会员用户管理
- [x] 用户注册
- [x] 用户登录（密码、验证码、社交登录）
- [x] 用户信息管理
- [x] 用户状态管理
- [x] 用户搜索查询

### 会员等级系统
- [x] 等级配置
- [x] 等级经验值
- [x] 自动升级
- [x] 等级权益

### 会员积分系统
- [x] 积分获取
- [x] 积分消费
- [x] 积分记录
- [x] 积分规则配置
- [x] 积分签到

### 会员签到功能
- [x] 每日签到
- [x] 连续签到奖励
- [x] 签到规则配置
- [x] 签到记录

### 会员地址管理
- [x] 收货地址CRUD
- [x] 默认地址设置
- [x] 地址区域选择

### 会员标签系统
- [x] 标签创建管理
- [x] 用户打标签
- [x] 按标签筛选用户

### 会员分组管理
- [x] 分组创建管理
- [x] 用户分组
- [x] 按分组筛选用户

### 会员配置管理
- [x] 系统配置
- [x] 注册配置
- [x] 积分配置

## 依赖说明

### 平台依赖
本模块依赖 yuheng-core 的以下模块：
- `com.yuheng.platform.framework` - 框架层
- `com.yuheng.platform.module.system` - 系统模块（用户、权限）
- `com.yuheng.platform.module.infra` - 基础设施（文件、短信）

### 第三方依赖
- MyBatis Plus - 数据库操作
- Redis - 缓存
- RabbitMQ/Kafka - 消息队列（可选）

## 数据库设计

### 核心表
- `member_user` - 会员用户表
- `member_level` - 会员等级表
- `member_experience_record` - 等级经验记录表
- `member_point_record` - 积分记录表
- `member_point_config` - 积分配置表
- `member_sign_in_record` - 签到记录表
- `member_sign_in_config` - 签到配置表
- `member_address` - 收货地址表
- `member_tag` - 会员标签表
- `member_group` - 会员分组表
- `member_config` - 会员配置表

## API 接口

### 管理后台 API
- `POST /admin-api/member/user/create` - 创建用户
- `PUT /admin-api/member/user/update` - 更新用户
- `GET /admin-api/member/user/page` - 分页查询
- `DELETE /admin-api/member/user/delete` - 删除用户
- ... 100+ 个管理接口

### 移动端/前台 API
- `POST /app-api/member/auth/login` - 用户登录
- `POST /app-api/member/auth/register` - 用户注册
- `POST /app-api/member/auth/sms-login` - 短信登录
- `GET /app-api/member/user/get` - 获取用户信息
- `POST /app-api/member/point/sign-in` - 每日签到
- ... 50+ 个前台接口

## 注意事项

### 包名替换
务必将所有包名替换为你的项目包名：
- `com.yuheng.template.member` → `com.yourcompany.member`
- `com.yuheng.platform` → `your.platform.package`

### 数据库脚本
需要执行数据库初始化脚本（需要从 ruoyi-vue-pro 获取）

### 配置调整
根据实际情况调整：
- Redis配置
- 消息队列配置
- 短信服务配置
- 文件上传配置

## 与 template-member 的区别

| 特性 | template-member | template-member-full |
|-----|----------------|---------------------|
| 文件数 | 17个 | 231个 |
| 完整度 | 60% | 100% |
| 功能 | 基础CRUD | 完整业务 |
| 适用场景 | 快速起步 | 生产级项目 |
| 定制难度 | 容易 | 较难 |
| 代码量 | ~1,861行 | ~10,000+行 |

## 选择建议

### 使用 template-member（精简版）
- ✅ 快速原型开发
- ✅ 学习参考
- ✅ 简单会员系统
- ✅ 高度定制需求

### 使用 template-member-full（完整版）
- ✅ 生产级项目
- ✅ 功能完整性要求高
- ✅ 快速上线
- ✅ 标准业务场景

## 迁移来源

- **原始项目**: ruoyi-vue-pro
- **模块**: yudao-module-member
- **版本**: 2.3.0
- **迁移日期**: 2024-11-09
- **包名调整**: cn.iocoder.yudao → com.yuheng.template

## 相关资源

- template-member（精简版）: ../template-member/
- yuheng-core平台: https://github.com/your-org/yuheng-core
- 原始项目: https://gitee.com/zhijiantianya/ruoyi-vue-pro

---

**模板版本**: v1.0  
**完整度**: 100%  
**适用于**: yuheng-core v0.1.0+  
**最后更新**: 2024-11-09
