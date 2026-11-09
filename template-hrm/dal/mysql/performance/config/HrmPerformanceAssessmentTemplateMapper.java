package com.yuheng.template.hrm.dal.mysql.performance.config;

import com.yuheng.platform.framework.common.enums.CommonStatusEnum;
import com.yuheng.platform.framework.common.pojo.PageResult;
import com.yuheng.platform.framework.mybatis.core.mapper.BaseMapperX;
import com.yuheng.platform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.yuheng.template.hrm.controller.admin.performance.vo.assessmenttemplate.HrmPerformanceAssessmentTemplatePageReqVO;
import com.yuheng.template.hrm.dal.dataobject.performance.config.HrmPerformanceAssessmentTemplateDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HrmPerformanceAssessmentTemplateMapper extends BaseMapperX<HrmPerformanceAssessmentTemplateDO> {

    default PageResult<HrmPerformanceAssessmentTemplateDO> selectPage(
            HrmPerformanceAssessmentTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HrmPerformanceAssessmentTemplateDO>()
                .likeIfPresent(HrmPerformanceAssessmentTemplateDO::getName, reqVO.getName())
                .eq(HrmPerformanceAssessmentTemplateDO::getStatus, CommonStatusEnum.ENABLE.getStatus())
                .orderByDesc(HrmPerformanceAssessmentTemplateDO::getUpdateTime)
                .orderByDesc(HrmPerformanceAssessmentTemplateDO::getId));
    }

    default HrmPerformanceAssessmentTemplateDO selectByName(String name) {
        return selectFirstOne(HrmPerformanceAssessmentTemplateDO::getName, name,
                HrmPerformanceAssessmentTemplateDO::getStatus, CommonStatusEnum.ENABLE.getStatus());
    }

    default List<HrmPerformanceAssessmentTemplateDO> selectListByStatus(Integer status) {
        return selectList(new LambdaQueryWrapperX<HrmPerformanceAssessmentTemplateDO>()
                .eq(HrmPerformanceAssessmentTemplateDO::getStatus, status)
                .orderByDesc(HrmPerformanceAssessmentTemplateDO::getUpdateTime)
                .orderByDesc(HrmPerformanceAssessmentTemplateDO::getId));
    }

}
