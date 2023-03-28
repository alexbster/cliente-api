package com.demo.cliente.api.exception;

import com.demo.cliente.api.dto.ExceptionDto;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class BadRequestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { ConstraintViolationException.class })
    protected ResponseEntity<Object> handleConflict(ConstraintViolationException ex, WebRequest request){
        log.error("Error en el servicio", ex);
        List<String> errors = ((ConstraintViolationException) ex).getConstraintViolations()
                .stream()
                .map(x -> x.getPropertyPath() + " " + x.getMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, null, 400);
    }

    @ExceptionHandler(value = { RuntimeException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        log.error("Error en el servicio", ex);
        return new ResponseEntity<>(new ExceptionDto("Error en el servicio"), null, 500);
    }
}
