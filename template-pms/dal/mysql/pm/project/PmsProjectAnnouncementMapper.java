package com.yuheng.template.pms.dal.mysql.pm.project;

import com.yuheng.platform.framework.mybatis.core.mapper.BaseMapperX;
import com.yuheng.platform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.yuheng.template.pms.dal.dataobject.pm.project.PmsProjectAnnouncementDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PmsProjectAnnouncementMapper extends BaseMapperX<PmsProjectAnnouncementDO> {

    default List<PmsProjectAnnouncementDO> selectListByProjectId(Long projectId) {
        return selectList(new LambdaQueryWrapperX<PmsProjectAnnouncementDO>()
                .eq(PmsProjectAnnouncementDO::getProjectId, projectId)
                .orderByDesc(PmsProjectAnnouncementDO::getCreateTime)
                .orderByDesc(PmsProjectAnnouncementDO::getId));
    }

    default void deleteByProjectId(Long projectId) {
        delete(new LambdaQueryWrapperX<PmsProjectAnnouncementDO>()
                .eq(PmsProjectAnnouncementDO::getProjectId, projectId));
    }

}
