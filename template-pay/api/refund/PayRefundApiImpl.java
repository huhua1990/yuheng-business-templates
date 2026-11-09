package com.yuheng.template.pay.api.refund;

import com.yuheng.platform.framework.common.util.object.BeanUtils;
import com.yuheng.template.pay.api.refund.dto.PayRefundCreateReqDTO;
import com.yuheng.template.pay.api.refund.dto.PayRefundRespDTO;
import com.yuheng.template.pay.dal.dataobject.refund.PayRefundDO;
import com.yuheng.template.pay.service.refund.PayRefundService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 退款单 API 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PayRefundApiImpl implements PayRefundApi {

    @Resource
    private PayRefundService payRefundService;

    @Override
    public Long createRefund(PayRefundCreateReqDTO reqDTO) {
        return payRefundService.createRefund(reqDTO);
    }

    @Override
    public PayRefundRespDTO getRefund(Long id) {
        PayRefundDO refund = payRefundService.getRefund(id);
        return BeanUtils.toBean(refund, PayRefundRespDTO.class);
    }

}
