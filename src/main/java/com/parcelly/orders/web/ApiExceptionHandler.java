package com.parcelly.orders.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/**
 * Renders every Spring MVC error (bad JSON, unsupported media type, ErrorResponseException
 * such as OrderNotFoundException, ...) as an RFC 9457 application/problem+json body.
 * Validation failures additionally list each invalid field under "errors".
 */
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemDetail problem = ex.getBody();
        problem.setTitle("Invalid request");
        problem.setDetail("The request has invalid fields.");
        problem.setProperty("errors", fieldErrors(ex));
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private static List<FieldViolation> fieldErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new FieldViolation(error.getField(), error.getDefaultMessage()))
                .toList();
    }

    record FieldViolation(String field, @Nullable String message) {
    }
}
