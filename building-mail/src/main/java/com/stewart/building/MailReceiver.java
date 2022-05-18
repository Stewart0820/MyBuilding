package com.stewart.building;

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
    public void handler(String email) throws MessagingException {
        LOGGER.error(email);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //发件人
        helper.setFrom(mailProperties.getUsername());
        //收件人
        String[] to = new String[]{"11@11.com", "22@22.com"};
        helper.setTo(email);
        //标题
        helper.setSubject("130.51.23.249MyCat故障");
        //文本
        helper.setText("130.51.23.249 Mycat 故障，请尽快去看看。");
        //附件
//        helper.addAttachment("downFile",new File("D:\\cygwin64\\home\\workspace3\\learn-demo\\zookeeper\\src\\test\\java\\com\\tzxylao\\design\\ZookeeperApplicationTests.java"));
        javaMailSender.send(mimeMessage);

        //创建邮件内容
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(mailProperties.getUsername()); //这里指的是发送者的账号
//        message.setTo(email);
//        message.setSubject("12");
//        message.setText("12");
//        //发送邮件
//        javaMailSender.send(message);


//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
//        try {
//            //发件人
//            helper.setFrom(mailProperties.getUsername());
//            //收件人
//            helper.setTo(email);
//            //主题
//            helper.setSubject("入职欢迎邮件");
//            //发送日期
//            helper.setSentDate(new Date());
//            //邮件内容
////            Context context = new Context();
////            context.setVariable("name", email);
////            context.setVariable("posName", user.get.getPosition().getName());
////            context.setVariable("joblevelName", employee.getJoblevel().getName());
////            context.setVariable("departmentName", employee.getDepartment().getName());
////            String mail = templateEngine.process("mail", context);
////            helper.setText(mail, true);
//            helper.setText("hello");
//            javaMailSender.send(mimeMessage);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            LOGGER.error("发送失败",e.getMessage());
//        }
    }
}
