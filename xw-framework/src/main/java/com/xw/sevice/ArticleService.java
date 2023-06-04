package com.xw.sevice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.domain.ResponseResult;
import com.xw.domain.dto.AddArticleDto;
import com.xw.domain.entity.Article;

/**
 * @Author 小吴
 * @Date 2023/05/29 22:33
 * @Version 1.0
 */
public interface ArticleService extends IService<Article> {
    /*
    热门文章列表
     */
    ResponseResult hotArticleList();

    /*
    文章分页列表
     */
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    /*
    查询文章详情
     */
    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    //新增博文
    ResponseResult add(AddArticleDto article);
}
