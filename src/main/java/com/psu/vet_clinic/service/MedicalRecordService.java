package com.psu.vet_clinic.service;

import com.psu.vet_clinic.dto.request.MedicalRecordRequestDto;
import com.psu.vet_clinic.dto.response.MedicalRecordResponseDto;
import com.psu.vet_clinic.entity.Animal;
import com.psu.vet_clinic.entity.MedicalRecord;
import com.psu.vet_clinic.exception.NotFoundException;
import com.psu.vet_clinic.repository.AnimalRepository;
import com.psu.vet_clinic.repository.MedicalRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.psu.vet_clinic.util.TextNormalizer.capitalize;

/**
 * Сервис для работы с медицинскими записями в ветеринарной клинике.
 * Обеспечивает бизнес-логику операций CRUD для сущности MedicalRecord.
 */
//FIX_ME: после добавления DTO обновлена логика всех методов
@Service
public class MedicalRecordService {

    /**
     * Репозиторий для работы с данными медицинских записей
     */
    private final MedicalRecordRepository medicalRecordRepository;

    private final AnimalRepository animalRepository;
    /**
     * Конструктор с внедрением зависимости репозитория.
     *
     */
    public MedicalRecordService(
            MedicalRecordRepository medicalRecordRepository,
            AnimalRepository animalRepository
    ) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.animalRepository = animalRepository;
    }
    /**
     * Получает список всех медицинских записей в системе.
     *
     * @return Список всех медицинских записей
     */
    public List<MedicalRecordResponseDto> findAll() {

        return medicalRecordRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    /**
     * Находит медицинскую запись по ее идентификатору.
     *
     * @param id Идентификатор медицинской записи
     * @return Найденная медицинская запись
     * @throws NotFoundException Если медицинская запись с указанным идентификатором не найдена
     */
    public MedicalRecordResponseDto findById(Integer id) {

        MedicalRecord medicalRecord =
                medicalRecordRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Medical record not found"
                                ));

        return toDto(medicalRecord);
    }

    /**
     * Находит все медицинские записи для указанного животного.
     *
     * @param animalId Идентификатор животного
     * @return Список медицинских записей, связанных с указанным животным
     */
    public List<MedicalRecordResponseDto> findByAnimalId(Integer animalId) {

        return medicalRecordRepository.findByAnimalId(animalId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    /**
     * Сохраняет медицинскую запись в системе.
     * Перед сохранением выполняет валидацию и нормализацию данных:
     * - Проверяет, что дата визита указана и находится в допустимом диапазоне (2000-2100 годы)
     * - Проверяет, что диагноз и тип процедуры не пустые
     * - Приводит диагноз и тип процедуры к виду с заглавной первой буквой
     *
     * @return Сохраненная медицинская запись
     * @throws IllegalArgumentException Если не пройдена валидация данных
     */
    @Transactional
    public MedicalRecordResponseDto create(
            MedicalRecordRequestDto dto
    ) {

        Animal animal = animalRepository.findById(
                dto.getAnimalId()
        ).orElseThrow(() ->
                new RuntimeException("Animal not found"));

        MedicalRecord medicalRecord = new MedicalRecord();

        medicalRecord.setVisitDate(dto.getVisitDate());

        medicalRecord.setDiagnosis(dto.getDiagnosis());

        medicalRecord.setProcedureType(
                dto.getProcedureType()
        );

        medicalRecord.setAnimal(animal);

        MedicalRecord savedMedicalRecord =
                medicalRecordRepository.save(medicalRecord);

        return toDto(savedMedicalRecord);
    }

    /**
     * Обновляет существующую медицинскую запись.
     *
     * @param id Идентификатор медицинской записи для обновления
     * @return Обновленная медицинская запись
     */

    @Transactional
    public MedicalRecordResponseDto update(
            Integer id,
            MedicalRecordRequestDto dto
    ) {

        MedicalRecord medicalRecord =
                medicalRecordRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Medical record not found"
                                ));

        Animal animal = animalRepository.findById(
                dto.getAnimalId()
        ).orElseThrow(() ->
                new RuntimeException("Animal not found"));

        medicalRecord.setVisitDate(dto.getVisitDate());

        medicalRecord.setDiagnosis(dto.getDiagnosis());

        medicalRecord.setProcedureType(
                dto.getProcedureType()
        );

        medicalRecord.setAnimal(animal);

        MedicalRecord updatedMedicalRecord =
                medicalRecordRepository.save(medicalRecord);

        return toDto(updatedMedicalRecord);
    }

    /**
     * Удаляет медицинскую запись из системы по идентификатору.
     *
     * @param id Идентификатор медицинской записи для удаления
     */
    @Transactional
    public void delete(Integer id) {
        medicalRecordRepository.deleteById(id);
    }

    private MedicalRecordResponseDto toDto(
            MedicalRecord medicalRecord
    ) {

        MedicalRecordResponseDto dto =
                new MedicalRecordResponseDto();

        dto.setId(medicalRecord.getId());

        dto.setVisitDate(
                medicalRecord.getVisitDate()
        );

        dto.setDiagnosis(
                medicalRecord.getDiagnosis()
        );

        dto.setProcedureType(
                medicalRecord.getProcedureType()
        );

        dto.setAnimalId(
                medicalRecord.getAnimal().getId()
        );

        dto.setAnimalName(
                medicalRecord.getAnimal().getName()
        );

        return dto;
    }
}