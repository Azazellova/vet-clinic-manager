package com.psu.vet_clinic.exception;

/**
 * Исключение, которое выбрасывается при попытке доступа к несуществующему ресурсу.
 * Используется для обработки ситуаций, когда запрашиваемый объект не найден в системе.
 */
public class NotFoundException extends RuntimeException {
    /**
     * Создает новое исключение с указанным сообщением об ошибке.
     *
     * @param message Сообщение об ошибке, которое будет отображено пользователю
     */
    public NotFoundException(String message) {
        super(message);
    }
}