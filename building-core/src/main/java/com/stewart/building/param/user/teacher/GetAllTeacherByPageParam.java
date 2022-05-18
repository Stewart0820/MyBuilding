package com.stewart.building.param.user.teacher;


import com.stewart.building.param.BasePageParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 陈鸿杰
 * @create 2021/12/22
 * @Description
 */
@Data
public class GetAllTeacherByPageParam extends BasePageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String account;
}
