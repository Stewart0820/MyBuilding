package com.stewart.building.common.renum;

/**
 * @author 陈鸿杰
 * @create 2021/12/22
 * @Description
 */
public enum RoleDetailEnum {

    //角色模块
    ADMIN("ADMIN", "管理员"),

    TEACHER("TEACHER", "任课老师"),

    TEACHER_LAB("TEACHER_LAB", "实验老师"),

    TEACHER_AND_LAB_TEACHER("Teacher_AND_LAB_TEACHER", "任课老师和实验老师"),

    STUDENT("STUDENT", "学生"),

    ADMIN_SUPER("ADMIN_SUPER", "超级管理员");
    private String res;

    RoleDetailEnum(String admin, String res) {
        this.res = res;
    }

    public String getRole(Integer index) {
        return this.res;
    }


}
