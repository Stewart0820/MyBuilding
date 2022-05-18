package com.stewart.building.config.security;

import com.stewart.building.mbg.pojo.Menu;
import com.stewart.building.mbg.pojo.Permission;
import com.stewart.building.mbg.pojo.Role;
import com.stewart.building.mbg.service.IMenuService;
import com.stewart.building.mbg.service.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.security.access.SecurityConfig;

import java.util.Collection;
import java.util.List;


/**
 * 权限控制
 * 根据请求的url分析所需要的角色
 *
 * @author Stewart
 * @create 2021/12/16
 */
@Slf4j
@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private RedisTemplate redisTemplate;


    AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final String PERMISSION_LIST = "permission-list";

    /**
     * 白名单的访问不会走这里
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取请求的url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // 第一种
//        List<Permission> permissionList = permissionService.getPermissionWidthRole();

        // 第二种
        //redis中是否有permission
        Boolean key = redisTemplate.hasKey(PERMISSION_LIST);
        List<Permission> permissionList = null;

        if (key) {
            //直接去redis中
            permissionList = (List<Permission>) redisTemplate.opsForValue().get(PERMISSION_LIST);
        } else {
            //去数据库中获取所有的url
            permissionList = permissionService.getPermissionWidthRole();
            redisTemplate.opsForValue().set(PERMISSION_LIST, permissionList);
        }


        for (Permission permission : permissionList) {
            // 判断请求url与菜单里面的url是否匹配
            if (antPathMatcher.match(permission.getUrl(), requestUrl)) {
                //根据url获取roles
                String[] str = permission.getRoles().stream().map(Role::getName).toArray(String[]::new);
                // 把获取的roles存到security中
                return SecurityConfig.createList(str);
            }
        }
        // 只要登录成功，默认给的角色 ROLE_LOGIN
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
