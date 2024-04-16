package io.pivot.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@ControllerAdvice
public class ExceptionHandler extends ExceptionHandlerExceptionResolver {

    // put some here match exception with http code
}
