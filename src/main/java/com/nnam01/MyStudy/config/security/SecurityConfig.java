package com.nnam01.MyStudy.config.security;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  private static final String[] WHITELIST = {
      "/**"  // 전체 URI 허용 (특정 URI만 허용하려면 여기에 개별 경로나 패턴 배열 작성)
  };

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize
            .dispatcherTypeMatchers(DispatcherType.ERROR, DispatcherType.ASYNC).permitAll()
            .requestMatchers(WHITELIST).permitAll()
            .anyRequest().authenticated()
        )
        .csrf(csrf -> csrf.disable()) // CSRF 비활성화 (필요할 때만)
        .httpBasic(Customizer.withDefaults()) // 기본 인증 활성화 또는 disable() 가능
        .formLogin(Customizer.withDefaults());

    return http.build();
  }
}

