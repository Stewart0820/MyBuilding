package com.stewart.building.mbg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stewart.building.common.R;
import com.stewart.building.mbg.pojo.Experiment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stewart.building.param.experiment.AddExperimentParam;
import com.stewart.building.param.experiment.GetAllExperimentParam;
import com.stewart.building.param.experiment.UpdateExperimentParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Stewart
 * @since 2022-01-06
 */
public interface IExperimentService extends IService<Experiment> {

    /**
     * 分页/模糊 查询所有的实验
     * @param getAllExperiment
     * @return
     */
    Page<Experiment> getAll(GetAllExperimentParam getAllExperiment);

    /**
     * 添加实验
     * @param addExperimentParam
     * @return
     */
    int addExperiment(AddExperimentParam addExperimentParam);

    /**
     * 修改实验
     * @param updateExperimentParam
     * @return
     */
    int updateExperiment(UpdateExperimentParam updateExperimentParam);


    /**
     * 根据id查询实验
     * @param id
     * @return
     */
    Experiment getExperimentById(Integer id);

    /**
     * 根据实验id删除实验
     * @param id
     * @return
     */
    R deleteExperimentById(Integer id);
}
