package com.stewart.building.param.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Stewart
 * @create 2021/12/15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "AdminLogin对象",description = "")
public class UserLoginParam {
    @ApiModelProperty(value = "账号",required = true)
    private String account;
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
