package com.aselmind.fsme.rest.config.security;

import com.aselmind.fsme.rest.config.exception.ErrorBody;
import com.aselmind.fsme.rest.config.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(map(authException))
                .build();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }

    private ErrorBody map(AuthenticationException authException) {
        return ErrorBody.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .type(authException.getClass().getSimpleName())
                .detail(authException.getLocalizedMessage())
                .build();
    }
}