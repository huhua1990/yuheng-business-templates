package com.yuheng.template.pms.dal.mysql.pm.project;

import com.yuheng.platform.framework.common.pojo.PageResult;
import com.yuheng.platform.framework.mybatis.core.mapper.BaseMapperX;
import com.yuheng.platform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.yuheng.template.pms.controller.admin.pm.project.vo.template.PmsProjectTemplatePageReqVO;
import com.yuheng.template.pms.dal.dataobject.pm.project.PmsProjectTemplateDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PmsProjectTemplateMapper extends BaseMapperX<PmsProjectTemplateDO> {

    default PageResult<PmsProjectTemplateDO> selectPage(PmsProjectTemplatePageReqVO pageReqVO) {
        return selectPage(pageReqVO, new LambdaQueryWrapperX<PmsProjectTemplateDO>()
                .likeIfPresent(PmsProjectTemplateDO::getName, pageReqVO.getName())
                .eqIfPresent(PmsProjectTemplateDO::getProjectType, pageReqVO.getProjectType())
                .eqIfPresent(PmsProjectTemplateDO::getStatus, pageReqVO.getStatus())
                .orderByAsc(PmsProjectTemplateDO::getSort)
                .orderByDesc(PmsProjectTemplateDO::getId));
    }

    default PmsProjectTemplateDO selectByNameAndProjectType(String name, Integer projectType) {
        return selectOne(new LambdaQueryWrapperX<PmsProjectTemplateDO>()
                .eq(PmsProjectTemplateDO::getName, name)
                .eq(PmsProjectTemplateDO::getProjectType, projectType));
    }

}
