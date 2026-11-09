package com.yuheng.template.pay.convert.wallet;

import com.yuheng.platform.framework.common.pojo.PageResult;
import com.yuheng.template.pay.controller.admin.wallet.vo.wallet.PayWalletRespVO;
import com.yuheng.template.pay.controller.app.wallet.vo.wallet.AppPayWalletRespVO;
import com.yuheng.template.pay.dal.dataobject.wallet.PayWalletDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayWalletConvert {

    PayWalletConvert INSTANCE = Mappers.getMapper(PayWalletConvert.class);

    AppPayWalletRespVO convert(PayWalletDO bean);

    PayWalletRespVO convert02(PayWalletDO bean);

    PageResult<PayWalletRespVO> convertPage(PageResult<PayWalletDO> page);

}
