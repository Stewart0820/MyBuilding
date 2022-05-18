package com.stewart.building.common.renum;

/**
 * @author Stewart
 * @create 2021/12/19
 *
 * 这个文件不能修改！！
 * 别的地方使用ordinal来获取它的地址，0 and 1
 */
public enum  SuccessEnum {

    //0为增加，修改的失败
    ERROR_CODE(0),
    //1为成功
    SUCCESS_CODE(1),
    //2:为
    ID_NOT_EXIST(2);
    private Integer code;

    SuccessEnum(int i) {

    }
}
