package com.stewart.building.mbg.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ChenHongjie
 * @since 2021-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
@TableName(value = "user")
public class UserVo implements Serializable  {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    @ApiModelProperty(value = "账号")
    private String account;

    private String password;

    private String mobile;

    @ApiModelProperty(value = "0 false FEMALE 1 true  MALE")
    private Integer gender;

    @TableField("create_time")
    private String createTime;

    @TableField("update_time")
    private String updateTime;

    @ApiModelProperty(value = "0:admin,1:teacher,2:student")
    private Integer type;

    @TableField("email")
    private String email;

}
