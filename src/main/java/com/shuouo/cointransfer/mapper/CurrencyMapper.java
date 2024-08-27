package com.shuouo.cointransfer.mapper;

import com.shuouo.cointransfer.domain.Currency;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CurrencyMapper {
    List<Currency> findAll();

    List<Currency> findCurrencyByCurrencyCode(@Param("currencyCode") String currencyCode);
}
