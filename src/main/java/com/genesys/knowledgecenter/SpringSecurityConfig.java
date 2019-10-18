package com.genesys.knowledgecenter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
        .withUser("author").password("{noop}test123").roles( "AUTHOR");

	}
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/document/**").hasRole("AUTHOR")
                .antMatchers(HttpMethod.POST, "/Kbase").hasRole("AUTHOR")
                .antMatchers(HttpMethod.PUT, "/Kbase").hasRole("AUTHOR")
                .antMatchers(HttpMethod.PUT ,"/document").hasRole("AUTHOR")
                .antMatchers(HttpMethod.DELETE ,"/document").hasRole("AUTHOR")
                .antMatchers(HttpMethod.DELETE ,"/Kbase/**").hasRole("AUTHOR")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

}
