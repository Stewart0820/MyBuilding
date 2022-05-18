package com.stewart.building.mbg.controller.clazz;


import com.stewart.building.common.R;
import com.stewart.building.common.ResultStatus;
import com.stewart.building.mbg.pojo.Clazz;
import com.stewart.building.mbg.pojo.Course;
import com.stewart.building.mbg.pojo.Semester;
import com.stewart.building.mbg.pojo.User;
import com.stewart.building.mbg.service.IClazzService;
import com.stewart.building.mbg.service.ICourseService;
import com.stewart.building.mbg.service.ISemesterService;
import com.stewart.building.mbg.service.IUserService;
import com.stewart.building.param.clazz.AddClazzParam;
import com.stewart.building.param.clazz.GetAllClazzParam;
import com.stewart.building.param.clazz.UpdateClazzParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Stewart
 * @since 2022-01-06
 */
@Api(tags = "班级模块")
@RestController
@RequestMapping("/clazz")
public class ClazzController {

    @Autowired
    private IClazzService clazzService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ISemesterService semesterService;

    @Autowired
    private ICourseService courseService;
    @ApiOperation(value = "查询所有的班级")
    @PostMapping("/getAll")
    public R getAll(@Valid @RequestBody GetAllClazzParam param) {
        return R.ok(ResultStatus.SELECT_SUCCESS, clazzService.getAll(param));
    }

    @ApiOperation(value = "添加班级")
    @PostMapping("/add")
    public R add(@Valid @RequestBody AddClazzParam param) {
        R r = judgeParameter(param.getTeacherId(), param.getCourseId(), param.getSemeterId());
        if(r!=null){
            return r;
        }
        int res = clazzService.add(param);
        if (res == 0) {
            return R.error(ResultStatus.ADD_ERROR);
        }
        return R.ok(ResultStatus.ADD_SUCCESS);
    }

    /**
     * 判断传入的三个参数是否存在
     * @param teacherId
     * @param courseId
     * @param semesterId
     * @return
     */
    private R judgeParameter(Integer teacherId, Integer courseId, Integer semesterId){
        User user = userService.getById(teacherId);
        if(user==null){
            return R.error(ResultStatus.TEACHER_NOT_EXIST);
        }
        Course course = courseService.getById(courseId);
        if(course==null){
            return R.error(ResultStatus.COURSE_NOT_EXIST);
        }
        Semester semester = semesterService.getById(semesterId);
        if (semester==null){
            return R.error(ResultStatus.SEMESTER_NOT_EXIST);
        }
        return null;
    }


    @ApiOperation(value = "修改班级")
    @PostMapping("/update")
    public R update(@Valid @RequestBody UpdateClazzParam param) {
        R r = judgeParameter(param.getTeacherId(), param.getCourseId(), param.getSemeterId());
        if(r!=null){
            return r;
        }
        return clazzService.update(param);
    }

    @ApiOperation(value = "根据传入的id查询对应的班级")
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable Integer id){
        if(StringUtils.isEmpty(id)){
            return R.error(ResultStatus.NEED_ID);
        }
        Clazz res = clazzService.getById(id);
        if(res==null){
            return R.error(ResultStatus.ID_NOT_EXIST);
        }
        return R.ok(ResultStatus.SELECT_SUCCESS,res);
    }

}
