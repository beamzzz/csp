package com.mx.service.impl;

import com.mx.exception.MxException;
import com.mx.service.SmsService;
import com.mx.util.HttpUtils;
import com.mx.util.MathUtil;
import com.sun.javafx.jmx.MXExtension;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @aother zcl
 * @date 2017/8/7
 */
@Service
public class SmsServiceImpl implements SmsService {

    private Logger log = LoggerFactory.getLogger(SmsServiceImpl.class);

    //保存验证码缓存
    private Map<String,Integer> verifyCodecache =  new HashMap<String,Integer>();

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
        verifyCodecache.put(telephone,random);

        querys.put("ParamString", "{\"verifyCode\":\"" + random + "\"}");
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

            //获取response的body
            String result = EntityUtils.toString(response.getEntity());
            JSONObject json = JSONObject.fromObject(result);
            if(json.getBoolean("success") == true){
                return random + "";
            }else{
              throw new MxException(json.getString("message"));
            }
        } catch(MxException e){
            throw e;
        }catch (Exception e) {
            log.error("获取验证码失败",e);
            throw new MxException("获取验证码失败");
        }
    }

    @Override
    public boolean checkVerifyCode(String telephone, String verifyCode) {
        if(verifyCodecache.get(telephone) == null){
            return false;
        }
        if(verifyCodecache.get(telephone).equals(verifyCode)){
            return true;
        }
        return false;
    }
}
