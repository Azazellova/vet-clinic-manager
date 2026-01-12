package com.psu.vet_clinic.service;

import com.psu.vet_clinic.entity.AnimalType;
import com.psu.vet_clinic.exception.NotFoundException;
import com.psu.vet_clinic.repository.AnimalTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.psu.vet_clinic.util.TextNormalizer.capitalize;

/**
 * Сервис для работы с типами животных в ветеринарной клинике.
 * Обеспечивает бизнес-логику операций CRUD для сущности AnimalType.
 */
@Service
public class AnimalTypeService {

    /**
     * Репозиторий для работы с данными типов животных
     */
    private final AnimalTypeRepository repository;

    /**
     * Конструктор с внедрением зависимости репозитория.
     *
     * @param repository Репозиторий для работы с типами животных
     */
    public AnimalTypeService(AnimalTypeRepository repository) {
        this.repository = repository;
    }

    /**
     * Получает список всех типов животных в системе.
     *
     * @return Список всех типов животных
     */
    public List<AnimalType> findAll() {
        return repository.findAll();
    }

    /**
     * Находит тип животного по его идентификатору.
     *
     * @param id Идентификатор типа животного
     * @return Найденный тип животного
     * @throws NotFoundException Если тип животного с указанным идентификатором не найден
     */
    public AnimalType findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("AnimalType not found: " + id));
    }

    /**
     * Сохраняет тип животного в системе.
     * Перед сохранением нормализует название типа, приводя его к виду с заглавной первой буквой.
     *
     * @param type Объект типа животного для сохранения
     * @return Сохраненный тип животного
     */
    public AnimalType save(AnimalType type) {
        if (type.getName() == null || type.getName().isBlank()) {
            throw new IllegalArgumentException("Название типа обязательно");
        }

        type.setName(capitalize(type.getName()));
        return repository.save(type);
    }

    /**
     * Обновляет информацию о типе животного.
     * Находит существующий тип по идентификатору и обновляет его поля.
     *
     * @param id Идентификатор типа животного для обновления
     * @param type Новые данные типа животного (название, средняя продолжительность жизни)
     * @return Обновленный объект типа животного
     */

    public AnimalType update(Integer id, AnimalType type) {
        AnimalType existing = findById(id);

        existing.setName(type.getName());
        existing.setAverageLifespan(type.getAverageLifespan());

        return save(existing);
    }

    /**
     * Удаляет тип животного из системы по идентификатору.
     *
     * @param id Идентификатор типа животного для удаления
     */
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}