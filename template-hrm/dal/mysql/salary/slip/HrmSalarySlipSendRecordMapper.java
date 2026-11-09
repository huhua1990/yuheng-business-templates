package com.yuheng.template.hrm.dal.mysql.salary.slip;

import com.yuheng.platform.framework.common.pojo.PageResult;
import com.yuheng.platform.framework.mybatis.core.mapper.BaseMapperX;
import com.yuheng.platform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.yuheng.template.hrm.controller.admin.salary.vo.slip.sendrecord.HrmSalarySlipSendRecordPageReqVO;
import com.yuheng.template.hrm.dal.dataobject.salary.slip.HrmSalarySlipSendRecordDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HrmSalarySlipSendRecordMapper extends BaseMapperX<HrmSalarySlipSendRecordDO> {

    default PageResult<HrmSalarySlipSendRecordDO> selectPage(HrmSalarySlipSendRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HrmSalarySlipSendRecordDO>()
                .eqIfPresent(HrmSalarySlipSendRecordDO::getYear, reqVO.getYear())
                .eqIfPresent(HrmSalarySlipSendRecordDO::getMonth, reqVO.getMonth())
                .orderByDesc(HrmSalarySlipSendRecordDO::getYear)
                .orderByDesc(HrmSalarySlipSendRecordDO::getMonth));
    }

}
