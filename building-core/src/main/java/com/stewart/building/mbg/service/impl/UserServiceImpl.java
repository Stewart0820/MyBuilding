package com.stewart.building.mbg.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stewart.building.common.R;
import com.stewart.building.common.ResultStatus;
import com.stewart.building.common.renum.RoleDetailEnum;
import com.stewart.building.common.renum.RoleEnum;
import com.stewart.building.config.component.JwtTokenUtil;
import com.stewart.building.mbg.mapper.*;
import com.stewart.building.mbg.pojo.*;
import com.stewart.building.mbg.service.IUserService;
import com.stewart.building.param.user.teacher.AddTeacherParam;
import com.stewart.building.param.user.teacher.GetAllTeacherByPageParam;
import com.stewart.building.param.user.teacher.UpdateTeacherParam;
import com.stewart.building.util.BeanConvert;
import com.stewart.building.util.IpUtil;
import com.stewart.building.util.ObjectUtils;
import com.stewart.building.util.UserUtil;
import com.stewart.building.vo.Menus;
import com.stewart.building.vo.user.GetAllTeacherVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ChenHongjie
 * @since 2021-12-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Value("${user.password}")
    private String userPassword;

    @Autowired
    private UserLogMapper userLogMapper;

    @Autowired
    private UserVoMapper userVoMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    private final String password = "123456";

    @Autowired
    private ClazzStudentMapper clazzStudentMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ClazzMapper clazzMapper;
    /**
     * 登录之后返回token
     *
     * @param account
     * @param password
     * @param request
     * @return
     */
    @Override
    public R login(String account, String password, HttpServletRequest request) {
        if("".equals(account)){
            return R.error(ResultStatus.LOGIN_ERROR);
        }

        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(account);

        //判断账号密码是否正确  加盐加密
        if (userDetails == null || passwordEncoder.matches(password, userDetails.getPassword())) {
            return R.error(ResultStatus.USERNAME_ERROR);
        }

        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        tokenMap.put("id", UserUtil.getUserId().toString());

        UserLog userLog = new UserLog();
        userLog.setUserId(UserUtil.getUserId())
                .setCreateTime(ObjectUtils.CurrentTime())
                .setToken(token)
                .setIp(IpUtil.getIpAddr(request));
        return R.ok(ResultStatus.LOGIN_SUCCESS, tokenMap);
    }


    /**
     * 根据用户id查询角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<Role> getRoles(Integer userId) {
        return roleMapper.getRoles(userId);

    }

    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public User getInfo(Integer userId) {
        return userMapper.selectById(userId);
    }


    /**
     * 分页/模糊 查询所有的老师
     *
     * @param param
     * @return
     */
    @Override
    public Page<UserVo> getAll(GetAllTeacherByPageParam param) {
        Page<UserVo> page = new Page<>(param.getCurrent(), param.getSize());
        Page<UserVo> userVos = userVoMapper.selectPage(page,
                new LambdaQueryWrapper<UserVo>()
                        .like(UserVo::getName, param.getName())
                        .like(UserVo::getAccount, param.getAccount())
                        .select(UserVo::getId,
                                UserVo::getAccount,
                                UserVo::getGender,
                                UserVo::getName,
                                UserVo::getGender,
                                UserVo::getType,
                                UserVo::getMobile));
        log.error(userVos.getRecords()+"");
        List<UserVo> records = userVos.getRecords();
        //转换bean
        List<GetAllTeacherVo> getAllTeacherByPageParams = BeanConvert.convertList2List(records, GetAllTeacherVo.class);
        Page<GetAllTeacherVo> teacherPage = new Page<>();
        BeanUtils.copyProperties(userVos,teacherPage);
        teacherPage.setRecords(getAllTeacherByPageParams);
        return userVos;
    }


    /**
     * 根据用户的账号获取用户信息
     *
     * @param account
     * @return
     */
    @Override
    public User getUserByUsername(String account) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("account", account));
    }

    /**
     * 修改老师
     * @param param
     * @return
     */
    @Override
    @Transactional
    public R updateTeacher(UpdateTeacherParam param) {
        UserVo userVo = userVoMapper.selectById(param.getId());
        if(StringUtils.isEmpty(userVo)){
            return R.error(ResultStatus.ID_NOT_EXIST);
        }
        UserVo user = new UserVo();
        if(param.getType()!= userVo.getType()){
            //修改角色信息
            List<UserRole> userRoles = userRoleMapper.selectList(new LambdaQueryWrapper<UserRole>()
                    .eq(UserRole::getUserId, param.getId())
                    .select(UserRole::getId));
            for(UserRole role:userRoles){
                userRoleMapper.deleteById(role.getId());
            }
            addUserRole(param.getType(),userRoleMapper,param.getId());
        }
        BeanUtils.copyProperties(param,user);
        user.setUpdateTime(ObjectUtils.CurrentTime());
        int res = userVoMapper.updateById(user);
        if (res==1){
            return R.ok(ResultStatus.UPDATE_SUCCESS);
        }else{
            return R.error(ResultStatus.UPDATE_ERROR);
        }
    }

    /**
     * 批量添加学生
     *
     * @param clazzId
     * @param datas
     * @return
     */
    @Transactional
    @Override
    public boolean batchInsert(int clazzId, List<Object> datas) {
        if(datas.size()==0){
            return false;
        }
        List<UserVo> userVos = BeanConvert.convertList2List(datas, UserVo.class);
        //批量添加学生，同时 插入 50条
        String password = passwordEncoder.encode(this.password);
        for (UserVo user:userVos) {
            user.setPassword(password);
            user.setType(RoleEnum.STUDENT.getRes());
            user.setCreateTime(ObjectUtils.CurrentTime());
        }
        log.error(userVos.toString());
        //批量添加
        userVoMapper.batchInsert(userVos);
        //批量添加完Mybatis会set Id到userVos中
        //取出userId
        List<Integer> userId = userVos.stream().map(p -> p.getId()).collect(Collectors.toList());
        UserRole userRole = new UserRole();
        ClazzStudent clazzStudent = new ClazzStudent();
        for (Integer id:userId) {
            //添加到userRole表中
            userRole.setUserId(id).setRoleId(RoleEnum.STUDENT.getRes());
            userRoleMapper.insert(userRole);
            //添加到clazz表中
            clazzStudent.setStudentId(id).setClazzId(clazzId);
            clazzStudentMapper.insert(clazzStudent);
        }
        return true;
    }

    /**
     * 根据用户id查询单条老师数据
     * @param id
     * @return
     */
    @Override
    public R getTeacherById(Integer id) {
        UserVo userVo = userVoMapper.selectById(id);
        if (!userVo.getType().equals(RoleEnum.TEACHER.getRes()) ||
                !userVo.getType().equals(RoleEnum.TEACHER_LAB.getRes()) ||
                !userVo.getType().equals(RoleEnum.TEACHER_LAB_TEACHER.getRes())){
            return R.error(ResultStatus.ID_IS_NOT_TEACHER_ID);
        }
        //这里需要转换一下实体类

        return R.ok(ResultStatus.SELECT_SUCCESS,userVo);
    }




    /**
     * 在userRole表中添加数据
     * @param userType
     * @param userRoleMapper
     * @param userId
     */
    private static void addUserRole(Integer userType,UserRoleMapper userRoleMapper,Integer userId){
        insertTeacher(userId, userType, userRoleMapper);
    }
    /**
     * 创建一个userRole对象
     *
     * @param userId 用户id
     * @param roleId 用户具有什么角色
     * @return
     */
    private static UserRole getUserRole(Integer userId, Integer roleId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        logger.info(userRole + "");
        return userRole;
    }
    /**
     * 添加老师
     *
     * @param addTeacherParam
     * @return
     */
    @Override
    @Transactional
    public R addTeacher(AddTeacherParam addTeacherParam) {
        //判断电话是否存在
        User mobileExist = userMapper.selectOne(new QueryWrapper<User>().eq("mobile", addTeacherParam.getMobile()));
        logger.warn(mobileExist + "");
        if (!BeanUtil.isEmpty(mobileExist)) {
            return R.error(ResultStatus.MOBILE_EXIST);
        }
        //判断邮箱是否存在
        User emailExist = userMapper.selectOne(new QueryWrapper<User>().eq("email", addTeacherParam.getEmail()));
        if (!BeanUtil.isEmpty(emailExist)) {
            return R.error(ResultStatus.EMAIL_EXIST);
        }
        User user = new User();
        BeanUtils.copyProperties(addTeacherParam, user);
        user.setCreateTime(ObjectUtils.CurrentTime())
                .setAccount(addTeacherParam.getMobile())
                .setPassword(passwordEncoder.encode(userPassword));
//        userMapper.insert(user);
        //查询存进的user
        User userResult = userMapper.selectOne(new QueryWrapper<User>().eq("mobile", user.getAccount()));
        Integer userId = userResult.getId();
        Integer userType = user.getType();
//        insertTeacher(userId, userType, userRoleMapper);



        return R.ok(ResultStatus.ADD_SUCCESS);
    }

    private static void insertTeacher(Integer userId, Integer userType, UserRoleMapper userRoleMapper) {
        if (userType == RoleDetailEnum.TEACHER.ordinal()) {
            userRoleMapper.insert(getUserRole(userId, RoleDetailEnum.TEACHER.ordinal()));
        } else if (userType == RoleDetailEnum.TEACHER_LAB.ordinal()) {
            userRoleMapper.insert(getUserRole(userId, RoleDetailEnum.TEACHER_LAB.ordinal()));
        } else {
            userRoleMapper.insert(getUserRole(userId, RoleDetailEnum.TEACHER.ordinal()));
            userRoleMapper.insert(getUserRole(userId, RoleDetailEnum.TEACHER_LAB.ordinal()));
        }
    }
    @Override
    public List<Menus> getMenuByRoleId(Integer roleId) {
        return menuMapper.getMenuWithRoleId(roleId);
    }
//    @Override
//    public List<Menus> getMenuByRoleId(Integer roleId) {
//        List<RoleMenu> roleMenus = roleMenuMapper.selectList(new QueryWrapper<RoleMenu>().eq("role_id", roleId));
//        roleMenus.stream().map(i->{
//            Integer menuId = i.getMenuId();
//            Menu menu = menuMapper.selectById(menuId);
//            Menus menus = new Menus();
//
//            menus.setName(menu.getName());
//            menus.setComponent(menu.getComponent());
//            menus.setPath(menu.getPath());
//
//            Meta meta = new Meta();
//            meta.setIcon(menu.getIcon());
//            meta.setTitle(menu.getTitle());
//            menus.setMeta(meta);
//            menus.setChildren();
//        })
//    }
}
