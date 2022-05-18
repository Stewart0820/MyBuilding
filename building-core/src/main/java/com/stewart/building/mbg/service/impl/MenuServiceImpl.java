package com.stewart.building.mbg.service.impl;

import com.stewart.building.mbg.pojo.Menu;
import com.stewart.building.mbg.mapper.MenuMapper;
import com.stewart.building.mbg.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stewart.building.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Stewart
 * @since 2022-01-06
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;




    /**
     * 根据用户id查询菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenusByUserId() {
        return menuMapper.getMenusByUserId(UserUtil.getUserId());
    }

    /**
     * 查询所有的menu
     * @return
     */
    @Override
    public List<Menu> getAllMenu() {
        return menuMapper.getAllMenu();

    }
}
