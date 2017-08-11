package com.mx.web;

import com.mx.domain.News;
import com.mx.domain.ReturnMessage;
import com.mx.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @aother zcl
 * @date 2017/8/10
 */
@RestController
@RequestMapping("/news")
public class newsController {

    @Autowired
    private NewsService newsService ;

    @RequestMapping("/getNews/{type}")
    public ReturnMessage getNews(@PathVariable("type") String type){
        List<News> news = newsService.getNews(type);
        System.out.println("获取新闻" + news);
        return new ReturnMessage("0000","获取成功",news);
    }
}
