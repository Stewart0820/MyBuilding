package com.stewart.building.mbg.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stewart.building.mbg.pojo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈鸿杰
 * @create 2021/12/23
 * @Description
 */
public interface UserVoMapper extends BaseMapper<UserVo> {

    /**
     * 批量添加学生
     * @param list
     * @return
     */
    int batchInsert(List<UserVo> list);

}


