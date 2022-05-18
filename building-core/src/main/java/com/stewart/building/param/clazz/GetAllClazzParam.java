package com.stewart.building.param.clazz;

import com.stewart.building.param.BasePageParam;
import lombok.Data;

/**
 * @author 陈鸿杰
 * @create 2021/12/24
 * @Description
 */
@Data
public class GetAllClazzParam extends BasePageParam {

    private Integer courseId;

    private Integer teacherId;

    private Integer semeterId;

    private String name;
}
