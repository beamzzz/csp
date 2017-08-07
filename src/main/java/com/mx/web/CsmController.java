package com.mx.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @aother zcl
 * @date 2017/8/7
 */
@RestController
@RequestMapping("/csp")
public class CsmController {

    @GetMapping("/getCsp")
    public String getCsp(){
        return "success";
    }
}
