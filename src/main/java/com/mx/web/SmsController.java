package com.mx.web;

import com.mx.domain.ReturnMessage;
import com.mx.exception.MxException;
import com.mx.service.SmsService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @aother zcl
 * @date 2017/8/7
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    private Logger log = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    private SmsService smsService;

    @GetMapping("/getVerifyCode/{telephone}")
    public ReturnMessage getVerifyCode(@PathVariable("telephone") String telephone){

        try{
            String verifyCode = smsService.getVerifyCode(telephone);
            log.info("手机号" + telephone + "获取验证码成功," + verifyCode);
            return new ReturnMessage("0000","获取验证码成功");
        }catch(MxException e){
            return new ReturnMessage("9999",e.getMessage());
        }
    }

    @GetMapping("/checkVerifyCode/{telephone}/{verifyCode}")
    public ReturnMessage checkVerifyCode(@PathVariable("telephone") String telephone,
                                         @PathVariable("verifyCode") String verifyCode){
        log.info("手机号" + telephone + "验证码校验," + verifyCode);
        Boolean result = smsService.checkVerifyCode(telephone,verifyCode);
        return new ReturnMessage("0000",result);
    }
}
