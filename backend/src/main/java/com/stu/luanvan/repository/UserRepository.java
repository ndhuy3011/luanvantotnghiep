package com.stu.luanvan.repository;

import com.stu.luanvan.model.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer> {
    UserModel findByUserName(String username);
    UserModel findByEmail(String email);
    UserModel findByEmailOrNumberPhone(String email,String numberPhone);
    UserModel findByAccessToken(String accessToken);
}
