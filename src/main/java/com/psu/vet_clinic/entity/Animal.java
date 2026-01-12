package com.psu.vet_clinic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * Сущность, представляющая животное, которое обращается в ветеринарную клинику.
 * Содержит основную информацию о животном для ведения учета и медицинской документации.
 */
@Entity
@Table(name = "animals")
public class Animal {

    /**
     * Уникальный идентификатор животного в системе.
     * Генерируется автоматически базой данных при создании записи.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Кличка животного.
     * Должна содержать от 2 до 50 символов и не может быть пустой.
     */
    @NotBlank(message = "Имя животного обязательно")
    @Size(min = 2, max = 50, message = "Имя должно содержать от 2 до 50 символов")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * Возраст животного в годах.
     * Должен быть в диапазоне от 0 до 100 лет.
     */
    @Min(value = 0, message = "Возраст не может быть отрицательным")
    @Max(value = 100, message = "Возраст не может превышать 100 лет")
    @Column(name = "age", nullable = false)
    private Integer age;

    /**
     * Порода животного.
     * Не может быть пустой строкой.
     */
    @NotBlank(message = "Порода обязательна")
    @Column(name = "breed", nullable = false, length = 100)
    private String breed;

    /**
     * Пол животного.
     * Допустимые значения: "мужской" или "женский".
     */
    @NotBlank(message = "Пол обязателен")
    @Pattern(regexp = "^(мужской|женский)$", message = "Пол должен быть 'мужской' или 'женский'")
    @Column(name = "gender", nullable = false, length = 10)
    private String gender;

    /**
     * Вес животного в килограммах.
     * Должен быть в диапазоне от 0.01 до 1000.00 кг с точностью до двух знаков после запятой.
     */
    @DecimalMin(value = "0.01", message = "Вес должен быть положительным")
    @DecimalMax(value = "1000.00", message = "Вес не может превышать 1000 кг")
    @Digits(integer = 4, fraction = 2, message = "Некорректный формат веса")
    @Column(name = "weight", nullable = false, precision = 5, scale = 2)
    private BigDecimal weight;

    /**
     * Окрас животного.
     * Не может быть пустой строкой.
     */
    @NotBlank(message = "Цвет обязателен")
    @Column(name = "color", nullable = false, length = 50)
    private String color;

    /**
     * Тип животного (вид), к которому относится данное животное.
     * Связь с сущностью AnimalType. Не может быть null.
     */
    @NotNull(message = "Тип животного обязателен")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_types_id", nullable = false)
    private AnimalType animalType;

    /**
     * Конструктор по умолчанию.
     * Требуется для JPA и Hibernate.
     */
    public Animal() {}

    /**
     * Конструктор с параметрами для создания нового животного.
     *
     * @param name Кличка животного
     * @param age Возраст животного в годах
     * @param breed Порода животного
     * @param gender Пол животного ("мужской" или "женский")
     * @param weight Вес животного в килограммах
     * @param color Окрас животного
     * @param animalType Тип животного (вид)
     */
    public Animal(String name, Integer age, String breed, String gender,
                  BigDecimal weight, String color, AnimalType animalType) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.gender = gender;
        this.weight = weight;
        this.color = color;
        this.animalType = animalType;
    }

    /** Стандартные геттеры и сеттеры для всех полей*/

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

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public BigDecimal getWeight() {
        return weight;
    }
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }
    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    /**
     * Возвращает строковое представление объекта Animal.
     * Включает основные поля для отладки и логирования.
     *
     * @return Строковое представление животного
     */
    @Override
    public String toString() {
        return "Animal{id=" + id + ", name='" + name + "', age=" + age +
                ", breed='" + breed + "', gender='" + gender +
                "', weight=" + weight + ", color='" + color +
                "', animalType=" + (animalType != null ? animalType.getName() : "null") + "}";
    }
}