package com.stewart.building.mbg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stewart.building.common.R;
import com.stewart.building.mbg.pojo.Lab;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stewart.building.param.lab.AddLabParam;
import com.stewart.building.param.lab.UpdateLabParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Stewart
 * @since 2022-01-06
 */
public interface ILabService extends IService<Lab> {
    /**
     * 分页查询所有的实验室
     * @param size
     * @param current
     * @param name
     * @return
     */
    Page<Lab> getAll(long size, long current, String name);
    /**
     * 添加实验室
     * @param labAddLabParam
     * @return
     */
    int addLab(AddLabParam labAddLabParam);

    /**
     * 修改实验室
     * @param updateLabParam
     * @return
     */
    int updateLab(UpdateLabParam updateLabParam);

    /**
     * 根据id查询一条实验室信息
     * @param id
     * @return
     */
    Lab getLabById(Integer id);

    /**
     * 根据id删除实验室
     * @param id
     * @return
     */
    R deleteLabById(Integer id);
}
