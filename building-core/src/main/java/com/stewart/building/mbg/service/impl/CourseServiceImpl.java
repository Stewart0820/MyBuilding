package com.stewart.building.mbg.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stewart.building.common.R;
import com.stewart.building.common.ResultStatus;
import com.stewart.building.mbg.mapper.CourseExperimentMapper;
import com.stewart.building.mbg.pojo.Course;
import com.stewart.building.mbg.mapper.CourseMapper;
import com.stewart.building.mbg.pojo.CourseExperiment;
import com.stewart.building.mbg.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stewart.building.param.course.AddCourseParam;
import com.stewart.building.param.course.GetAllCourseParam;
import com.stewart.building.param.course.UpdateCourseParam;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseExperimentMapper courseExperimentMapper;
    /**
     * 分页查询所有的课程
     * @param param
     * @return
     */
    @Override
    public Page<Course> getAll(GetAllCourseParam param) {
        Page<Course> page = new Page<>(param.getCurrent(), param.getSize());
        return courseMapper.selectPage(page,new QueryWrapper<Course>().like("name",param.getName()));
    }

    /**
     * 添加课程
     * @param param
     * @return
     */
    @Override
    public int add(AddCourseParam param) {
        Course course = new Course();
        BeanUtils.copyProperties(param,course);
        course.setCreateTime(String.valueOf(DateUtil.currentSeconds()));
        return courseMapper.insert(course);
    }

    /**
     * 修改课程
     * @return
     * @param param
     */
    @Override
    public R updateCourse(UpdateCourseParam param) {
        Course course = courseMapper.selectById(param.getId());
        if(course==null){
            return R.error(ResultStatus.ID_NOT_EXIST);
        }
        Course c = new Course();
        BeanUtils.copyProperties(param,c);
        c.setUpdateTime(String.valueOf(DateUtil.currentSeconds()));
        int i = courseMapper.updateById(c);
        if(i>0){
            return R.ok(ResultStatus.UPDATE_SUCCESS);
        }else {
            return R.error(ResultStatus.UPDATE_ERROR);
        }
    }

    /**
     * 根据id删除课程
     * @param id
     * @return
     */
    @Transactional
    @Override
    public R deleteCourseById(Integer id) {
        int res = courseMapper.deleteById(id);
        int result = courseExperimentMapper.delete(new QueryWrapper<CourseExperiment>().eq("course_id", id));
        if(res>0&&result>=0){
            return R.ok(ResultStatus.DELETE_SUCCESS);
        }
        return R.error(ResultStatus.DELETE_ERROR);
    }









}
