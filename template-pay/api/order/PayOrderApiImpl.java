package com.yuheng.template.pay.api.order;

import com.yuheng.template.pay.api.order.dto.PayOrderCreateReqDTO;
import com.yuheng.template.pay.api.order.dto.PayOrderRespDTO;
import com.yuheng.template.pay.convert.order.PayOrderConvert;
import com.yuheng.template.pay.dal.dataobject.order.PayOrderDO;
import com.yuheng.template.pay.service.order.PayOrderService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * 支付单 API 实现类
 *
 * @author 芋道源码
 */
@Service
public class PayOrderApiImpl implements PayOrderApi {

    @Resource
    private PayOrderService payOrderService;

    @Override
    public Long createOrder(PayOrderCreateReqDTO reqDTO) {
        return payOrderService.createOrder(reqDTO);
    }

    @Override
    public PayOrderRespDTO getOrder(Long id) {
        PayOrderDO order = payOrderService.getOrder(id);
        return PayOrderConvert.INSTANCE.convert2(order);
    }

    @Override
    public void closeOrder(Long id) {
        payOrderService.closeOrder(id);
    }

    @Override
    public void updatePayOrderPrice(Long id, Integer payPrice) {
        payOrderService.updatePayOrderPrice(id, payPrice);
    }

}
