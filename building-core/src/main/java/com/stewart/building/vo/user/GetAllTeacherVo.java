package com.stewart.building.vo.user;

import lombok.Data;


/**
 * @author 陈鸿杰
 * @create 2021/12/22
 * @Description
 */
@Data
public class GetAllTeacherVo {
    private Integer id;

    private String name;

    private String account;

    private String phone;


    private Integer gender;

    private Integer type;

    private String genderDetail;

    private String jobPosition;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
//        if (gender == GenderEnum.WOMAN.ordinal()) {
//            this.genderDetail = GenderEnum.MAN.getG();
//        } else {
//            this.genderDetail = GenderEnum.WOMAN.getG();
//        }
    }

    public void setType(Integer type) {
        this.type = type;
//        if (type == RoleDetailEnum.TEACHER.ordinal()) {
//            this.jobPosition = RoleDetailEnum.TEACHER.toString();
//        } else if (type == RoleDetailEnum.TEACHER_LAB.ordinal()) {
//            this.jobPosition = RoleDetailEnum.TEACHER_LAB.toString();
//        } else if (type == RoleDetailEnum.TEACHER_AND_LAB_TEACHER.ordinal()) {
//            this.jobPosition = RoleDetailEnum.TEACHER_AND_LAB_TEACHER.toString();
//        }
    }
}
