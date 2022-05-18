package com.stewart.building.common;

/**
 * @ Created by Stewart on 2017/5/10 .
 * @ Description:自定义请求状态码
 */
public enum ResultStatus {
    /**
     * 500：失败
     * 200：成功
     */
    /**
     * info
     */
    NOT_LOGIN(401,"未登录，请登录"),
    NOT_PERMISSION(403,"权限不足，请联系管理员"),
    NOT_ID(500,"未传入id"),
    ID_NOT_EXIST(500,"该id不存在"),
    MIN_NOT_GREATER_MAX(500,"最小值不能大于最大值"),
    NEED_ID(500,"请传入id"),
    USER_EXIST(500,"该用户已经存在"),
    MOBILE_EXIST(500,"该电话已经存在"),
    EMAIL_EXIST(500,"该邮箱已经存在"),
    TEACHER_NOT_EXIST(500,"该用户不存在"),
    COURSE_NOT_EXIST(500,"该课程不存在"),
    SEMESTER_NOT_EXIST(500,"该学期不存在"),
    CLAZZ_ID_NOT_EXIST(500,"传入的班级id不存在"),
    FILE_IS_NULL(500,"传入的文件为空"),
    UPLOAD_ERROR(500,"上传失败"),

    /**
     * 成功信息
     */
    SUCCESS(200, "成功"),
    ADD_SUCCESS(200, "增加成功"),
    DELETE_SUCCESS(200, "删除成功"),
    UPDATE_SUCCESS(200, "更改成功"),
    SELECT_SUCCESS(200, "查询成功"),
    SINGUP_SUCCESS(200, "注册成功"),
    LOGIN_SUCCESS(200, "登录成功"),
    LOGOUT_SUCCESS(200, "退出成功"),
    GAIN_SUCCESS(200,"获取成功"),
    EMAIL_SEND_SUCCESS(200,"邮箱发送成功"),
    UPLOAD_SUCCESS(200,"上传文件成功"),


    /**
     * 失败
     */
    ERROR(500,"失败"),
    ADD_ERROR(500,"添加失败"),
    UPDATE_ERROR(500,"修改失败"),
    DELETE_ERROR(500,"删除失败"),
    SELECT_ERROR(500,"查询失败"),
    LOGIN_ERROR(500,"登录失败"),

    TYPE_ERROR(500,"类型转换错误"),
    ASSOCIATED_DATA_ERROR(500,"该数据有关联数据，操作失败！"),
    MYSQL_ERROR(500,"数据库异常，操作失败！"),
    BUSINESS_ERROR(500,"业务出现错误,请联系后端开发人员"),
    USERNAME_ERROR(500,"用户名错误或者密码错误"),
    EMAIL_FORMAT_ERROR(500,"邮箱格式错误"),
    EMAIL_SEND_ERROR(500,"邮箱发送失败"),
    ID_IS_NOT_TEACHER_ID(500,"传入的id不是老师id"),
    ;

    /**
     * 返回码
     */
    private int status;

    /**
     * 返回结果描述
     */
    private String msg;

    ResultStatus() {
    }

    ResultStatus(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}