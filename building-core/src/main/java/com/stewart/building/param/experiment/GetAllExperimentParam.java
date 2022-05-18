package com.stewart.building.param.experiment;


import com.stewart.building.param.BasePageParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Stewart
 * @create 2021/12/20
 */
@Data
public class GetAllExperimentParam extends BasePageParam implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 允许为空进行模糊查询
     */
    private String name;

}
