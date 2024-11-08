package ru.DTF98.stats.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleOtherExceptions(Throwable e) {
        ErrorResponse apiError = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .reason("Ошибка сервера")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        log.warn(apiError.toString(), e);
        return apiError;
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class,
            MissingServletRequestParameterException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(Exception e) {
        ErrorResponse apiError = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.toString())
                .reason("Неверно составленный запрос")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        log.warn(apiError.toString(), e);
        return apiError;
    }
}
