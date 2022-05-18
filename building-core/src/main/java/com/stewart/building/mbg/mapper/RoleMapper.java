package com.stewart.building.mbg.mapper;

import com.stewart.building.mbg.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Stewart
 * @since 2022-01-06
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    List<Role> getRoles(@Param("userId") Integer userId);
}
