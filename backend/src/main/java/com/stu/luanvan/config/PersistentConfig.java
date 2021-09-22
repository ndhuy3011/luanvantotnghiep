package com.stu.luanvan.config;

import com.stu.luanvan.model.user.UserModel;
import com.stu.luanvan.security.SecurityAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class PersistentConfig {
    /**
     * Khai báo bean cho auditor
     *
     */

    @Bean
    public AuditorAware<UserModel> auditorProvider() {
        return new SecurityAuditorAware();
    }
}
