package com.stewart.building.param.lab;


import com.stewart.building.param.BasePageParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Stewart
 * @create 2021/12/19
 */
@Data
public class GetAllLabPageParam extends BasePageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 实验名称
     */
    private String name;
}
