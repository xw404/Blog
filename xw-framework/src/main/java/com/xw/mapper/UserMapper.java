package com.xw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.domain.entity.User;
import org.springframework.stereotype.Repository;


/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-30 14:47:42
 */
@Repository(value = "userMapper")
public interface UserMapper extends BaseMapper<User> {

}
