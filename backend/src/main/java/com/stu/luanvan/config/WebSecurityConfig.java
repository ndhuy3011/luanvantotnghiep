package com.stu.luanvan.config;

import com.stu.luanvan.controller.URlController;
import com.stu.luanvan.filter.JwtRequestFilter;
import com.stu.luanvan.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private  static String ROLE_ADMIN = "ADMIN";
    private static String ROLE_EDIT="EDIT";
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,URlController.CATEGORY_ADMIN).hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET,URlController.PRODUCT_ADMIN).hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET,URlController.COLOR_ADMIN).hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET,URlController.COUPON_ADMIN).hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET,URlController.DETAILS_PRODUCT_ADMIN).hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET,URlController.FILE_ADMIN).hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET,URlController.SIZE_ADMIN).hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET,URlController.CATEGORY_ADMIN+"/**").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET,"/api/admin/user").hasAnyRole(ROLE_ADMIN,ROLE_EDIT)
                .antMatchers(HttpMethod.GET,"/api/user").authenticated()
                .antMatchers(HttpMethod.POST,"/api/register").permitAll()
                .antMatchers(HttpMethod.POST,"/api/login").permitAll()
                .antMatchers(HttpMethod.POST,"/api/admin/login").permitAll()
                .antMatchers(HttpMethod.POST,URlController.ADMIN+"/**").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT,URlController.ADMIN+"/**").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE,URlController.ADMIN+"/**").hasRole(ROLE_ADMIN)
                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
