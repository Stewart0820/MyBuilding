package com.stewart.building.mbg.service;

import com.stewart.building.mbg.pojo.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Stewart
 * @since 2022-05-04
 */
public interface IPermissionService extends IService<Permission> {

    /**
     * 获取所有的权限
     * @return
     */
    List<Permission> getPermissionWidthRole();
}
