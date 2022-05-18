package com.stewart.building.param.semester;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author 陈鸿杰
 * @create 2021/12/24
 * @Description
 */
@Data
public class AddSemesterParam implements Serializable {

    @NotBlank(message = "学期的名称不能为空")
    private String name;

    @NotBlank(message = "学期开始时间不能为空")
    private String startDate;

    @NotBlank(message = "学期结束时间不能为空")
    private String endDate;

    private String description;
}
