package com.xw.runner;

import com.xw.domain.entity.Article;
import com.xw.mapper.ArticleMapper;
import com.xw.sevice.ArticleService;
import com.xw.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author 小吴
 * @Date 2023/06/02 13:21
 * @Version 1.0
 */

@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询数据库博客信息  id和viewCount
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(),
                        article -> article.getViewCount().intValue()));
        //存储到reids中
        redisCache.setCacheMap("article:viewCount",viewCountMap);

    }
}
