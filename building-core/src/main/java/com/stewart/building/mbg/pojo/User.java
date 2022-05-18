package com.stewart.building.mbg.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.stewart.building.vo.Menus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class User implements Serializable , UserDetails {

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

    @ApiModelProperty(value = "角色")
    @TableField(exist = false)
    private List<Role> roles;

    @ApiModelProperty(value = "权限列表")
    @TableField(exist = false)
    private List<Menus> menus;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //将存在这个Admin对象中的roles取出name放在security中
        //对security设置用户的角色
        List<SimpleGrantedAuthority> authorities = roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getUsername() {
        return account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
