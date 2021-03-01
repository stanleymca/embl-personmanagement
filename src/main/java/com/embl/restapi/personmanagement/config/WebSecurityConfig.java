package com.embl.restapi.personmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * delegates the exceptions to ControllerAdvice
     * qualifier is required for the resolver bean
     */
    private final HandlerExceptionResolver resolver;

    public WebSecurityConfig( @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/console/**").permitAll();
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
        httpSecurity.csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and().httpBasic();        
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("embl")
                .password(passwordEncoder().encode("embl"))
                .roles("ADMIN");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
