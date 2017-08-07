package com.mx.service.impl;

import com.mx.service.SmsService;
import com.mx.util.HttpUtils;
import com.mx.util.MathUtil;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @aother zcl
 * @date 2017/8/7
 */
@Service
public class SmsServiceImpl implements SmsService {

    //保存验证码缓存
    private Map<String,Integer> verifyCodecatch =  new HashMap<String,Integer>();

    /**
     * 获取验证码
     * 调用阿里云短信服务
     *
     * @auther : beam
     * @date : 2017/8/7
     **/
    @Override
    public String getVerifyCode(String telephone) {

        String host = "http://sms.market.alicloudapi.com";
        String path = "/singleSendSms";
        String method = "GET";
        String appcode = "88851740ef51415c993d4d36c10f3542";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        //生成一个6位随机数，并缓存
        int random = MathUtil.getRandom(6);
        verifyCodecatch.put(telephone,random);

        querys.put("ParamString", "{\"verifyCode\":\" + random + \"}");
        querys.put("RecNum", telephone);
        querys.put("SignName", "毛线");
        querys.put("TemplateCode", "SMS_82505001");

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
