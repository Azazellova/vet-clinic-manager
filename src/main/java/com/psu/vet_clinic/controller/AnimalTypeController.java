package com.psu.vet_clinic.controller;

import com.psu.vet_clinic.entity.AnimalType;
import com.psu.vet_clinic.service.AnimalTypeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления типами животных в ветеринарной клинике.
 * Предоставляет REST API для операций с типами животных (видами).
 */
@RestController
@RequestMapping("/animal-types")
public class AnimalTypeController {

    /**
     * Сервис для работы с типами животных
     */
    private final AnimalTypeService service;

    /**
     * Конструктор с внедрением зависимости.
     *
     * @param service Сервис для работы с типами животных
     */
    public AnimalTypeController(AnimalTypeService service) {
        this.service = service;
    }

    /**
     * Получает список всех типов животных, зарегистрированных в системе.
     *
     * @return Список всех типов животных
     */
    @GetMapping
    public List<AnimalType> findAll() {
        return service.findAll();
    }

    /**
     * Создает новый тип животного в системе.
     *
     * @param type Объект типа животного для создания
     * @return Созданный тип животного с присвоенным идентификатором
     */
    @PostMapping
    public AnimalType create(@Valid @RequestBody AnimalType type) {
        return service.save(type);
    }

    /**
     * Обновляет информацию о типе животного.
     * Использует сервис для выполнения обновления с валидацией.
     *
     * @param id Идентификатор типа животного для обновления
     * @param type Новые данные типа животного
     * @return Обновленный объект типа животного
     */

    @PutMapping("/{id}")
    public AnimalType update(
            @PathVariable Integer id,
            @Valid @RequestBody AnimalType type
    ) {
        return service.update(id, type);
    }

    /**
     * Удаляет тип животного из системы по идентификатору.
     * Примечание: удаление типа животного может быть ограничено, если существуют животные этого типа.
     *
     * @param id Идентификатор типа животного для удаления
     */


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}