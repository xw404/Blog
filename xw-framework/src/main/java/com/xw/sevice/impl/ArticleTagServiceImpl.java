package com.xw.sevice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.domain.entity.ArticleTag;
import com.xw.mapper.ArticleTagMapper;
import com.xw.sevice.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2023-06-03 22:06:24
 */
@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}

