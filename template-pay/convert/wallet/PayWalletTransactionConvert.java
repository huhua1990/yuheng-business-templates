package com.yuheng.template.pay.convert.wallet;

import com.yuheng.platform.framework.common.pojo.PageResult;
import com.yuheng.template.pay.controller.admin.wallet.vo.transaction.PayWalletTransactionRespVO;
import com.yuheng.template.pay.controller.app.wallet.vo.transaction.AppPayWalletTransactionRespVO;
import com.yuheng.template.pay.dal.dataobject.wallet.PayWalletTransactionDO;
import com.yuheng.template.pay.service.wallet.bo.WalletTransactionCreateReqBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayWalletTransactionConvert {

    PayWalletTransactionConvert INSTANCE = Mappers.getMapper(PayWalletTransactionConvert.class);

    PageResult<PayWalletTransactionRespVO> convertPage2(PageResult<PayWalletTransactionDO> page);

    PayWalletTransactionDO convert(WalletTransactionCreateReqBO bean);

}
