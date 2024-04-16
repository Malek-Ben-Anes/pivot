package io.pivot.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@ControllerAdvice
public class ExceptionHandler extends ExceptionHandlerExceptionResolver {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = { NotFoundException.class })
    protected Object handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return new ExceptionInformation(bodyOfResponse, HttpStatus.NOT_FOUND.toString());
    }
}
