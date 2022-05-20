package com.stewart.building.mbg.controller.user.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stewart.building.common.R;
import com.stewart.building.common.ResultStatus;
import com.stewart.building.common.renum.ReturnEnum;
import com.stewart.building.constants.MailConstants;
import com.stewart.building.mbg.pojo.MailLog;
import com.stewart.building.mbg.pojo.User;
import com.stewart.building.mbg.pojo.UserVo;
import com.stewart.building.mbg.service.IMailLogService;
import com.stewart.building.mbg.service.IUserService;
import com.stewart.building.param.user.teacher.AddTeacherParam;
import com.stewart.building.param.user.teacher.GetAllTeacherByPageParam;
import com.stewart.building.param.user.teacher.UpdateTeacherParam;
import com.stewart.building.to.TeacherTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author 陈鸿杰
 * @create 2022/1/8
 * @Description
 */
@Api(tags = "老师模块")
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private IUserService userService;

    @Autowired
    private IMailLogService mailLogService;
    @ApiOperation(value = "测试rabbitmq")
    @GetMapping("/text/{email}")
    public R text(@PathVariable String email){
        //添加成功,到mail服务端去发送邮箱
//        rabbitTemplate.convertAndSend("mail.welcome",email);
        return R.ok(ResultStatus.SUCCESS);
    }

    @ApiOperation(value = "分页获取所有的老师")
    @PostMapping("/getAll")
    public R getAll(@Valid @RequestBody GetAllTeacherByPageParam teacherByPageParam) {
        Page<UserVo> result = userService.getAll(teacherByPageParam);
        return R.ok(ResultStatus.SELECT_SUCCESS, result);
    }

    @ApiOperation(value = "添加老师")
    @PostMapping("/add")
    public R addTeacher(@Valid @RequestBody AddTeacherParam addTeacherParam) {
        TeacherTo teacherTo = new TeacherTo();
        teacherTo.setEmail(addTeacherParam.getEmail()).setName(addTeacherParam.getName());

        String msgId = UUID.randomUUID().toString();
        MailLog mailLog = new MailLog();
        mailLog.setMsgId(msgId)
                //这个是老师的id
                .setEid(1)
                .setStatus(0)
                .setRoutekey(MailConstants.MAIL_ROUTING_KEY_NAME)
                .setExchange(MailConstants.MAIL_EXCHANGE_NAME)
                .setCount(1)
                .setTryTime(LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT))
                .setCreateTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now());
        mailLogService.save(mailLog);

        rabbitTemplate.convertAndSend("MailConstants.MAIL_EXCHANGE_NAME",
                MailConstants.MAIL_ROUTING_KEY_NAME,teacherTo
        ,new CorrelationData(msgId));

        return R.ok(ResultStatus.SUCCESS);
//        return userService.addTeacher(addTeacherParam);
    }

    @ApiOperation(value = "根据id查询老师")
    @GetMapping("/getById/{id}")
    public R getTeacherById(@PathVariable Integer id) {
        if (StringUtils.isEmpty(id)) {
            return R.error(ResultStatus.NEED_ID);
        }
        return userService.getTeacherById(id);
    }

    @ApiOperation(value = "修改老师")
    @PostMapping("/update")
    public R updateTeacher(@Valid @RequestBody UpdateTeacherParam param) {
        return userService.updateTeacher(param);
    }
}
