package com.digify.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.digify.auth.DigifyAuthenticationFailureHandler;
import com.digify.auth.DigifyAuthenticationProvider;
import com.digify.auth.MySimpleUrlAuthenticationSuccessHandler;
import com.digify.auth.RefererRedirectionAuthenticationSuccessHandler;

@Configuration
// @EnableWebSecurity = @EnableWebMVCSecurity + Extra features
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DigifyAuthenticationProvider digifyAuthenticationProvider;

    @Autowired
    DigifyAuthenticationFailureHandler digifyAuthenticationFailureHandler;

    @Autowired
    MySimpleUrlAuthenticationSuccessHandler mySimpleUrlAuthenticationSuccessHandler;

    @Autowired
    RefererRedirectionAuthenticationSuccessHandler refererRedirectionAuthenticationSuccessHandler;

    public WebSecurityConfig() {
        super();
    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {

        // Users in memory.
 
      /*  auth.inMemoryAuthentication().withUser("user1").password("12345").roles("USER");
        auth.inMemoryAuthentication().withUser("admin1").password("12345").roles("USER, ADMIN");
 
        // For User in database.
        auth.userDetailsService(myDBAauthenticationService);*/
        auth.authenticationProvider(digifyAuthenticationProvider);
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
        http.authorizeRequests().antMatchers( "/forgotpassword", "/requestQuotes").permitAll();
        http.authorizeRequests().antMatchers("/user/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
        // For ADMIN only.
        http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/ecommerce/**").access("hasRole('ROLE_USER')");
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Config for Login Form
        http.authorizeRequests().and().formLogin()
                // Submit URL of login page.
//                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .successHandler(mySimpleUrlAuthenticationSuccessHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .failureHandler(digifyAuthenticationFailureHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")// Config for Logout Page
//                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/loginpage?logout")
                .and().csrf().disable();
// 
    }

}