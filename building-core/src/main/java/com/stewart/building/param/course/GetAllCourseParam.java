package com.stewart.building.param.course;


import com.stewart.building.param.BasePageParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Stewart
 * @create 2021/12/20
 */
@Data
public class GetAllCourseParam extends BasePageParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

}
