package com.psu.vet_clinic.service;

import com.psu.vet_clinic.dto.request.AnimalTypeRequestDto;
import com.psu.vet_clinic.dto.response.AnimalTypeResponseDto;
import com.psu.vet_clinic.entity.AnimalType;
import com.psu.vet_clinic.exception.NotFoundException;
import com.psu.vet_clinic.repository.AnimalTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.psu.vet_clinic.util.TextNormalizer.capitalize;

/**
 * Сервис для работы с типами животных в ветеринарной клинике.
 * Обеспечивает бизнес-логику операций CRUD для сущности AnimalType.
 */
//FIX_ME: после добавления DTO обновлена логика всех методов
@Service
public class AnimalTypeService {

    /**
     * Репозиторий для работы с данными типов животных.
     */
    private final AnimalTypeRepository animalTypeRepository;

    /**
     * Конструктор с внедрением зависимости репозитория.
     *
     * @param animalTypeRepository репозиторий типов животных
     */
    public AnimalTypeService(
            AnimalTypeRepository animalTypeRepository
    ) {
        this.animalTypeRepository = animalTypeRepository;
    }

    /**
     * Получает список всех типов животных в системе.
     *
     * @return список всех типов животных
     */
    public List<AnimalTypeResponseDto> findAll() {

        return animalTypeRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Находит тип животного по идентификатору.
     *
     * @param id идентификатор типа животного
     * @return найденный тип животного
     * @throws NotFoundException если тип животного не найден
     */
    public AnimalTypeResponseDto findById(Integer id) {

        AnimalType animalType = animalTypeRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Тип животного не найден"
                        ));

        return toDto(animalType);
    }

    /**
     * Сохраняет тип животного в системе.
     *
     * @param dto DTO объект типа животного
     * @return сохраненный тип животного
     */
    @Transactional
    public AnimalTypeResponseDto create(
            AnimalTypeRequestDto dto
    ) {

        AnimalType animalType = new AnimalType();

        animalType.setName(
                capitalize(dto.getName())
        );

        animalType.setAverageLifespan(
                dto.getAverageLifespan()
        );

        AnimalType savedAnimalType =
                animalTypeRepository.save(animalType);

        return toDto(savedAnimalType);
    }

    /**
     * Обновляет информацию о типе животного.
     *
     * @param id идентификатор типа животного
     * @param dto DTO объект типа животного
     * @return обновленный тип животного
     */
    @Transactional
    public AnimalTypeResponseDto update(
            Integer id,
            AnimalTypeRequestDto dto
    ) {

        AnimalType animalType = animalTypeRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Тип животного не найден"
                        ));

        animalType.setName(
                capitalize(dto.getName())
        );

        animalType.setAverageLifespan(
                dto.getAverageLifespan()
        );

        AnimalType updatedAnimalType =
                animalTypeRepository.save(animalType);

        return toDto(updatedAnimalType);
    }

    /**
     * Удаляет тип животного из системы.
     *
     * @param id идентификатор типа животного
     */
    @Transactional
    public void delete(Integer id) {

        animalTypeRepository.deleteById(id);
    }

    /**
     * Преобразует сущность AnimalType в DTO.
     *
     * @param animalType сущность типа животного
     * @return DTO объект типа животного
     */
    private AnimalTypeResponseDto toDto(
            AnimalType animalType
    ) {

        AnimalTypeResponseDto dto =
                new AnimalTypeResponseDto();

        dto.setId(animalType.getId());

        dto.setName(animalType.getName());

        dto.setAverageLifespan(
                animalType.getAverageLifespan()
        );

        return dto;
    }
}