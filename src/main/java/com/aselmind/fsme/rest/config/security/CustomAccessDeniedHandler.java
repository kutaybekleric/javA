package com.aselmind.fsme.rest.config.security;

import com.aselmind.fsme.rest.config.exception.ErrorBody;
import com.aselmind.fsme.rest.config.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(map(accessDeniedException))
                .build();
        response.setStatus(HttpStatus.FORBIDDEN.value());
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }

    private ErrorBody map(AccessDeniedException accessDeniedException) {
        return ErrorBody.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .type(accessDeniedException.getClass().getSimpleName())
                .detail(accessDeniedException.getLocalizedMessage())
                .build();
    }

}