package com.stewart.building.mbg.controller.course;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stewart.building.common.R;
import com.stewart.building.common.ResultStatus;
import com.stewart.building.mbg.pojo.Course;
import com.stewart.building.mbg.service.ICourseService;
import com.stewart.building.param.course.AddCourseParam;
import com.stewart.building.param.course.GetAllCourseParam;
import com.stewart.building.param.course.UpdateCourseParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Stewart
 * @since 2022-01-06
 */
@Api(tags = "课程模块")
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @ApiOperation(value = "分页查询所有课程")
    @PostMapping("/getAll")
    public R getAll(@Valid @RequestBody GetAllCourseParam param){
        Page<Course> result = courseService.getAll(param);
        return R.ok(ResultStatus.SELECT_SUCCESS,result);
    }

    @ApiOperation(value = "添加课程")
    @PostMapping("/add")
    public R add(@Valid @RequestBody AddCourseParam param){
        int res = courseService.add(param);
        if(res==0){
            return R.error(ResultStatus.ADD_ERROR);
        }
        return R.ok(ResultStatus.ADD_SUCCESS);
    }


    @ApiOperation(value = "修改课程")
    @PostMapping("/update")
    public R updateCourse(@Valid @RequestBody UpdateCourseParam param){
        return courseService.updateCourse(param);
    }

    @ApiOperation(value = "根据id查询课程")
    @GetMapping("/getCourseById/{id}")
    public R getCourseById(@PathVariable Integer id){
        Course course = courseService.getById(id);
        if(course==null){
            return R.error(ResultStatus.ID_NOT_EXIST);
        }
        return R.ok(ResultStatus.SELECT_SUCCESS,course);
    }

    @ApiOperation(value = "根据id删除课程")
    @GetMapping("/deleteCourseById/{id}")
    public R deleteCourseById(@PathVariable Integer id){
        return courseService.deleteCourseById(id);
    }



}
