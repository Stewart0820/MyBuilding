package com.stewart.building.mbg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stewart.building.common.R;
import com.stewart.building.mbg.pojo.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stewart.building.param.course.AddCourseParam;
import com.stewart.building.param.course.GetAllCourseParam;
import com.stewart.building.param.course.UpdateCourseParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Stewart
 * @since 2022-01-06
 */
public interface ICourseService extends IService<Course> {

    /**
     * 分页查询所有的课程
     * @param param
     * @return
     */
    Page<Course> getAll(GetAllCourseParam param);

    /**
     * 添加课程
     * @param param
     * @return
     */
    int add(AddCourseParam param);

    /**
     * 修改课程
     * @return
     * @param param
     */
    R updateCourse(UpdateCourseParam param);

    /**
     * 根据id删除课程
     * @param id
     * @return
     */
    R deleteCourseById(Integer id);
}
