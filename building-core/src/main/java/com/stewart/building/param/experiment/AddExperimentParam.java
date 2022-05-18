package com.stewart.building.param.experiment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Stewart
 * @create 2021/12/20
 */
@Data
public class AddExperimentParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "最大数量不能为空")
    private Integer maxStuNum;

    @NotNull(message = "最小数量不能为空")
    private Integer minStuNum;

    @NotBlank(message = "实验名称不能为空")
    private String name;

    private String expLink;

    private String description;

    private Integer capacity;
}
