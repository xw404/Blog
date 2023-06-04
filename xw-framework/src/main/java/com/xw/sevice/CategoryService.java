package com.xw.sevice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.domain.ResponseResult;
import com.xw.domain.entity.Category;
import com.xw.domain.vo.CategoryVo;

import java.util.List;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-05-30 09:55:41
 */
public interface CategoryService extends IService<Category> {

    /*
    分类列表接口
     */
    ResponseResult getCategoryList();

    List<CategoryVo> listAllCategory();
}
