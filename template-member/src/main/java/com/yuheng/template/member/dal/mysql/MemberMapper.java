package com.yuheng.template.member.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.yuheng.template.member.dal.dataobject.MemberDO;
import com.yuheng.template.member.vo.MemberPageReqVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员 Mapper
 *
 * @author Yuheng
 */
@Mapper
public interface MemberMapper extends BaseMapperX<MemberDO> {

    default PageResult<MemberDO> selectPage(MemberPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MemberDO>()
                .likeIfPresent(MemberDO::getNickname, reqVO.getNickname())
                .eqIfPresent(MemberDO::getMobile, reqVO.getMobile())
                .eqIfPresent(MemberDO::getLevelId, reqVO.getLevelId())
                .eqIfPresent(MemberDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(MemberDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MemberDO::getId));
    }

    default MemberDO selectByMobile(String mobile) {
        return selectOne(MemberDO::getMobile, mobile);
    }

    default MemberDO selectByUserId(Long userId) {
        return selectOne(MemberDO::getUserId, userId);
    }

}
