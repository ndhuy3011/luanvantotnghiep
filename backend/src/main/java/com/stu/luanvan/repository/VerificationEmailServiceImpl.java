package com.stu.luanvan.repository;

import com.stu.luanvan.model.verification.VerificationModel;

public interface VerificationEmailServiceImpl {
    VerificationModel getVerificationToken(String token);
}
