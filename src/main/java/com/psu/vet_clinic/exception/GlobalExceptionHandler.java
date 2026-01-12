package com.psu.vet_clinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * Глобальный обработчик исключений для REST API ветеринарной клиники.
 * Обрабатывает исключения, возникающие в контроллерах, и возвращает соответствующие HTTP-статусы и сообщения.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обрабатывает исключение NotFoundException.
     * Возвращает HTTP статус 404 (Not Found) с сообщением об ошибке.
     *
     * @param ex Исключение NotFoundException
     * @return Сообщение об ошибке
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(NotFoundException ex) {
        return ex.getMessage();
    }

    /**
     * Обрабатывает исключения валидации данных.
     * Возвращает HTTP статус 400 (Bad Request) с сообщениями об ошибках валидации.
     *
     * @param ex Исключение MethodArgumentNotValidException
     * @return Строка с сообщениями об ошибках валидации, объединенными через точку с запятой
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidation(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining("; "));
    }

    /**
     * Обрабатывает все остальные непредвиденные исключения.
     * Возвращает HTTP статус 500 (Internal Server Error) с сообщением об ошибке.
     *
     * @param ex Исключение любого типа
     * @return Сообщение об ошибке
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleOther(Exception ex) {
        return ex.getMessage();
    }
}