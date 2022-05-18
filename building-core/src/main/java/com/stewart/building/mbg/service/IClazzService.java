package com.stewart.building.mbg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stewart.building.common.R;
import com.stewart.building.mbg.pojo.Clazz;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stewart.building.param.clazz.AddClazzParam;
import com.stewart.building.param.clazz.GetAllClazzParam;
import com.stewart.building.param.clazz.UpdateClazzParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Stewart
 * @since 2022-01-06
 */
public interface IClazzService extends IService<Clazz> {

    /**
     * 分页模糊查询所有的班级
     * @param param
     * @return
     */
    Page<Clazz> getAll(GetAllClazzParam param);

    /**
     * 添加班级
     * @param param
     * @return
     */
    int add(AddClazzParam param);

    /**
     * 修改班级
     * @param param
     * @return
     */
    R update(UpdateClazzParam param);
}
