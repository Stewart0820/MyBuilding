package com.stewart.building.mbg.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stewart.building.common.R;
import com.stewart.building.common.ResultStatus;
import com.stewart.building.common.renum.SuccessEnum;
import com.stewart.building.mbg.mapper.LabExperimentMapper;
import com.stewart.building.mbg.pojo.Lab;
import com.stewart.building.mbg.mapper.LabMapper;
import com.stewart.building.mbg.pojo.LabExperiment;
import com.stewart.building.mbg.service.ILabService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stewart.building.param.lab.AddLabParam;
import com.stewart.building.param.lab.UpdateLabParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Stewart
 * @since 2022-01-06
 */
@Service
public class LabServiceImpl extends ServiceImpl<LabMapper, Lab> implements ILabService {
    private static final Logger logger = LoggerFactory.getLogger(LabServiceImpl.class);
    @Autowired
    private LabMapper labMapper;

    @Autowired
    private LabExperimentMapper labExperimentMapper;
    /**
     * 分页模糊查询所有的实验室
     * @param size
     * @param current
     * @param name
     * @return
     */
    @Override
    public Page<Lab> getAll(long size, long current, String name) {
        Page<Lab> page = new Page<>(current, size);
        return labMapper.selectPage(page,new QueryWrapper<Lab>().like("name",name).eq("status",0));
    }
    /**
     * 添加实验室
     * @param labAddLabParam
     * @return
     */
    @Override
    public int addLab(AddLabParam labAddLabParam) {
        Lab lab = new Lab();
        BeanUtils.copyProperties(labAddLabParam,lab);
        //获取当前时间戳,除掉毫秒
        lab.setCreateTime(String.valueOf(DateUtil.currentSeconds()));
        return labMapper.insert(lab);
    }

    /**
     * 根据id修改实验室信息
     * @param updateLabParam
     * @return
     */
    @Override
    public int updateLab(UpdateLabParam updateLabParam) {
        //判断该lab是否存在
        Lab flag = labMapper.selectById(updateLabParam.getId());
        if (flag==null){
            return SuccessEnum.ERROR_CODE.ordinal();
        }
        Lab lab = new Lab();
        BeanUtils.copyProperties(updateLabParam,lab);
        lab.setUpdateTime(String.valueOf(DateUtil.currentSeconds()));
        return labMapper.updateById(lab);
    }

    /**
     * 根据id获取单条实验室信息
     * @param id
     * @return
     */
    @Override
    public Lab getLabById(Integer id) {
        return labMapper.selectById(id);
    }

    /**
     * 根据id删除实验室
     * @param id
     * @return
     */
    @Transactional
    @Override
    public R deleteLabById(Integer id) {
        int res = labMapper.deleteById(id);
        int result = labExperimentMapper.delete(new QueryWrapper<LabExperiment>().eq("lab_id", id));
        if(res>0&&result>=0){
            return R.ok(ResultStatus.DELETE_SUCCESS);
        }
        return R.error(ResultStatus.DELETE_ERROR);
    }
}
