package com.stu.luanvan.controller.admin.authonticator;

import com.stu.luanvan.request.auth.LoginRequest;
import com.stu.luanvan.request.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthonticatorInterfaceController {
    ResponseEntity<?> register(@Valid @RequestBody UserRequest userRequest, Errors errors) throws Exception;
    ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception;
    ResponseEntity<?> profile();
    ResponseEntity<?> getAllUser();
}
