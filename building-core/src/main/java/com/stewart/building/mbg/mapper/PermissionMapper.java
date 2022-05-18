package com.stewart.building.mbg.mapper;

import com.stewart.building.mbg.pojo.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Stewart
 * @since 2022-05-04
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> getPermissionWidthRole();
}
