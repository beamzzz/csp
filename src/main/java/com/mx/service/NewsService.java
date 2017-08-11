package com.mx.service;


import com.mx.domain.News;

import java.util.List;

/**
 * @aother zcl
 * @date 2017/8/11
 */
public interface NewsService {

    public List<News> getNews(String type);
}
