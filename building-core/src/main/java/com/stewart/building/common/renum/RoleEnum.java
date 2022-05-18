package com.stewart.building.common.renum;

/**
 * @author 陈鸿杰
 * @create 2021/12/22
 * @Description
 */
public enum RoleEnum {
    //角色模块
    ADMIN("ADMIN", 1),

    //任课老师
    TEACHER("TEACHER", 2),

    TEACHER_LAB("TEACHER_LAB", 3),

    //任课老师+实验老师
    TEACHER_LAB_TEACHER("TEACHER_LAB_TEACHER",4),

    //学生
    STUDENT("STUDENT", 5),

    //超级管理员
    ADMIN_SUPER("ADMIN_SUPER", 6);
    private String code;
    private Integer res;

    RoleEnum(String code, int res) {
        this.code = code;
        this.res = res;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getRes() {
        return res;
    }

    public void setRes(Integer res) {
        this.res = res;
    }
    /**
     * 通过枚举<code>code</code>获取枚举
     *
     * @param code
     * @return
     */
    public static Integer getEnumByCode(RoleDetailEnum code) {
        for (RoleEnum bt : RoleEnum.values()) {
            if (bt.getCode().equals(code)) {
                System.out.println(bt.getRes());
                return bt.getRes();
            }
        }
        return null;
    }
}
