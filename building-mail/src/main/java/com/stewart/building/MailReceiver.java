package com.stewart.building;

import com.stewart.building.to.TeacherTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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

    @RabbitListener(queues = "mail.welcome")
    public void handler(TeacherTo to) throws MessagingException {
        LOGGER.error(to.getEmail());

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
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
        } catch (MessagingException e) {
            e.printStackTrace();
            LOGGER.error("发送失败",e.getMessage());
        }
    }
}
