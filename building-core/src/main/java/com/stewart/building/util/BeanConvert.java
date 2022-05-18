package com.stewart.building.util;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author Stewart
 * @create 2021/12/15
 */
public class BeanConvert {

    /**
     * List<User> =>List<UserDto>
     * @param input 输入集合 List<User>
     * @param clzz  输出集合类型 UserDto.class
     * @param <E>   输入集合类型
     * @param <T>   输出集合类型
     * @return 返回集合
     */
    public static <E, T> List<T> convertList2List(List<E> input, Class<T> clzz) {
        List<T> output = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(input)) {
            for (E source : input) {
                T target = BeanUtils.instantiate(clzz);
                BeanUtils.copyProperties(source, target);
                output.add(target);
            }
        }
        return output;
    }
}
