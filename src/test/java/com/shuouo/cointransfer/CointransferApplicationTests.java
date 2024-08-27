package com.shuouo.cointransfer;

import cn.hutool.core.util.ObjectUtil;
import com.shuouo.cointransfer.domain.Currency;
import com.shuouo.cointransfer.domain.vo.CurrencyVo;
import com.shuouo.cointransfer.mapper.CurrencyMapper;
import com.shuouo.cointransfer.service.ICurrencyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CointransferApplicationTests {
    @Autowired
    private ICurrencyService currencyService;
    @Autowired
    private CurrencyMapper currencyMapper;

    @Test
    @DisplayName("測試獲取Coindesk返回數據")
    void testGetCoindeskData() {
        System.out.println(currencyService.getCoindeskData().toString());
    }

    @Test
    @DisplayName("測試獲取所有currency數據(DB)")
    void testGetAllCurrency(){
        List<Currency> myCurrencyList = currencyMapper.findAll();
        if(ObjectUtil.isNotEmpty(myCurrencyList)){
            myCurrencyList.forEach(System.out::println);
        }
    }

    // 增, 刪, 改 同理，依此類推，編寫sql語句

    @Test
    @DisplayName("測試獲取Coindesk數據，進行資料轉換並返回")
    void testGetCustomCoinInfo(){
        List<CurrencyVo> currencyVoList = currencyService.getCustomCoinInfo();
        if(ObjectUtil.isNotEmpty(currencyVoList)){
            currencyVoList.forEach(System.out::println);
        }
    }
}
