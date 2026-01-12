package com.psu.vet_clinic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Сущность, представляющая медицинскую запись о визите животного в ветеринарную клинику.
 * Содержит информацию о проведенных процедурах, диагнозе и дате визита.
 */
@Entity
@Table(name = "medical_records")
public class MedicalRecord {

    /**
     * Уникальный идентификатор медицинской записи в системе.
     * Генерируется автоматически базой данных при создании записи.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Дата визита животного в ветеринарную клинику.
     * Не может быть в будущем и не может быть null.
     */
    @NotNull(message = "Дата визита обязательна")
    @PastOrPresent(message = "Дата визита не может быть в будущем")
    @Column(name = "visit_date", nullable = false)
    private LocalDate visitDate;

    /**
     * Тип процедуры, проведенной во время визита (например, вакцинация, осмотр, операция).
     * Не может быть пустым и не должен превышать 100 символов.
     */
    @NotBlank(message = "Тип процедуры обязателен")
    @Size(max = 100, message = "Тип процедуры не может превышать 100 символов")
    @Column(name = "procedure_type", nullable = false, length = 100)
    private String procedureType;

    /**
     * Диагноз, поставленный животному во время визита.
     * Не может быть пустым и не должен превышать 500 символов.
     */
    @NotBlank(message = "Диагноз обязателен")
    @Size(max = 500, message = "Диагноз не может превышать 500 символов")
    @Column(name = "diagnosis", nullable = false, length = 500)
    private String diagnosis;

    /**
     * Животное, к которому относится данная медицинская запись.
     * Связь с сущностью Animal. Не может быть null.
     */
    @NotNull(message = "Животное обязательно")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animals_id", nullable = false, foreignKey = @ForeignKey(name = "fk_medical_record_animal"))
    private Animal animal;

    /**
     * Конструктор по умолчанию.
     * Требуется для JPA и Hibernate.
     */
    public MedicalRecord() {}

    /**
     * Конструктор с параметрами для создания новой медицинской записи.
     *
     * @param visitDate Дата визита животного
     * @param procedureType Тип проведенной процедуры
     * @param diagnosis Диагноз, поставленный животному
     * @param animal Животное, к которому относится запись
     */
    public MedicalRecord(LocalDate visitDate, String procedureType, String diagnosis, Animal animal) {
        this.visitDate = visitDate;
        this.procedureType = procedureType;
        this.diagnosis = diagnosis;
        this.animal = animal;
    }

    /** Стандартные геттеры и сеттеры для всех полей */

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }
    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getProcedureType() {
        return procedureType;
    }
    public void setProcedureType(String procedureType) {
        this.procedureType = procedureType;
    }

    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Animal getAnimal() {
        return animal;
    }
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    /**
     * Возвращает строковое представление объекта MedicalRecord.
     * Включает основные поля для отладки и логирования.
     *
     * @return Строковое представление медицинской записи
     */
    @Override
    public String toString() {
        return "MedicalRecord{id=" + id + ", visitDate=" + visitDate +
                ", procedureType='" + procedureType + "', diagnosis='" + diagnosis +
                "', animal=" + (animal != null ? animal.getName() : "null") + "}";
    }
}