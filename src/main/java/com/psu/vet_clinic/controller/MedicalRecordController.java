package com.psu.vet_clinic.controller;

import com.psu.vet_clinic.entity.MedicalRecord;
import com.psu.vet_clinic.service.AnimalService;
import com.psu.vet_clinic.service.MedicalRecordService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления медицинскими записями в ветеринарной клинике.
 * Предоставляет REST API для операций с медицинскими записями о визитах животных.
 */
@RestController
@RequestMapping("/medical-records")
public class MedicalRecordController {

    /**
     * Сервис для работы с медицинскими записями
     */
    private final MedicalRecordService service;

    /**
     * Сервис для работы с животными
     */
    private final AnimalService animalService;

    /**
     * Конструктор с внедрением зависимостей.
     *
     * @param service Сервис для работы с медицинскими записями
     * @param animalService Сервис для работы с животными
     */
    public MedicalRecordController(MedicalRecordService service,
                                   AnimalService animalService) {
        this.service = service;
        this.animalService = animalService;
    }

    /**
     * Получает список всех медицинских записей в системе.
     *
     * @return Список всех медицинских записей
     */
    @GetMapping
    public List<MedicalRecord> findAll() {
        return service.findAll();
    }

    /**
     * Создает новую медицинскую запись.
     * Перед сохранением проверяет наличие идентификатора животного и загружает полный объект Animal.
     *
     * @param record Объект медицинской записи для создания
     * @return Созданная медицинская запись с присвоенным идентификатором
     * @throws IllegalArgumentException Если идентификатор животного не указан
     */
    @PostMapping
    public MedicalRecord create(@Valid @RequestBody MedicalRecord record) {
        if (record.getAnimal() == null || record.getAnimal().getId() == null) {
            throw new IllegalArgumentException("Animal ID is required");
        }

        record.setAnimal(
                animalService.findById(record.getAnimal().getId())
        );

        return service.save(record);
    }

    /**
     * Получает все медицинские записи для конкретного животного.
     *
     * @param animalId Идентификатор животного
     * @return Список медицинских записей для указанного животного
     */
    @GetMapping("/by-animal/{animalId}")
    public List<MedicalRecord> findByAnimal(@PathVariable Integer animalId) {
        return service.findByAnimalId(animalId);
    }

    /**
     * Обновляет существующую медицинскую запись.
     * При обновлении сохраняет связь с тем же животным, что и в исходной записи.
     *
     * @param id Идентификатор медицинской записи для обновления
     * @return Обновленная медицинская запись
     */

    @PutMapping("/{id}")
    public MedicalRecord update(@PathVariable Integer id,
                                @RequestBody MedicalRecord record) {
        return service.update(id, record);
    }

    /**
     * Удаляет медицинскую запись по идентификатору.
     *
     * @param id Идентификатор медицинской записи для удаления
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}