package com.psu.vet_clinic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Сущность, представляющая тип (вид) животного в ветеринарной клинике.
 * Используется для классификации животных по видам (например, собака, кошка и т.д.).
 */
@Entity
@Table(name = "animal_types")
public class AnimalType {

    /**
     * Уникальный идентификатор типа животного в системе.
     * Генерируется автоматически базой данных при создании записи.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Название типа животного (например, "Собака", "Кошка", "Попугай").
     * Не может быть пустой строкой.
     */
    @NotBlank(message = "Название типа животного обязательно")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /**
     * Средняя продолжительность жизни для данного типа животных в годах.
     * Может быть нулевым значением, если данные отсутствуют.
     * Должна быть положительной или равной нулю.
     */
    @PositiveOrZero(message = "Средняя продолжительность жизни должна быть положительной или нулем")
    @Column(name = "average_lifespan")
    private Integer averageLifespan;

    /**
     * Конструктор по умолчанию.
     * Требуется для JPA и Hibernate.
     */
    public AnimalType() {}

    /**
     * Конструктор с параметрами для создания нового типа животного.
     *
     * @param name Название типа животного
     * @param averageLifespan Средняя продолжительность жизни в годах (может быть null)
     */
    public AnimalType(String name, Integer averageLifespan) {
        this.name = name;
        this.averageLifespan = averageLifespan;
    }

    /** Стандартные геттеры и сеттеры для всех полей */

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAverageLifespan() {
        return averageLifespan;
    }
    public void setAverageLifespan(Integer averageLifespan) {
        this.averageLifespan = averageLifespan;
    }

    /**
     * Возвращает строковое представление объекта AnimalType.
     * Включает основные поля для отладки и логирования.
     *
     * @return Строковое представление типа животного
     */
    @Override
    public String toString() {
        return "AnimalType{id=" + id + ", name='" + name + "', averageLifespan=" + averageLifespan + "}";
    }
}