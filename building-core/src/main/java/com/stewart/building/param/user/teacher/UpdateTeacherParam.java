package com.stewart.building.param.user.teacher;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * @author 陈鸿杰
 * @create 2021/12/23
 * @Description
 */
@Data
@Accessors(chain = true)
public class UpdateTeacherParam  implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空")
    private Integer id;

    @NotBlank(message = "名称不能为空")
    private String name;

    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$",message = "请输入正确的邮箱账号")
    private String email;

    @NotBlank(message = "电话不能为空")
    @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$",message = "请传入正确的电话号码")
    private String mobile;

    @Min(0)
    @Max(1)
    private Integer gender;

    @Min(1)
    @Max(3)
    @ApiModelProperty("1:任课老师2：实验老师3：任课老师和实验老师")
    private Integer type;
}
