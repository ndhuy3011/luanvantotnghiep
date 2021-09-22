package com.stu.luanvan.service.email;

import com.stu.luanvan.model.verification.VerificationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendEmail(VerificationModel verificationUserEntity) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(verificationUserEntity.getUser().getEmail());
        mailMessage.setSubject("Xác thực đăng ký");
        mailMessage.setFrom("myduyen06122910@gmail.com");
        mailMessage.setText("Vui lòng bạn nhấp vào đường dẫn này để xác thực tài khoản của bạn : "
                +"https://localhost:3000/verification?token="+verificationUserEntity.getConfirmationToken());
        javaMailSender.send(mailMessage);
    }
}
