package com.xw.job;

import com.xw.domain.entity.Article;
import com.xw.mapper.ArticleMapper;
import com.xw.sevice.ArticleService;
import com.xw.utils.RedisCache;
import javafx.scene.transform.MatrixType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author 小吴
 * @Date 2023/06/02 13:55
 * @Version 1.0
 */

@Component
public class updateViewCountJob {


    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleMapper articleMapper;

    @Scheduled(cron = "0/55 * * * * ?")
    public void updateViewCount(){
        //获取redis中的浏览量数据
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");
        //更新多条数据到数据库中
        List<Article> articles = viewCountMap.entrySet().stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey())
                        , entry.getValue().longValue()))
                .collect(Collectors.toList());
        //批量更新
        //注意：更新的时候，实体中没有传入的字段就不会更新，而是保持原状
        articleService.updateBatchById(articles);
    }
}
