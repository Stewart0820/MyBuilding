package com.stewart.building.mbg.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stewart.building.common.R;
import com.stewart.building.common.ResultStatus;
import com.stewart.building.mbg.pojo.Clazz;
import com.stewart.building.mbg.mapper.ClazzMapper;
import com.stewart.building.mbg.service.IClazzService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stewart.building.param.clazz.AddClazzParam;
import com.stewart.building.param.clazz.GetAllClazzParam;
import com.stewart.building.param.clazz.UpdateClazzParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Stewart
 * @since 2022-01-06
 */
@Service
public class                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements IClazzService {

    @Autowired
    private ClazzMapper clazzMapper;
    @Override
    public Page<Clazz> getAll(GetAllClazzParam param) {
        Page<Clazz> page = new Page<>(param.getCurrent(), param.getSize());
        Page<Clazz> res = clazzMapper.selectPage(page, new QueryWrapper<Clazz>()
                .eq("teacher_id", param.getTeacherId())
                .eq("course_id", param.getCourseId())
                .eq("semeter_id", param.getSemeterId())
                .like("name", param.getName()));
        return res;
    }

    /**
     * 修改班级
     * @param param
     * @return
     */
    @Override
    public R update(UpdateClazzParam param) {
        Integer count = clazzMapper.selectCount(new QueryWrapper<Clazz>().eq("id", param.getId()));
        if(count==0){
            return R.error(ResultStatus.ID_NOT_EXIST);
        }
        Clazz clazz = new Clazz();
        BeanUtils.copyProperties(param,clazz);
        clazz.setUpdateTime(String.valueOf(DateUtil.currentSeconds()));
        int res = clazzMapper.updateById(clazz);
        if(res==0){
            return R.error(ResultStatus.UPDATE_ERROR);
        }
        return R.ok(ResultStatus.UPDATE_SUCCESS);
    }

    /**
     * 添加班级
     * @param param
     * @return
     */
    @Override
    public int add(AddClazzParam param) {
        Clazz clazz = new Clazz();
        BeanUtils.copyProperties(param,clazz);
        clazz.setCreateTime(String.valueOf(DateUtil.currentSeconds()));
        return clazzMapper.insert(clazz);
    }
}
