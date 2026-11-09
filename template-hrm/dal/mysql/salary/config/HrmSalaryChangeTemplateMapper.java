package com.yuheng.template.hrm.dal.mysql.salary.config;

import com.yuheng.platform.framework.mybatis.core.mapper.BaseMapperX;
import com.yuheng.platform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.yuheng.template.hrm.dal.dataobject.salary.config.HrmSalaryChangeTemplateDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HrmSalaryChangeTemplateMapper extends BaseMapperX<HrmSalaryChangeTemplateDO> {

    default List<HrmSalaryChangeTemplateDO> selectListByIdDesc() {
        return selectList(new LambdaQueryWrapperX<HrmSalaryChangeTemplateDO>()
                .orderByDesc(HrmSalaryChangeTemplateDO::getId));
    }

    default List<HrmSalaryChangeTemplateDO> selectListByDefaultStatus(Boolean defaultStatus) {
        return selectList(HrmSalaryChangeTemplateDO::getDefaultStatus, defaultStatus);
    }

}
