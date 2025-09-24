package com.mahmoud.appointmentsystem.notificationservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private  JavaMailSender mailSender;
private final Logger logger = LoggerFactory.getLogger(EmailService.class);
    @Value("${spring.mail.username}")
    private String sender;



    public void sendEmail(String to, String subject, String body) {


        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        try {
            mailSender.send(message);
            logger.info("Email sent successfully to {}",to);
        }catch (Exception ex){
            System.out.println("Error sending email: " + ex.getMessage());
            logger.error("Failed to send email to {}: {}", to, ex.getMessage());
        }
    }


}
