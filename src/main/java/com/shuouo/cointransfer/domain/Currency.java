package com.shuouo.cointransfer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    private Long currencyId;

    private String currencyCode;

    private String currencyName;

    private BigDecimal exchangeRate;

    private Date createTime;

    private Date updateTime;
}
