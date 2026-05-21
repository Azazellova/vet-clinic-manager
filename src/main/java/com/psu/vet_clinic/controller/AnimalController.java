package com.psu.vet_clinic.controller;

import com.psu.vet_clinic.dto.request.AnimalRequestDto;
import com.psu.vet_clinic.dto.response.AnimalResponseDto;
import com.psu.vet_clinic.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления животными в ветеринарной клинике.
 * Предоставляет REST API для выполнения операций CRUD над животными.
 */
//FIX_ME: после добавления DTO обновлена логика всех методов
@RestController
@RequestMapping("/api/v1/animals")
public class AnimalController {

    /**
     * Сервис для работы с животными.
     */
    private final AnimalService animalService;

    /**
     * Конструктор с внедрением зависимости.
     *
     * @param animalService сервис для работы с животными
     */
    public AnimalController(
            AnimalService animalService
    ) {
        this.animalService = animalService;
    }

    /**
     * Получает список всех животных в системе.
     *
     * @return список всех животных
     */
    @GetMapping
    public ResponseEntity<List<AnimalResponseDto>>
    findAll() {

        return ResponseEntity.ok(
                animalService.findAll()
        );
    }

    /**
     * Получает информацию о животном по идентификатору.
     *
     * @param id идентификатор животного
     * @return объект животного
     */
    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponseDto>
    findById(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                animalService.findById(id)
        );
    }

    /**
     * Создает новое животное в системе.
     *
     * @param dto DTO объект животного
     * @return созданное животное
     */
    @PostMapping
    public ResponseEntity<AnimalResponseDto>
    create(
            @Valid
            @RequestBody
            AnimalRequestDto dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(animalService.create(dto));
    }

    /**
     * Обновляет информацию о существующем животном.
     *
     * @param id идентификатор животного
     * @param dto DTO объект животного
     * @return обновленное животное
     */
    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponseDto>
    update(
            @PathVariable Integer id,
            @Valid
            @RequestBody
            AnimalRequestDto dto
    ) {

        return ResponseEntity.ok(
                animalService.update(id, dto)
        );
    }

    /**
     * Удаляет животное из системы по идентификатору.
     *
     * @param id идентификатор животного
     * @return пустой ответ
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    delete(@PathVariable Integer id) {

        animalService.delete(id);

        return ResponseEntity.noContent().build();
    }
}