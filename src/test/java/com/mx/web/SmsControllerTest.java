package com.mx.web;

import com.mx.SpringRestDocApplication;
import org.junit.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;


import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.relaxedPathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SmsControllerTest extends SpringRestDocApplication {

    @Test
    public void getVerifyCodeTest() throws Exception{

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/sms/getVerifyCode/{telephone}","17710026695"))
                .andDo(print()).andExpect(status().isOk())
                .andDo(document("csp-getVerifyCode",
                        relaxedPathParameters(
                                parameterWithName("telephone").description("手机号")
                         ),
                        relaxedResponseFields(
                                fieldWithPath("data").description("验证码").type("String")
                        )
                        ));

    }
}
