package com.bihu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by wanganyu on 2017/12/19.
 */

@Service
public class MailSender implements InitializingBean{
    private static final Logger logger= LoggerFactory.getLogger(MailSender.class);
    private JavaMailSenderImpl javaMailSender;
    public void sendMail(String userName,String email,String code,int operation){
        javaMailSender.send(new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
             logger.info("开始发送邮件");
             MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true,"UTF-8");
             mimeMessageHelper.setFrom(BiHuConstant.MAIL_FROM);
             mimeMessageHelper.setTo(email);
             mimeMessageHelper.setSubject("逼乎网激活邮件");
             StringBuilder sb=new StringBuilder();
             sb.append("<html><head></head><body>");
             if(operation==1){
                 sb.append("欢迎你,"+userName+"注册逼乎网，<a href="+BiHuConstant.DOMAIN_NAME+"/activate?code="+code);
                 sb.append(">点击激活</a></body>");
             }else if(operation==2){
                 sb.append("是否将您的密码修改为：");
                 sb.append(code.substring(0,6));
                 sb.append(",<a href="+BiHuConstant.DOMAIN_NAME+"modify.do?code="+code);
                 sb.append("点击是</a></body>");
             }
             mimeMessageHelper.setText(sb.toString(),true);
             logger.info("邮件发送结束.....");
            }
        });
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        javaMailSender=new JavaMailSenderImpl();
        javaMailSender.setUsername(BiHuConstant.MAIL_FROM);
        javaMailSender.setPassword(BiHuConstant.MAIL_PASSWORD);
        javaMailSender.setHost(BiHuConstant.MAIL_HSOT);
        javaMailSender.setPort(465);
        javaMailSender.setProtocol("smtps");
        javaMailSender.setDefaultEncoding("utf8");
        Properties properties=new Properties();
        properties.put("mail.smtp.ssl.enable",true);
        javaMailSender.setJavaMailProperties(properties);
        /**
         * mailSender = new JavaMailSenderImpl();
         mailSender.setUsername("wanganyu1996@163.com");
         mailSender.setPassword("wanganyu123");
         mailSender.setHost("smtp.163.com");
         //mailSender.setHost("smtp.qq.com");
         mailSender.setPort(465);
         mailSender.setProtocol("smtps");
         mailSender.setDefaultEncoding("utf8");
         Properties javaMailProperties = new Properties();
         javaMailProperties.put("mail.smtp.ssl.enable", true);
         //javaMailProperties.put("mail.smtp.auth", true);
         //javaMailProperties.put("mail.smtp.starttls.enable", true);
         mailSender.setJavaMailProperties(javaMailProperties);
         */
    }
}
