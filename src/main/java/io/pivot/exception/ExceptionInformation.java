package io.pivot.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public class ExceptionInformation {
    String body;
    String code;
    public ExceptionInformation(String bodyOfResponse, String notFound) {
        this.body = body;
        this.code = notFound;
    }
}
