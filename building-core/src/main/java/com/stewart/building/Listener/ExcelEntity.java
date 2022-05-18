package com.stewart.building.Listener;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author 陈鸿杰
 * @create 2022/1/8
 * @Description
 */
@Data
public class ExcelEntity extends BaseRowModel {
    @ExcelProperty(index = 0, value = "学号")
    private String account;
    @ExcelProperty(index = 1, value = "姓名")
    private String name;
    @ExcelProperty(index = 2, value = "电话")
    private String mobile;
    @ExcelProperty(index = 3, value = "性别")
    private String gender;
    @ExcelProperty(index = 4, value = "邮箱")
    private String email;

}
