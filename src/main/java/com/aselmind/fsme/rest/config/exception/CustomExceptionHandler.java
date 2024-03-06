package com.aselmind.fsme.rest.config.exception;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorResponse.ErrorResponseBuilder builder = ErrorResponse.builder();
        ex.getAllErrors().forEach(objectError -> builder.error(map(objectError)));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(builder.build());
    }

    private ErrorBody map(ObjectError objectError) {
        return ErrorBody.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .type(objectError.getClass().getSimpleName())
                .detail(getMessage(objectError))
                .build();
    }

    private String getMessage(ObjectError er) {
        if (er instanceof FieldError fe) {
            return String.format("%s: %s", fe.getField(), er.getDefaultMessage());
        } else {
            return er.getDefaultMessage();
        }
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(map(ex))
                .build();
        return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
    }

    private ErrorBody map(ApiException ex) {
        return ErrorBody.builder()
                .status(ex.getHttpStatus().value())
                .type(ex.getType())
                .detail(ex.getLocalizedMessage())
                .build();
    }

    @ExceptionHandler(NotImplementedException.class)
    public ResponseEntity<ErrorResponse> handleNotImplementedException(NotImplementedException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(map(ex))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(errorResponse);
    }

    private ErrorBody map(NotImplementedException ex) {
        return ErrorBody.builder()
                .status(HttpStatus.NOT_IMPLEMENTED.value())
                .type(ex.getClass().getSimpleName())
                .detail(ex.getLocalizedMessage())
                .build();
    }
}
