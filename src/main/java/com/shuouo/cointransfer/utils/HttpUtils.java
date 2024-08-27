package com.shuouo.cointransfer.utils;

import cn.hutool.http.HttpUtil;
import com.shuouo.cointransfer.entity.HttpResult;

public class HttpUtils {
    /**
     * GET 请求，编码格式默认 UTF-8
     *
     * @param url 请求 URL
     * @return HttpResult
     */
    public static HttpResult doGet(String url) {
        HttpResult result = new HttpResult();
        try {
            // 使用 Hutool 發送 GET 請求並獲取響應內容
            String body = HttpUtil.get(url);
            result.setStatus(200); // Hutool 自動處理狀態碼，這裡假設成功
            result.setBody(body);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(500); // 如果出現異常，設置狀態碼為 500
        } // 這邊簡單用Hutool處理，複雜請況可以使用apache.http工具獲取狀態碼做客製化處理
        return result;
    }
}
