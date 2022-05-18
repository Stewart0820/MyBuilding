package com.stewart.building.util;

import com.stewart.building.mbg.pojo.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Stewart
 * @create 2021/12/16
 */
public class UserUtil {
    public static Integer getUserId(){
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
