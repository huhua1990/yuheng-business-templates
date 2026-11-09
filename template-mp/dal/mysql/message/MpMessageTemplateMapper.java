package com.yuheng.template.mp.dal.mysql.message;

import com.yuheng.platform.framework.mybatis.core.mapper.BaseMapperX;
import com.yuheng.template.mp.controller.admin.message.vo.template.MpMessageTemplateListReqVO;
import com.yuheng.template.mp.dal.dataobject.message.MpMessageTemplateDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MpMessageTemplateMapper extends BaseMapperX<MpMessageTemplateDO> {

    default List<MpMessageTemplateDO> selectList(MpMessageTemplateListReqVO listReqVO) {
        return selectList(MpMessageTemplateDO::getAccountId, listReqVO.getAccountId());
    }

}