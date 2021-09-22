package com.stu.luanvan.security;

import com.stu.luanvan.model.user.UserModel;
import com.stu.luanvan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Xác thực người dùng theo username
     * @param email
     * @return UserDetails
     */

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByEmail(email);
        if(userModel==null){
             throw new UsernameNotFoundException(email);
        }
        return new MyUserDetails(userModel);
    }
}
