package com.xw.sevice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.domain.ResponseResult;
import com.xw.domain.dto.TagListDto;
import com.xw.domain.entity.Tag;
import com.xw.domain.vo.PageVo;
import com.xw.domain.vo.TagVo;
import com.xw.mapper.TagMapper;
import com.xw.sevice.TagService;
import com.xw.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.awt.image.BandCombineOp;
import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-06-02 16:41:01
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    //条件查询标签
    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum,
                                              Integer pageSize,
                                              TagListDto tagListDto) {
        //分页查询
        LambdaQueryWrapper<Tag>  queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(tagListDto.getRemark()),
                Tag::getRemark,tagListDto.getRemark());
        queryWrapper.like(StringUtils.hasText(tagListDto.getName()),
                Tag::getName,tagListDto.getName());

        IPage<Tag> page = new Page<>(pageNum,pageSize);
        page = page(page,queryWrapper);
        List<Tag> records = page.getRecords();
        //封装返回
        PageVo pageVo = new PageVo();
        pageVo.setRows(records);
        pageVo.setCount(page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public List<TagVo> listAllTag() {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Tag::getId,Tag::getName);
        List<Tag> list = list(wrapper);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(list, TagVo.class);
        return tagVos;
    }
}

