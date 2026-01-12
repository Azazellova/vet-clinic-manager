package com.psu.vet_clinic.service;

import com.psu.vet_clinic.entity.Animal;
import com.psu.vet_clinic.exception.NotFoundException;
import com.psu.vet_clinic.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.psu.vet_clinic.util.TextNormalizer.capitalize;

/**
 * Сервис для работы с животными в ветеринарной клинике.
 * Обеспечивает бизнес-логику операций CRUD для сущности Animal.
 */
@Service
public class AnimalService {

    /**
     * Репозиторий для работы с данными животных
     */
    private final AnimalRepository repository;

    /**
     * Конструктор с внедрением зависимости репозитория.
     *
     * @param repository Репозиторий для работы с животными
     */
    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }

    /**
     * Получает список всех животных в системе.
     *
     * @return Список всех животных
     */
    public List<Animal> findAll() {
        return repository.findAll();
    }

    /**
     * Находит животное по его идентификатору.
     *
     * @param id Идентификатор животного
     * @return Найденное животное
     * @throws NotFoundException Если животное с указанным идентификатором не найдено
     */
    public Animal findById(Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Animal not found: " + id));
    }

    /**
     * Сохраняет животное в системе.
     * Перед сохранением выполняет нормализацию строковых полей:
     * - Имя, порода и цвет приводятся к виду с заглавной первой буквой
     * - Пол приводится к нижнему регистру
     *
     * @param animal Объект животного для сохранения
     * @return Сохраненное животное
     */
    public Animal save(Animal animal) {
        animal.setName(capitalize(animal.getName()));
        animal.setBreed(capitalize(animal.getBreed()));
        animal.setColor(capitalize(animal.getColor()));
        animal.setGender(animal.getGender().toLowerCase());

        return repository.save(animal);
    }

    /**
     * Удаляет животное из системы по идентификатору.
     *
     * @param id Идентификатор животного для удаления
     */
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}