package com.stewart.building.param;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Stewart
 * @create 2021/12/20
 */
@Data
public class BasePageParam {
    /**
     * 每页显示大小
     */
    @NotNull(message = "传入的每页大小不能为空")
    @Min(value = 1,message = "每页大小必须大于1")
    private Integer  size;

    /**
     * 当前页码
     */
    @NotNull(message = "传入的当前页不能为空")
    @Min(value = 1,message = "当前页码必须大于1")
    private  Integer current;
}
