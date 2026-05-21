package com.psu.vet_clinic.controller;

import com.psu.vet_clinic.dto.request.AnimalTypeRequestDto;
import com.psu.vet_clinic.dto.response.AnimalTypeResponseDto;
import com.psu.vet_clinic.service.AnimalTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления типами животных в ветеринарной клинике.
 * Предоставляет REST API для операций с типами животных.
 */
//FIX_ME: после добавления DTO обновлена логика всех методов
@RestController
@RequestMapping("/api/v1/animal-types")
public class AnimalTypeController {

    /**
     * Сервис для работы с типами животных.
     */
    private final AnimalTypeService animalTypeService;

    /**
     * Конструктор с внедрением зависимости.
     *
     * @param animalTypeService сервис для работы с типами животных
     */
    public AnimalTypeController(
            AnimalTypeService animalTypeService
    ) {
        this.animalTypeService = animalTypeService;
    }

    /**
     * Получает список всех типов животных.
     *
     * @return список типов животных
     */
    @GetMapping
    public ResponseEntity<List<AnimalTypeResponseDto>>
    findAll() {

        return ResponseEntity.ok(
                animalTypeService.findAll()
        );
    }

    /**
     * Получает тип животного по идентификатору.
     *
     * @param id идентификатор типа животного
     * @return объект типа животного
     */
    @GetMapping("/{id}")
    public ResponseEntity<AnimalTypeResponseDto>
    findById(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                animalTypeService.findById(id)
        );
    }

    /**
     * Создает новый тип животного.
     *
     * @param dto DTO объект типа животного
     * @return созданный тип животного
     */
    @PostMapping
    public ResponseEntity<AnimalTypeResponseDto>
    create(
            @Valid
            @RequestBody
            AnimalTypeRequestDto dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(animalTypeService.create(dto));
    }

    /**
     * Обновляет информацию о типе животного.
     *
     * @param id идентификатор типа животного
     * @param dto DTO объект типа животного
     * @return обновленный тип животного
     */
    @PutMapping("/{id}")
    public ResponseEntity<AnimalTypeResponseDto>
    update(
            @PathVariable Integer id,
            @Valid
            @RequestBody
            AnimalTypeRequestDto dto
    ) {

        return ResponseEntity.ok(
                animalTypeService.update(id, dto)
        );
    }

    /**
     * Удаляет тип животного по идентификатору.
     *
     * @param id идентификатор типа животного
     * @return пустой ответ
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    delete(@PathVariable Integer id) {

        animalTypeService.delete(id);

        return ResponseEntity.noContent().build();
    }
}