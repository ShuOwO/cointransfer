package com.shuouo.cointransfer.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.google.gson.Gson;
import com.shuouo.cointransfer.domain.Currency;
import com.shuouo.cointransfer.domain.vo.CurrencyVo;
import com.shuouo.cointransfer.entity.CoindeskResponse;
import com.shuouo.cointransfer.entity.HttpResult;
import com.shuouo.cointransfer.mapper.CurrencyMapper;
import com.shuouo.cointransfer.service.ICurrencyService;
import com.shuouo.cointransfer.utils.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CurrencyServiceImpl implements ICurrencyService {
    private static final String COINDESK_API_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";
    private final CurrencyMapper currencyMapper;

    @Override
    public CoindeskResponse getCoindeskData() {
        HttpResult httpResult = HttpUtils.doGet(COINDESK_API_URL);
        if (httpResult.getStatus() == 200) {
            String jsonResponse = httpResult.getBody();
            Gson gson = new Gson();
            return gson.fromJson(jsonResponse, CoindeskResponse.class);
        } else {
            throw new RuntimeException("獲取 Coindesk API 錯誤. 錯誤碼: " + httpResult.getStatus());
        }
    }

    @Override
    public List<CurrencyVo> getCustomCoinInfo(){
        // 從 Coindesk API 獲取數據
        HttpResult httpResult = HttpUtils.doGet(COINDESK_API_URL);
        if (httpResult.getStatus() == 200) {
            String jsonResponse = httpResult.getBody();
            Gson gson = new Gson();
            CoindeskResponse coindeskResponse = gson.fromJson(jsonResponse, CoindeskResponse.class);

            // 將 API 數據轉換為幣別代碼列表
            List<String> currencyCodeList = transformApiDataToCurrencyCodeList(coindeskResponse);

            // 將數據轉換
            Map<String, Currency> customCoinInfo = mapToCoindeskResponse(coindeskResponse, currencyCodeList);

            // 遍歷並轉換數據格式
            return customCoinInfo.values().stream()
                    .map(currency -> BeanUtil.copyProperties(currency, CurrencyVo.class))
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("獲取 Coindesk API 錯誤. 錯誤碼: " + httpResult.getStatus());
        }
    }

    private List<String> transformApiDataToCurrencyCodeList(CoindeskResponse coindeskResponse) {
        // 從 Coindesk API 響應數據中動態提取幣別代碼
        return coindeskResponse.getBpi().keySet().stream().collect(Collectors.toList());
    }

    private Map<String, Currency> mapToCoindeskResponse(CoindeskResponse coindeskResponse, List<String> currencyCodeList) {
        // 遍歷所有的幣別代碼並設置相應的屬性
        Map<String, Currency> currencyInfoMap = new HashMap<>();

        for (String currencyCode : currencyCodeList) {
            // 從資料庫中獲取對應幣別代碼的資料
            List<Currency> currencyList = currencyMapper.findCurrencyByCurrencyCode(currencyCode);
            if (ObjectUtil.isNotEmpty(currencyList)) {
                for(Currency dbCurrency:currencyList){
                    // 使用 API 的數據更新資料庫 Currency 的匯率資料
                    String rateString = coindeskResponse.getBpi().get(currencyCode).getRate().replace(",", "");
                    dbCurrency.setExchangeRate(new BigDecimal(rateString));

                    // 將 Currency 對象放入 Map
                    currencyInfoMap.put(currencyCode, dbCurrency);
                }
            }
        }

        return currencyInfoMap;
    }
}
