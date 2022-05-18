package com.stewart.building.param.clazz;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 陈鸿杰
 * @create 2022/1/8
 * @Description
 */
@Data
public class UpdateClazzParam {

    @NotNull(message = "id不能为空")
    private Integer id;

    @NotNull(message = "课程的id不能为空")
    private Integer courseId;

    @NotNull(message = "老师的id不能为空")
    private Integer teacherId;

    private String description;

    @NotBlank(message = "班级名称不能为空")
    private String name;

    @NotNull(message = "学期的id不能为空")
    private Integer semeterId;
}
