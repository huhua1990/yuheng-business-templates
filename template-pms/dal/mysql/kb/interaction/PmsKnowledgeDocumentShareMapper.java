package com.yuheng.template.pms.dal.mysql.kb.interaction;

import com.yuheng.platform.framework.mybatis.core.mapper.BaseMapperX;
import com.yuheng.platform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.yuheng.template.pms.dal.dataobject.kb.interaction.PmsKnowledgeDocumentShareDO;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

@Mapper
public interface PmsKnowledgeDocumentShareMapper extends BaseMapperX<PmsKnowledgeDocumentShareDO> {

    default PmsKnowledgeDocumentShareDO selectByDocumentId(Long documentId) {
        return selectOne(new LambdaQueryWrapperX<PmsKnowledgeDocumentShareDO>()
                .eq(PmsKnowledgeDocumentShareDO::getDocumentId, documentId));
    }

    default PmsKnowledgeDocumentShareDO selectByToken(String token) {
        return selectOne(new LambdaQueryWrapperX<PmsKnowledgeDocumentShareDO>()
                .eq(PmsKnowledgeDocumentShareDO::getToken, token));
    }

    default int updateToReopen(PmsKnowledgeDocumentShareDO updateObj) {
        return update(updateObj, new LambdaUpdateWrapper<PmsKnowledgeDocumentShareDO>()
                .set(PmsKnowledgeDocumentShareDO::getCloseUserId, null)
                .set(PmsKnowledgeDocumentShareDO::getCloseTime, null)
                .eq(PmsKnowledgeDocumentShareDO::getId, updateObj.getId()));
    }

    default void deleteByDocumentIds(Collection<Long> documentIds) {
        delete(new LambdaQueryWrapperX<PmsKnowledgeDocumentShareDO>()
                .in(PmsKnowledgeDocumentShareDO::getDocumentId, documentIds));
    }

}
