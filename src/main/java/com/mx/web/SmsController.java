package com.mx.web;

import com.mx.domain.ReturnMessage;
import com.mx.service.SmsService;
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

    @Autowired
    private SmsService smsService;

    @GetMapping("/getVerifyCode/{telephone}")
    public ReturnMessage getVerifyCode(@PathVariable("telephone") String telephone){
        return null;
    }
}
