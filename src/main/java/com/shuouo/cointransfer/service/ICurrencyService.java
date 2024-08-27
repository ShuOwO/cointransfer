package com.shuouo.cointransfer.service;

import com.shuouo.cointransfer.domain.vo.CurrencyVo;
import com.shuouo.cointransfer.entity.CoindeskResponse;
import com.shuouo.cointransfer.entity.HttpResult;

import java.util.List;

public interface ICurrencyService {

    CoindeskResponse getCoindeskData();

    List<CurrencyVo> getCustomCoinInfo();
}
