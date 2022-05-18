package com.stewart.building.common.renum;

/**
 * @author 陈鸿杰
 * @create 2021/12/23
 * @Description
 */
public enum GenderEnum {
    //性别
    WOMAN(0,"男"),
    MAN(1,"女");

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    GenderEnum(Integer gender, String g) {
        this.gender = gender;
        this.g = g;
    }

    private Integer gender;
    private String g;

}
