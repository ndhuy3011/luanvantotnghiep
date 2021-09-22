package com.stu.luanvan.service.user;

import com.stu.luanvan.model.user.UserModel;
import com.stu.luanvan.request.UserRequest;
import com.stu.luanvan.request.auth.AccessTokenRequest;
import com.stu.luanvan.request.auth.RegisterRequest;
import com.stu.luanvan.service.ServiceInterfaces;

public interface UserServiceInterfaces extends ServiceInterfaces<UserModel, UserRequest> {
    UserModel findByUserName(String userName);
    UserModel findByEmail(String email);
    UserModel saveNew(RegisterRequest userRequest) throws Exception;
    UserModel saveNewFacebook(AccessTokenRequest userRequest) throws Exception;
}