package com.nnam01.MyStudy.config.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint
    implements AuthenticationEntryPoint { // 인증이 아예 안 된 요청에 대한 응답 처리 담당

  private final ObjectMapper objectMapper = new ObjectMapper();

  // commence는 인증되지 않은 요청이 들어왔을 때 호출됨
  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException, ServletException {

    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    Map<String, Object> errorDetails = new HashMap<>();
    errorDetails.put("status", HttpServletResponse.SC_UNAUTHORIZED);
    errorDetails.put("message", authException.getMessage());

    response.getWriter().write(objectMapper.writeValueAsString(errorDetails));
    response.getWriter().flush();
  }
}
