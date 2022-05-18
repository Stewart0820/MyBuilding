package com.stewart.building.mbg.service.impl;

import com.stewart.building.mbg.pojo.Permission;
import com.stewart.building.mbg.mapper.PermissionMapper;
import com.stewart.building.mbg.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Stewart
 * @since 2022-05-04
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissionWidthRole() {
        return permissionMapper.getPermissionWidthRole();
    }
}
