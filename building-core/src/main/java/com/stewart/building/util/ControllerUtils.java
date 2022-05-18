package com.stewart.building.util;

import com.stewart.building.common.R;
import com.stewart.building.common.ResultStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Stewart
 * @create 2022/1/11
 * @Description
 */
public class ControllerUtils {
    /**
     * controller层删除的通用方法
     * @param id 删除的id
     * @param i
     * @return
     */
    public static R getR(@PathVariable Integer id, Boolean i) {
        if (StringUtils.isEmpty(id)) {
            return R.error(ResultStatus.NOT_ID);
        }
        boolean res = i;
        if(!res){
            return R.error(ResultStatus.ID_NOT_EXIST);
        }
        return R.ok(ResultStatus.DELETE_SUCCESS);
    }
}
