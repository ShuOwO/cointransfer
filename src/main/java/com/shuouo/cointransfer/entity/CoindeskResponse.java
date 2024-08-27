package com.shuouo.cointransfer.entity;

import lombok.Data;

import java.util.Map;

@Data
public class CoindeskResponse {
    private Time time;
    private String disclaimer;
    private String chartName;
    private Map<String, Currency> bpi;

    @Data
    public static class Time {
        private String updated;
        private String updatedISO;
        private String updateduk;
    }

    @Data
    public static class Currency {
        private String code;
        private String symbol;
        private String rate;
        private String description;
        private float rate_float;
    }
}
