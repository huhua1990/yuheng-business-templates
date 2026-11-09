package com.yuheng.template.pms.controller.admin.kb.interaction.vo.favorite;

import com.yuheng.platform.framework.common.pojo.PageParam;
import com.yuheng.platform.framework.common.validation.InEnum;
import com.yuheng.template.pms.enums.kb.PmsKnowledgeObjectTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - PMS 知识收藏（关注）分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class PmsKnowledgeFavoritePageReqVO extends PageParam {

    @Schema(description = "对象类型", example = "3")
    @InEnum(value = PmsKnowledgeObjectTypeEnum.class, message = "对象类型必须是 {value}")
    private Integer type;

}
