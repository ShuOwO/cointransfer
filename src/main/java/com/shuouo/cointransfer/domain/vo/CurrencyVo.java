package com.shuouo.cointransfer.domain.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CurrencyVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long currencyId;

    private String currencyCode;

    private String currencyName;

    private BigDecimal exchangeRate;

    private Date createTime;

    private Date updateTime;
}
