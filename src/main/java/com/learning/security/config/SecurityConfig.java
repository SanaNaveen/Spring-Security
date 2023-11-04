package com.learning.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    String[] permitted = new String[]{
            "/static/**"
    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.authorizeHttpRequests(requests ->
                        requests.requestMatchers(permitted).permitAll()
                                .requestMatchers("/leaders/**").hasRole("MANAGER")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .exceptionHandling(configurer -> {
                    configurer.accessDeniedPage("/accessDenied");
                })
                .formLogin(form ->
                        form.loginPage("/customLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .defaultSuccessUrl("/")
                                .permitAll())
                .logout(LogoutConfigurer::permitAll);
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("naveen").password("123").roles("USER").build();

        UserDetails userDetails1 = User.withDefaultPasswordEncoder()
                .username("santhosh").password("123").roles("USER","ADMIN").build();

        UserDetails userDetails2 = User.withDefaultPasswordEncoder()
                .username("dinesh").password("123").roles("USER","MANAGER").build();

        return new InMemoryUserDetailsManager(userDetails,userDetails1,userDetails2);
    }


}
