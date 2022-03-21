package com.piedpiper.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private JwtTokenGenerator jwtTokenGenerator;
	
    public JwtConfigurer(JwtTokenGenerator jwtTokenProvider) {
        this.jwtTokenGenerator = jwtTokenProvider;
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenGenerator);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
