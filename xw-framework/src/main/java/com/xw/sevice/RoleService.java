package com.xw.sevice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 * @author makejava
 * @since 2023-06-03 10:24:40
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}
