package br.evoluum.robot.controller;

import br.evoluum.robot.error.AppException;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AppException.class)
    protected ResponseEntity<Object> handleBusinessException(AppException ex) {
        return handleExceptionInternal(ex,
                Erro.builder()
                        .code(400)
                        .userMessage(ex.getMessage())
                        .developerMessage(Optional.ofNullable(ex.getCause())
                                .orElse(ex)
                                .getMessage())
                        .build(),
                null, HttpStatus.BAD_REQUEST, null);
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class Erro {
        private int code;

        private String userMessage;

        private String developerMessage;

    }
}
