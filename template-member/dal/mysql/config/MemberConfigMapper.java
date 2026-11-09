package com.yuheng.template.member.dal.mysql.config;

import com.yuheng.platform.framework.mybatis.core.mapper.BaseMapperX;
import com.yuheng.template.member.dal.dataobject.config.MemberConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 积分设置 Mapper
 *
 * @author QingX
 */
@Mapper
public interface MemberConfigMapper extends BaseMapperX<MemberConfigDO> {
}
