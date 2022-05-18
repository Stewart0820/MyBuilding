package com.stewart.building.mbg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stewart.building.common.R;
import com.stewart.building.vo.Menus;
import com.stewart.building.mbg.pojo.Role;
import com.stewart.building.mbg.pojo.User;
import com.stewart.building.mbg.pojo.UserVo;
import com.stewart.building.param.user.teacher.AddTeacherParam;
import com.stewart.building.param.user.teacher.GetAllTeacherByPageParam;
import com.stewart.building.param.user.teacher.UpdateTeacherParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChenHongjie
 * @since 2021-12-15
 */
public interface IUserService extends IService<User> {
    /**
     * 登录之后返回token
     * @param account
     * @param password
     * @param request
     * @return
     */
    R login(String account, String password, javax.servlet.http.HttpServletRequest request);



    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    List<Role> getRoles(Integer userId);




    /**
     * 根据用户账号获取用户信息
     * @param account
     * @return
     */
    User getUserByUsername(String account);

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    User getInfo(Integer userId);


    /**
     *分页/模糊 查询所有的老师
     * @return
     * @param teacherByPageParam
     */
    Page<UserVo> getAll(GetAllTeacherByPageParam teacherByPageParam);


    /**
     * 添加老师
     * @param addTeacherParam
     * @return
     */
    R addTeacher(AddTeacherParam addTeacherParam);




    /**
     * 修改老师
     * @param param
     * @return
     */
    R updateTeacher(UpdateTeacherParam param);

    /**
     * 批量添加学生
     *
     * @param clazzId
     * @param datas
     * @return
     */
    boolean batchInsert(int clazzId, List<Object> datas);

    /**
     * 根据用户id查询单条老师数据
     * @param id
     * @return
     */
    R getTeacherById(Integer id);


    /**
     * 根据角色id查询menus
     * @param roleId
     * @return
     */
    List<Menus> getMenuByRoleId(Integer roleId);
}
