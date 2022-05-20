package com.stewart.building.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.stewart.building.constants.MailConstants;
import com.stewart.building.mbg.pojo.MailLog;
import com.stewart.building.mbg.service.IMailLogService;
import com.stewart.building.to.TeacherTo;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Stewart
 * @create 2022/5/20
 * @Description 邮件发送定时任务
 */
@Component
public class MailTask {

    @Autowired
    private IMailLogService mailLogService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 10秒发送一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void mailTask() {
        List<MailLog> list = mailLogService.list(new QueryWrapper<MailLog>()
                .eq("status", 0)
                .lt("try_time", LocalDateTime.now()));
        if(list.size()>0){
            list.forEach(mailLog -> {
                //如果重试次数超过3次 ，更新状态投递状态为失败
                if (MailConstants.MAX_TRY_COUNT <= mailLog.getCount()) {
                    mailLogService.update(new UpdateWrapper<MailLog>()
                            .set("status", 2)
                            .eq("msg_id", mailLog.getMsgId()));
                }
                mailLogService.update(new UpdateWrapper<MailLog>()
                        .set("count", mailLog.getCount() + 1)
                        .set("update_time", LocalDateTime.now())
                        .set("try_time", LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT))
                        );

                //这个teacherTo需要去查询出来
                TeacherTo teacherTo = new TeacherTo();
                teacherTo.setEmail("1936979980@qq.com").setName("hello");
                //这里需要重新发送消息
                rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,
                        MailConstants.MAIL_ROUTING_KEY_NAME, teacherTo,
                        new CorrelationData(mailLog.getMsgId())
                );
            });
        }



    }
}
