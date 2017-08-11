package com.mx.web;

import com.mx.SpringRestDocApplication;
import org.junit.Test;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SmsControllerTest extends SpringRestDocApplication {

    @Test
    public void getVerifyCodeTest() throws Exception{
        this.mockMvc.perform(get("/sms/getVerifyCode/17710026695"))
                .andDo(print()).andExpect(status().isOk())
                .andDo(document("csp-getVerifyCode",
                        relaxedResponseFields(
                                fieldWithPath("data").description("验证码").type("String")
                        )
                        ));

    }
}
