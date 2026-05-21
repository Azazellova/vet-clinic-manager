package com.psu.vet_clinic.service;

import com.psu.vet_clinic.dto.request.AnimalRequestDto;
import com.psu.vet_clinic.dto.response.AnimalResponseDto;
import com.psu.vet_clinic.entity.Animal;
import com.psu.vet_clinic.entity.AnimalType;
import com.psu.vet_clinic.exception.NotFoundException;
import com.psu.vet_clinic.repository.AnimalRepository;
import com.psu.vet_clinic.repository.AnimalTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.psu.vet_clinic.util.TextNormalizer.capitalize;

/**
 * Сервис для работы с животными в ветеринарной клинике.
 * Обеспечивает бизнес-логику операций CRUD для сущности Animal.
 */
//FIX_ME: после добавления DTO обновлена логика всех методов
@Service
public class AnimalService {

    /**
     * Репозиторий для работы с данными животных.
     */
    //FIX_ME: название переменной обобщенное
    //private final AnimalRepository repository;
    private final AnimalRepository animalRepository;

    /**
     * Репозиторий для работы с типами животных.
     */
    private final AnimalTypeRepository animalTypeRepository;

    /**
     * Конструктор с внедрением зависимостей репозиториев.
     *
     * @param animalRepository репозиторий животных
     * @param animalTypeRepository репозиторий типов животных
     */
    public AnimalService(
            AnimalRepository animalRepository,
            AnimalTypeRepository animalTypeRepository
    ) {
        this.animalRepository = animalRepository;
        this.animalTypeRepository = animalTypeRepository;
    }

    /**
     * Получает список всех животных в системе.
     *
     * @return список всех животных
     */
    public List<AnimalResponseDto> findAll() {

        return animalRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Находит животное по его идентификатору.
     *
     * @param id идентификатор животного
     * @return найденное животное
     * @throws NotFoundException если животное не найдено
     */
    public AnimalResponseDto findById(Integer id) {

        Animal animal = animalRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Животное не найдено"
                        ));

        return toDto(animal);
    }

    /**
     * Сохраняет животное в системе.
     * Перед сохранением выполняет нормализацию строковых полей.
     *
     * @param dto DTO объект животного
     * @return сохраненное животное
     */
    @Transactional
    public AnimalResponseDto create(
            AnimalRequestDto dto
    ) {

        AnimalType animalType = animalTypeRepository.findById(
                dto.getAnimalTypeId()
        ).orElseThrow(() ->
                new NotFoundException(
                        "Тип животного не найден"
                ));

        Animal animal = new Animal();

        animal.setName(capitalize(dto.getName()));
        animal.setAge(dto.getAge());
        animal.setBreed(capitalize(dto.getBreed()));
        animal.setGender(dto.getGender().toLowerCase());
        animal.setWeight(dto.getWeight());
        animal.setColor(capitalize(dto.getColor()));

        animal.setAnimalType(animalType);

        Animal savedAnimal = animalRepository.save(animal);

        return toDto(savedAnimal);
    }

    /**
     * Обновляет информацию о животном.
     *
     * @param id идентификатор животного
     * @param dto DTO объект животного
     * @return обновленное животное
     */
    @Transactional
    public AnimalResponseDto update(
            Integer id,
            AnimalRequestDto dto
    ) {

        Animal animal = animalRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Животное не найдено"
                        ));

        AnimalType animalType = animalTypeRepository.findById(
                dto.getAnimalTypeId()
        ).orElseThrow(() ->
                new NotFoundException(
                        "Тип животного не найден"
                ));

        animal.setName(capitalize(dto.getName()));
        animal.setAge(dto.getAge());
        animal.setBreed(capitalize(dto.getBreed()));
        animal.setGender(dto.getGender().toLowerCase());
        animal.setWeight(dto.getWeight());
        animal.setColor(capitalize(dto.getColor()));

        animal.setAnimalType(animalType);

        Animal updatedAnimal = animalRepository.save(animal);

        return toDto(updatedAnimal);
    }

    /**
     * Удаляет животное из системы по идентификатору.
     *
     * @param id идентификатор животного
     */
    @Transactional
    public void delete(Integer id) {

        animalRepository.deleteById(id);
    }

    /**
     * Преобразует сущность Animal в DTO.
     *
     * @param animal сущность животного
     * @return DTO объект животного
     */
    private AnimalResponseDto toDto(
            Animal animal
    ) {

        AnimalResponseDto dto = new AnimalResponseDto();

        dto.setId(animal.getId());
        dto.setName(animal.getName());
        dto.setAge(animal.getAge());
        dto.setBreed(animal.getBreed());
        dto.setGender(animal.getGender());
        dto.setWeight(animal.getWeight());
        dto.setColor(animal.getColor());

        dto.setAnimalTypeId(
                animal.getAnimalType().getId()
        );

        dto.setAnimalTypeName(
                animal.getAnimalType().getName()
        );

        return dto;
    }
}