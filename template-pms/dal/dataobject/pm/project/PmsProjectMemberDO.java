package com.yuheng.template.pms.dal.dataobject.pm.project;

import com.yuheng.platform.framework.mybatis.core.dataobject.BaseDO;
import com.yuheng.template.pms.enums.pm.project.PmsProjectMemberLevelEnum;
import com.yuheng.platform.module.system.dal.dataobject.user.AdminUserDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * PMS 项目成员 DO
 *
 * @author 芋道源码
 */
@TableName("pms_project_member")
@KeySequence("pms_project_member_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PmsProjectMemberDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 项目编号
     *
     * 关联 {@link PmsProjectDO#getId()}
     */
    private Long projectId;
    /**
     * 后台用户编号
     *
     * 关联 {@link AdminUserDO#getId()}
     */
    private Long userId;
    /**
     * 成员权限级别
     *
     * 枚举 {@link PmsProjectMemberLevelEnum}
     */
    private Integer level;

}
