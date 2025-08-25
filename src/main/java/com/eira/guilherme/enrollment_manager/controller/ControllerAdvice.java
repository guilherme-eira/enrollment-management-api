package com.eira.guilherme.enrollment_manager.controller;

import com.eira.guilherme.enrollment_manager.exception.BusinessException;
import com.eira.guilherme.enrollment_manager.exception.ResourceNotFoundException;
import com.eira.guilherme.enrollment_manager.dto.ErrorResponseDTO;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleError400(MethodArgumentNotValidException ex, HttpServletRequest req) {
        var errors = ex.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, f -> Objects.requireNonNull(f.getDefaultMessage())));
        return new ErrorResponseDTO(
                "Erro de formatação",
                "É necessário que os campos estejam preenchidos conforme indicado em 'invalidFields'",
                req.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now().toString(),
                errors
                );
    };

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleError400(HttpMessageNotReadableException ex, HttpServletRequest req) {

        String fieldName = "desconhecido";

        if (ex.getCause() instanceof InvalidFormatException invalidFormatException) {
            if (!invalidFormatException.getPath().isEmpty()) {
                fieldName = invalidFormatException.getPath().getFirst().getFieldName();
            }
        }

        return new ErrorResponseDTO(
                "Erro ao desserializar o corpo da requisição",
                "Valor inválido no campo '%s'".formatted(fieldName),
                req.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now().toString(),
                null
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResponseDTO handleError400(MethodArgumentTypeMismatchException exception, HttpServletRequest req){
        return new ErrorResponseDTO(
                "Erro de formatação no parâmetro da requisição",
                "O valor passado em '%s' não é válido".formatted(exception.getName()),
                req.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now().toString(),
                null
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponseDTO handleError405(HttpRequestMethodNotSupportedException ex, HttpServletRequest req) {
        return new ErrorResponseDTO(
                "Método HTTP não permitido",
                "O método '%s' não é suportado para este endpoint".formatted(ex.getMethod()),
                req.getRequestURI(),
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                LocalDateTime.now().toString(),
                null
        );
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponseDTO handleError422(BusinessException ex, HttpServletRequest req) {
        return new ErrorResponseDTO(
                "Erro de negócio",
                ex.getMessage(),
                req.getRequestURI(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                LocalDateTime.now().toString(),
                null
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleError404(ResourceNotFoundException ex, HttpServletRequest req){
        return new ErrorResponseDTO(
                "Erro de busca",
                ex.getMessage(),
                req.getRequestURI(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now().toString(),
                null
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO handleInternalServerError(Exception ex, HttpServletRequest req) {
        return new ErrorResponseDTO(
                "Erro Interno do Servidor",
                "Ocorreu um erro inesperado no sistema. Tente novamente mais tarde.",
                req.getRequestURI(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now().toString(),
                null
        );
    }
}
