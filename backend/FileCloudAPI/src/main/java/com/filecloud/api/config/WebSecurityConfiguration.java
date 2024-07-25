package com.filecloud.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration  {
	@Autowired
	private UserDetailsService userDetailsService;
    @Bean
    AuthenticationProvider authenticationProvider() {
    	 DaoAuthenticationProvider provider
          = new DaoAuthenticationProvider();
    	  provider.setUserDetailsService(userDetailsService);
    	  provider.setPasswordEncoder(new BCryptPasswordEncoder());
    	  return  provider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	 http
         .authorizeHttpRequests(authorize -> authorize
             .requestMatchers("/").permitAll()
             .requestMatchers("/home").hasAuthority("user")
             .anyRequest().authenticated()
         )
         .httpBasic(Customizer.withDefaults()); // 使用推薦的配置方法設置 HTTP 基本認證
     return http.build();
    }
}
