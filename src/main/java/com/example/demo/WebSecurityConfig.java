/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 *
 * @author fahadabunayyan
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    
    @Autowired
    CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.csrf().disable();
        http
            .authorizeRequests()
                .antMatchers("/logout").permitAll()
                .antMatchers("/", "/index", "/index.html", "/about", "about", "/signup", "signup").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login").successForwardUrl("/media")
                .permitAll()
                .and()
            .logout().logoutSuccessHandler(customizeLogoutSuccessHandler)
                .permitAll();
    }
    
    
    
    @Bean
    @Override
    public UserDetailsService userDetailsService()  
    {
        

        
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("fahad")
                .roles("USER")
                .build();
        
        
        System.out.print("Username: " + user.getUsername() + "\nPassword: " + user.getPassword());

        return new InMemoryUserDetailsManager(user);
    }
    
}
