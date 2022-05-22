package com.stewart.building;

import com.rabbitmq.client.Channel;
import com.stewart.building.constants.MailConstants;
import com.stewart.building.to.TeacherTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

/**
 * @author Stewart
 * @create 2022/5/17
 * @Description
 */
@Component
public class MailReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailReceiver.class);

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void handler(Message message, Channel channel) {
        TeacherTo to = (TeacherTo) message.getPayload();
        MessageHeaders headers = message.getHeaders();
        // 消息序号
        long tag = (long) headers.get(AmqpHeaders.DELIVERY_TAG);
        String msgId = (String) headers.get("spring_returned_message_correlation");

        HashOperations hashOperations = redisTemplate.opsForHash();

        LOGGER.info(to.getEmail());


        try {
            if(hashOperations.entries("mail_log").containsKey(msgId)){
                LOGGER.error("消息已经被消费过了",msgId);
                /**
                 * 手动确认消息
                 * tag：消息序号
                 * mutiple：是否确认多条
                 */
                channel.basicAck(tag,false);
                return;
            }
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            //发件人
            helper.setFrom(mailProperties.getUsername());
            //收件人
            helper.setTo(to.getEmail());
            //主题
            helper.setSubject("泉信工作室");
            //发送日期
            helper.setSentDate(new Date());
            //邮件内容
            Context context = new Context();
            context.setVariable("name", to.getName());
            String mail = templateEngine.process("mail", context);
            helper.setText(mail, true);
            javaMailSender.send(mimeMessage);
            LOGGER.info("发送成功");
            //将消息的id存入到redis中
            hashOperations.put("mail_log",msgId,"ok");
            channel.basicAck(tag,false);
        } catch (Exception e) {
            /**
             * 手动确认消息
             * tag:消息序号
             * multiple:是否确认多条
             * requeue:是否退回到队列
             */
            try {
                channel.basicNack(tag,false,true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            LOGGER.error("发送失败",e.getMessage());
        }
    }
}
