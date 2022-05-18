package com.stewart.building.mbg.controller.other;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stewart.building.common.R;
import com.stewart.building.common.ResultStatus;
import com.stewart.building.mbg.pojo.User;
import com.stewart.building.mbg.service.IUserService;
import com.stewart.building.util.EmailUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.mail.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 陈鸿杰
 * @create 2022/1/8
 * @Description
 */
@Api(tags = "邮箱模块")
@RestController("/email")
public class EmailController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "根据邮箱发送验证码")
    @GetMapping("/code/{email}")
    public R sendCode(@PathVariable String email){
        boolean judgeEmail = EmailUtils.isMail(email);
        System.out.println(judgeEmail+"----------------------------");
        if(!judgeEmail){
            return R.error(ResultStatus.EMAIL_FORMAT_ERROR);
        }
        int code = RandomUtil.randomInt(100000, 999999);
        boolean b = EmailUtils.sendEmail(email, code);
        if(!b){
            return R.error(ResultStatus.EMAIL_SEND_ERROR);
        }
        return R.error(ResultStatus.EMAIL_SEND_SUCCESS);
    }

}
