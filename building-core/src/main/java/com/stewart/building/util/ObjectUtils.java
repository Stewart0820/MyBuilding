package com.stewart.building.util;

import cn.hutool.core.date.DateUtil;

/**
 * @author Stewart
 * @create 2021/12/20
 */
public class ObjectUtils {
    /**
     * 获取当前时间段时间戳
     * @return
     */
    public  static String CurrentTime(){
        return String.valueOf(DateUtil.currentSeconds());
    }
}
