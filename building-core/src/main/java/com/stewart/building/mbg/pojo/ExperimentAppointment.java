package com.stewart.building.mbg.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("experiment_appointment")
@ApiModel(value="ExperimentAppointment对象", description="")
public class ExperimentAppointment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "实验预约")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("create_time")
    private String createTime;

    @TableField("update_time")
    private String updateTime;

    @ApiModelProperty(value = "0：待审批，1：通过，2：拒绝，3：过期")
    private Integer status;

    @TableField("clazz_id")
    private Integer clazzId;

    @TableField("experiment_id")
    private Integer experimentId;

    @TableField("timz_id")
    private Integer timzId;

    @ApiModelProperty(value = "任课老师")
    @TableField("teacher_id")
    private Integer teacherId;

    @ApiModelProperty(value = "年月日")
    private String times;

    @ApiModelProperty(value = "实验老师")
    @TableField("exp_teacher_id")
    private Integer expTeacherId;


}
