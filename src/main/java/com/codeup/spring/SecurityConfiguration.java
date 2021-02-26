package com.codeup.spring;

import com.codeup.spring.services.UserDeatailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDeatailsLoader userDeatailsLoader;

    public SecurityConfiguration(UserDeatailsLoader userDeatailsLoader) {
        this.userDeatailsLoader = userDeatailsLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDeatailsLoader)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // define how to login
        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()

                // logout configuration
                .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/")

                // define pages where you don't have to be logged in
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/sign-up", "/posts", "/ads", "/login", "/weather-map")
                    .permitAll()

                // define pages that require users to be logged in
                .and()
                    .authorizeRequests()
                    .antMatchers("/ads/*", "/posts/*", "/posts")
                    .authenticated();
    }

}
