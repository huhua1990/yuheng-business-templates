package com.yuheng.template.hrm.dal.mysql.attendance.config;

import com.yuheng.platform.framework.common.pojo.PageResult;
import com.yuheng.platform.framework.mybatis.core.mapper.BaseMapperX;
import com.yuheng.platform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.yuheng.template.hrm.controller.admin.attendance.vo.group.HrmAttendanceGroupPageReqVO;
import com.yuheng.template.hrm.dal.dataobject.attendance.config.HrmAttendanceGroupDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HrmAttendanceGroupMapper extends BaseMapperX<HrmAttendanceGroupDO> {

    default PageResult<HrmAttendanceGroupDO> selectPage(HrmAttendanceGroupPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HrmAttendanceGroupDO>()
                .likeIfPresent(HrmAttendanceGroupDO::getName, reqVO.getName())
                .eqIfPresent(HrmAttendanceGroupDO::getDefaultStatus, reqVO.getDefaultStatus())
                .orderByDesc(HrmAttendanceGroupDO::getId));
    }

    default HrmAttendanceGroupDO selectByName(String name) {
        return selectFirstOne(HrmAttendanceGroupDO::getName, name);
    }

    default List<HrmAttendanceGroupDO> selectListOrderByDefaultStatusAndId() {
        return selectList(new LambdaQueryWrapperX<HrmAttendanceGroupDO>()
                .orderByDesc(HrmAttendanceGroupDO::getDefaultStatus)
                .orderByDesc(HrmAttendanceGroupDO::getId));
    }

}
