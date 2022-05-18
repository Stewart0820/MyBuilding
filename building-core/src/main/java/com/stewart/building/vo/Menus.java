package com.stewart.building.vo;

import com.stewart.building.mbg.pojo.Meta;
import lombok.Data;

import java.util.List;

/**
 * @author Stewart
 * @create 2022/2/27
 * @Description
 */
@Data
public class Menus {
    private String path;

    private String name;

    private String component;

    private Meta meta;

    private List<Menus> children;
}
