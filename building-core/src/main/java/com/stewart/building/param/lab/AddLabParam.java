package com.stewart.building.param.lab;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Stewart
 * @create 2021/12/19
 */
@Data
public class AddLabParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "实验室名称不能为空")
    private String name;

    @NotBlank(message = "实验室描述不能为空")
    private String description;

    @NotNull(message = "实验室容量不能为空")
    @Min(0)
    private Integer capacity;

    @Min(0)
    @Max(1)
    private Integer status;
}
