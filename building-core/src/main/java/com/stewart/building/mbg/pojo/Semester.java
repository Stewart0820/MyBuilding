package com.stewart.building.mbg.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Stewart
 * @since 2022-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Semester对象", description="")
public class Semester implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学期id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("create_time")
    private String createTime;

    @TableField("update_time")
    private String updateTime;

    private String name;

    @TableField("start_date")
    private String startDate;

    @TableField("end_date")
    private String endDate;

    private Integer status;

    private String description;

    private String email;


}
