package com.stewart.building.common.renum;


/**
 * @author Stewart
 * @create 2021/12/19
 */
public enum  ReturnEnum {


    //base



    SELECT_SUCCESS("查询成功"),
    SELECT_ERROR("查询失败"),

    ADD_SUCCESS("添加成功"),
    ADD_ERROR("添加失败"),

    UPDATE_SUCCESS("修改成功"),
    UPDATE_ERROR("修改失败"),

    DELETE_SUCCESS("删除成功"),
    DELETE_ERROR("删除失败"),

    NEED_ID("请传入id"),
    ID_NOT_EXIST("传入的id不存在"),
    //实验模块
    EXPERIMENT_MIN_MAX_ERROR("最小数量必须少于最大数量");

    private String s;

    ReturnEnum() {
    }
    ReturnEnum(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
