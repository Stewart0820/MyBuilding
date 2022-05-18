package com.stewart.building.mbg.service;

import com.stewart.building.mbg.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Stewart
 * @since 2022-01-06
 */
public interface IMenuService extends IService<Menu> {


    /**
     * 根据用户id查询菜单列表
     * @return
     */
    List<Menu> getMenusByUserId();

    /**
     * 查询所有的menu
     * @return
     */
    List<Menu> getAllMenu();
}
