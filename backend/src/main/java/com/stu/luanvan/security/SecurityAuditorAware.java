package com.stu.luanvan.security;

import com.stu.luanvan.model.user.UserModel;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SecurityAuditorAware implements AuditorAware<UserModel> {

    @Override
    public Optional<UserModel> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(MyUserDetails.class::cast)
                .map(MyUserDetails::getUserModel);
    }
}
