package com.mx.web;

import com.mx.SpringRestDocApplication;
import org.junit.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @aother zcl
 * @date 2017/8/11
 */
public class NewsControllerTest extends SpringRestDocApplication {

    @Test
    public void getNewsTest() throws Exception{

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("type", "keji");

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/news/getNews/{type}","yule"))
                .andDo(print()).andExpect(status().isOk())
                .andDo(document("csp-getNews",
                        pathParameters(
                                parameterWithName("type").description("类型,,top(头条，默认),shehui(社会),guonei(国内)," +
                                        "guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("data[0].title").description("标题").type("String"),
                                fieldWithPath("data[0].date").description("时间").type("String"),
                                fieldWithPath("data[0].author_name").description("作者").type("String"),
                                fieldWithPath("data[0].thumbnail_pic_s").description("图片").type("String"),
                                fieldWithPath("data[0].thumbnail_pic_s02").description("图片2").type("String"),
                                fieldWithPath("data[0].thumbnail_pic_s03").description("图片3").type("String"),
                                fieldWithPath("data[0].url").description("新闻连接").type("String"),
                                fieldWithPath("data[0].uniquekey").description("唯一标识").type("String"),
                                fieldWithPath("data[0].type").description("类型").type("String"),
                                fieldWithPath("data[0].realtype").description("类型二").type("String")
                        )
                ));

    }
}
