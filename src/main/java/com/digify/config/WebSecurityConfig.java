package com.digify.config;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import com.digify.auth.ImageVideoAuthenticationFailureHandler;
import com.digify.auth.ImageVideoAuthenticationProvider;
import com.digify.auth.MySimpleUrlAuthenticationSuccessHandler;
 
@Configuration
// @EnableWebSecurity = @EnableWebMVCSecurity + Extra features
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired
	ImageVideoAuthenticationProvider imageVideoAuthenticationProvider;
    
    @Autowired
    ImageVideoAuthenticationFailureHandler imageVideoAuthenticationFailureHandler;
    
    @Autowired
    MySimpleUrlAuthenticationSuccessHandler mySimpleUrlAuthenticationSuccessHandler;
    
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
 
        // Users in memory.
 
      /*  auth.inMemoryAuthentication().withUser("user1").password("12345").roles("USER");
        auth.inMemoryAuthentication().withUser("admin1").password("12345").roles("USER, ADMIN");
 
        // For User in database.
        auth.userDetailsService(myDBAauthenticationService);*/
    	auth.authenticationProvider(imageVideoAuthenticationProvider);
    }
 
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/static/**")
            .antMatchers("/IMAGES/**");
       
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
 
//        http.csrf().disable();
      http.authorizeRequests().antMatchers("/*","/forgotpassword").permitAll();
        http.authorizeRequests().antMatchers("/user/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
        // For ADMIN only.
        http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
 
        // Config for Login Form
        http.authorizeRequests().and().formLogin()
                // Submit URL of login page.
//                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .successHandler(mySimpleUrlAuthenticationSuccessHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .failureHandler(imageVideoAuthenticationFailureHandler)
                // Config for Logout Page
//                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/loginpage?logout")
                .and().csrf().disable();
// 
    }
    
}