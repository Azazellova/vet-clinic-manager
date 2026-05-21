package com.psu.vet_clinic.controller;

import com.psu.vet_clinic.dto.request.MedicalRecordRequestDto;
import com.psu.vet_clinic.dto.response.MedicalRecordResponseDto;
import com.psu.vet_clinic.service.MedicalRecordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления медицинскими записями
 * в ветеринарной клинике.
 * Предоставляет REST API для операций
 * с медицинскими записями о визитах животных.
 */
//FIX_ME: после добавления DTO обновлена логика всех методов
@RestController
@RequestMapping("/api/v1/medical-records")
public class MedicalRecordController {

    /**
     * Сервис для работы с медицинскими записями.
     */
    private final MedicalRecordService medicalRecordService;

    /**
     * Конструктор с внедрением зависимости.
     *
     * @param medicalRecordService
     *        сервис для работы с медицинскими записями
     */
    public MedicalRecordController(
            MedicalRecordService medicalRecordService
    ) {
        this.medicalRecordService = medicalRecordService;
    }

    /**
     * Получает список всех медицинских записей.
     *
     * @return список медицинских записей
     */
    @GetMapping
    public ResponseEntity<List<MedicalRecordResponseDto>>
    findAll() {

        return ResponseEntity.ok(
                medicalRecordService.findAll()
        );
    }

    /**
     * Получает медицинскую запись по идентификатору.
     *
     * @param id идентификатор медицинской записи
     * @return медицинская запись
     */
    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecordResponseDto>
    findById(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                medicalRecordService.findById(id)
        );
    }

    /**
     * Создает новую медицинскую запись.
     *
     * @param dto DTO объект медицинской записи
     * @return созданная медицинская запись
     */
    @PostMapping
    public ResponseEntity<MedicalRecordResponseDto>
    create(
            @Valid
            @RequestBody
            MedicalRecordRequestDto dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(medicalRecordService.create(dto));
    }

    /**
     * Получает все медицинские записи
     * для конкретного животного.
     *
     * @param animalId идентификатор животного
     * @return список медицинских записей
     */
    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<MedicalRecordResponseDto>>
    findByAnimalId(
            @PathVariable Integer animalId
    ) {

        return ResponseEntity.ok(
                medicalRecordService.findByAnimalId(
                        animalId
                )
        );
    }

    /**
     * Обновляет существующую медицинскую запись.
     *
     * @param id идентификатор медицинской записи
     * @param dto DTO объект медицинской записи
     * @return обновленная медицинская запись
     */
    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecordResponseDto>
    update(
            @PathVariable Integer id,
            @Valid
            @RequestBody
            MedicalRecordRequestDto dto
    ) {

        return ResponseEntity.ok(
                medicalRecordService.update(id, dto)
        );
    }

    /**
     * Удаляет медицинскую запись по идентификатору.
     *
     * @param id идентификатор медицинской записи
     * @return пустой ответ
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    delete(
            @PathVariable Integer id
    ) {

        medicalRecordService.delete(id);

        return ResponseEntity.noContent().build();
    }
}