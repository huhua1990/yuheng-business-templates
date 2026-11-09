package com.yuheng.template.pay.convert.wallet;

import com.yuheng.platform.framework.common.pojo.PageResult;
import com.yuheng.platform.framework.common.util.collection.CollectionUtils;
import com.yuheng.platform.framework.common.util.collection.MapUtils;
import com.yuheng.platform.framework.common.util.object.BeanUtils;
import com.yuheng.platform.framework.dict.core.DictFrameworkUtils;
import com.yuheng.template.pay.controller.app.wallet.vo.recharge.AppPayWalletRechargeCreateRespVO;
import com.yuheng.template.pay.controller.app.wallet.vo.recharge.AppPayWalletRechargeRespVO;
import com.yuheng.template.pay.dal.dataobject.order.PayOrderDO;
import com.yuheng.template.pay.dal.dataobject.wallet.PayWalletRechargeDO;
import com.yuheng.template.pay.enums.DictTypeConstants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

@Mapper
public interface PayWalletRechargeConvert {

    PayWalletRechargeConvert INSTANCE = Mappers.getMapper(PayWalletRechargeConvert.class);

    @Mapping(target = "totalPrice", expression = "java( payPrice + bonusPrice)")
    PayWalletRechargeDO convert(Long walletId, Integer payPrice, Integer bonusPrice, Long packageId);

    AppPayWalletRechargeCreateRespVO convert(PayWalletRechargeDO bean);

    default PageResult<AppPayWalletRechargeRespVO> convertPage(PageResult<PayWalletRechargeDO> pageResult,
                                                               List<PayOrderDO> payOrderList) {
        PageResult<AppPayWalletRechargeRespVO> voPageResult = BeanUtils.toBean(pageResult, AppPayWalletRechargeRespVO.class);
        Map<Long, PayOrderDO> payOrderMap = CollectionUtils.convertMap(payOrderList, PayOrderDO::getId);
        voPageResult.getList().forEach(recharge -> {
            recharge.setPayChannelName(DictFrameworkUtils.parseDictDataLabel(
                    DictTypeConstants.CHANNEL_CODE, recharge.getPayChannelCode()));
            MapUtils.findAndThen(payOrderMap, recharge.getPayOrderId(),
                    order -> recharge.setPayOrderChannelOrderNo(order.getChannelOrderNo()));
        });
        return voPageResult;
    }

}
