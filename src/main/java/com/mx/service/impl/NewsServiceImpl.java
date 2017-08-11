package com.mx.service.impl;

import com.mx.domain.News;
import com.mx.exception.ServiceException;
import com.mx.service.NewsService;
import com.mx.util.HttpUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @aother zcl
 * @date 2017/8/11
 */
@Service
public class NewsServiceImpl implements NewsService{

    private Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Override
    public List<News> getNews(String type) {

        List<News> list = new ArrayList<News>();
        String host = "http://toutiao-ali.juheapi.com";
        String path = "/toutiao/index";
        String method = "GET";
        String appcode = "88851740ef51415c993d4d36c10f3542";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("type", type);

        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //获取response的body
            String result = EntityUtils.toString(response.getEntity());
            JSONArray json = JSONObject.fromObject(result).getJSONObject("result").getJSONArray("data");

            for(int i = 0;i<json.size();i++){
                JSONObject obj = (JSONObject) json.get(i);
                News news = (News) JSONObject.toBean(obj,News.class);
                list.add(news);
            }
            return list;
        } catch (Exception e) {
            logger.error("获取新闻失败",e);
            throw new ServiceException("获取新闻列表失败");
        }
    }
}
