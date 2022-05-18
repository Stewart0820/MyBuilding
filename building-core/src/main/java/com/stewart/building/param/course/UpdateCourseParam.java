package com.stewart.building.param.course;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Stewart
 * @create 2021/12/20
 */
@Data
public class UpdateCourseParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空")
    @Min(1)
    private Integer id;

    @NotBlank(message = "课程名称不能为空")
    private String name;

    @NotBlank(message = "课程名称不能为空")
    private String number;

    private Integer year;

    private String image;

    private Integer hour;
}
