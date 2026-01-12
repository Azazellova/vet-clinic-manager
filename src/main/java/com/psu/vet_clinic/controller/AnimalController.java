package com.psu.vet_clinic.controller;

import com.psu.vet_clinic.entity.Animal;
import com.psu.vet_clinic.entity.AnimalType;
import com.psu.vet_clinic.service.AnimalService;
import com.psu.vet_clinic.service.AnimalTypeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления животными в ветеринарной клинике.
 * Предоставляет REST API для выполнения операций CRUD над животными.
 */
@RestController
@RequestMapping("/animals")
public class AnimalController {

    /**
     * Сервис для работы с животными
     */
    private final AnimalService animalService;

    /**
     * Сервис для работы с типами животных
     */
    private final AnimalTypeService animalTypeService;

    /**
     * Конструктор с внедрением зависимостей.
     *
     * @param animalService Сервис для работы с животными
     * @param animalTypeService Сервис для работы с типами животных
     */
    public AnimalController(AnimalService animalService, AnimalTypeService animalTypeService) {
        this.animalService = animalService;
        this.animalTypeService = animalTypeService;
    }

    /**
     * Получает список всех животных в системе.
     *
     * @return Список всех животных
     */
    @GetMapping
    public List<Animal> findAll() {
        return animalService.findAll();
    }

    /**
     * Получает информацию о животном по его идентификатору.
     *
     * @param id Идентификатор животного
     * @return Объект животного с указанным идентификатором
     */
    @GetMapping("/{id}")
    public Animal findById(@PathVariable Integer id) {
        return animalService.findById(id);
    }

    /**
     * Создает новое животное в системе.
     * Перед сохранением проверяет существование типа животного.
     *
     * @param animal Объект животного для создания
     * @return Созданное животное с присвоенным идентификатором
     */
    @PostMapping
    public Animal create(@Valid @RequestBody Animal animal) {
        AnimalType type = animalTypeService.findById(animal.getAnimalType().getId());
        animal.setAnimalType(type);
        return animalService.save(animal);
    }

    /**
     * Обновляет информацию о существующем животном.
     * Проверяет существование животного и типа животного перед обновлением.
     *
     * @param id Идентификатор животного для обновления
     * @param animal Новые данные животного
     * @return Обновленный объект животного
     */
    @PutMapping("/{id}")
    public Animal update(@PathVariable Integer id, @Valid @RequestBody Animal animal) {
        animalService.findById(id);
        animal.setId(id);

        AnimalType type = animalTypeService.findById(animal.getAnimalType().getId());
        animal.setAnimalType(type);

        return animalService.save(animal);
    }

    /**
     * Удаляет животное из системы по идентификатору.
     *
     * @param id Идентификатор животного для удаления
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        animalService.delete(id);
    }
}