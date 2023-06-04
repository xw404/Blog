package com.xw.controller;

import com.xw.domain.ResponseResult;
import com.xw.sevice.ArticleService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @Author 小吴
 * @Date 2023/05/29 22:37
 * @Version 1.0
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;
    /*
    热门文章
     */
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){

        ResponseResult result = articleService.hotArticleList();
        return result;
    }
    /*
    文章分页列表
     */
    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }
    /*
    查询文章详情
     */
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }


    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }


}
