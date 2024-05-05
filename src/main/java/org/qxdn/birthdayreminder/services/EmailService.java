package org.qxdn.birthdayreminder.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.qxdn.birthdayreminder.model.enums.ErrorEnum;
import org.qxdn.birthdayreminder.model.exception.BirthdayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    /**
     * 发送方
     */
    @Value("${spring.mail.username}")
    private String from;

    public void sendHTMLMessage(String to, String subject, String text) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text,true);
            emailSender.send(message);
        } catch (MessagingException e) {
            throw new BirthdayException(ErrorEnum.FAIL,"Failed to send email: " + e.getMessage());
        }
    }


}
