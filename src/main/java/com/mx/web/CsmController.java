package com.mx.web;

import com.mx.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @aother zcl
 * @date 2017/8/7
 */
@RestController
@RequestMapping("/csp")
public class CsmController {

    Logger logger = LoggerFactory.getLogger(CsmController.class);

    @GetMapping("/getCsp")
    public String getCsp(){
        return "success";
    }

    @GetMapping("/getSession")
    public String getSession(HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        logger.info(session.getId());
        logger.info(user.getUserCode());
        return  session.getId();
    }
}
