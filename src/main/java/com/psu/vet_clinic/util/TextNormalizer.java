package com.psu.vet_clinic.util;

/**
 * Утилитный класс для нормализации текстовых данных.
 * Предоставляет методы для приведения строк к стандартному формату.
 */
public class TextNormalizer {
    private TextNormalizer() {}

    /**
     * Преобразует строку к формату с заглавной первой буквой и остальными строчными.
     * Удаляет начальные и конечные пробелы. Если строка null или пустая, возвращает ее без изменений.
     *
     * @param value Исходная строка для нормализации
     * @return Нормализованная строка (с заглавной первой буквой) или исходное значение, если оно null или пустое
     */
    public static String capitalize(String value) {
        if (value == null || value.isBlank()) return value;

        value = value.trim().toLowerCase();
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }
}