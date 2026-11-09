package com.yuheng.template.oa.service.vehicle.listener;

import com.yuheng.template.oa.service.vehicle.OaVehicleReturnService;

import cn.iocoder.yudao.module.bpm.api.event.BpmProcessInstanceStatusEvent;
import com.yuheng.template.oa.enums.BpmModelConstants;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import cn.iocoder.yudao.module.bpm.api.event.BpmProcessInstanceStatusEventListener;

/**
 * 还车申请审批结果监听器
 *
 * @author 芋道源码
 */
@Component
public class OaVehicleReturnStatusListener extends BpmProcessInstanceStatusEventListener {

    @Resource
    private OaVehicleReturnService vehicleReturnService;

    @Override
    protected String getProcessDefinitionKey() {
        return BpmModelConstants.VEHICLE_RETURN;
    }

    @Override
    protected void onEvent(BpmProcessInstanceStatusEvent event) {
        vehicleReturnService.updateVehicleReturnStatus(Long.parseLong(event.getBusinessKey()), event.getStatus());
    }

}
