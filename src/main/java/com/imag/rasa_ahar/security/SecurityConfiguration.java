package com.imag.rasa_ahar.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    JwtAuthFilter authFilter;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
      // http.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("admin");
        http.authorizeHttpRequests().requestMatchers("/com.imag.rasa-ahar/v1/user/authenticate","/com.imag.rasa-ahar/v1/user/new-user",
                "/com.imag.rasa-ahar/v1/restaurant/**").permitAll();
        http.authorizeHttpRequests().requestMatchers("/com.imag.rasa-ahar/v1/user-admin/**").hasAuthority("admin").and().exceptionHandling().accessDeniedHandler(((request, response, accessDeniedException) -> response.setStatus(HttpServletResponse.SC_FORBIDDEN)));
        http.authorizeHttpRequests().requestMatchers("/com.imag.rasa-ahar/v1/user/**").hasAuthority("user");
        http.authorizeHttpRequests().anyRequest().permitAll();
        http.httpBasic();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authenticationProvider(authenticationProvider()).addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
