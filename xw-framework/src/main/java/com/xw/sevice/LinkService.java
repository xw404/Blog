package com.xw.sevice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.domain.ResponseResult;
import com.xw.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-05-30 14:22:40
 */
public interface LinkService extends IService<Link> {

    /*
    查询所有审核通过的友链
     */
    ResponseResult getAllLink();
}
