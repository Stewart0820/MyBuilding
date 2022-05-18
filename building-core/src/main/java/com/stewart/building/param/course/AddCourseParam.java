package com.stewart.building.param.course;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Stewart
 * @create 2021/12/20
 */
@Data
public class AddCourseParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "课程名称不能为空")
    private String name;

    private Integer year;

    @NotBlank(message = "课程名称不能为空")
    private String number;

    private String image;

    private Integer hour;
}
