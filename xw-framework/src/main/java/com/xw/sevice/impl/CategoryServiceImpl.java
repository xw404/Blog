package com.xw.sevice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.constants.SystemConstants;
import com.xw.domain.ResponseResult;
import com.xw.domain.entity.Article;
import com.xw.domain.entity.Category;
import com.xw.domain.vo.CategoryVo;
import com.xw.mapper.CategoryMapper;
import com.xw.sevice.ArticleService;
import com.xw.sevice.CategoryService;
import com.xw.utils.BeanCopyUtils;
import kotlin.collections.UCollectionsKt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 * @author makejava
 * @since 2023-05-30 09:55:42
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    //分类列表接口
    //主要逻辑：分类列表要有对应的博文
    @Override
    public ResponseResult getCategoryList() {
        //查询文章列表，条件是状态已经发布
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);
        //获取文章的分类的id集合,并且去重复
        Set<Long> categoryIds = articleList
                .stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());
        //根据id查询有文章的分类列表
        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream()
                .filter(category ->
                        SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装返回
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);


        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public List<CategoryVo> listAllCategory() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus,SystemConstants.STATUS_NORMAL);
        List<Category> list = list(wrapper);
        List<CategoryVo> categoryVoList = BeanCopyUtils.copyBeanList(list, CategoryVo.class);
        return categoryVoList;
    }
}

