package com.stu.luanvan.service.email;

import com.stu.luanvan.model.user.UserModel;
import com.stu.luanvan.model.verification.VerificationModel;
import com.stu.luanvan.repository.VerificationEmailReponsitory;
import com.stu.luanvan.repository.VerificationEmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationEmailService implements VerificationEmailServiceImpl {
    @Autowired
    VerificationEmailReponsitory verificationEmailReponsitory;
    @Override
    public VerificationModel getVerificationToken(String token) {

        try {
            return verificationEmailReponsitory.findOneByConfirmationToken(token);
        }catch(Exception ex)
        {
            return null;
        }

    }
    public VerificationModel save(UserModel user){
        VerificationModel verificationUserEntity = new VerificationModel(user);
        return verificationEmailReponsitory.save(verificationUserEntity);
    }
}
