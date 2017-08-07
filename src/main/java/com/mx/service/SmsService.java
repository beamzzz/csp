package com.mx.service;

/**
 * @aother zcl
 * @date 2017/8/7
 */
public interface SmsService {

    /**
     * 获取验证码
     * 调用阿里云短信服务
     * @auther : beam
     * @date : 2017/8/7
     **/
    public String getVerifyCode(String telephone);
}
