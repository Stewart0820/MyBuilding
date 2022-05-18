package com.stewart.building.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Stewart
 * @create 2021/11/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageR {

    /**
     * 每页显示大小
     */
    private long  size;

    /**
     * 当前页码
     */
    private  long current;

    /**
     * 最大页数
     */
    private  long maxCurrent;

    /**
     * 数据总条数
     */
    private  long total;
}
