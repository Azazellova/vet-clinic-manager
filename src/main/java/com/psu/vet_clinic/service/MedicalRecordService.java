package com.psu.vet_clinic.service;

import com.psu.vet_clinic.entity.MedicalRecord;
import com.psu.vet_clinic.exception.NotFoundException;
import com.psu.vet_clinic.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.psu.vet_clinic.util.TextNormalizer.capitalize;

/**
 * Сервис для работы с медицинскими записями в ветеринарной клинике.
 * Обеспечивает бизнес-логику операций CRUD для сущности MedicalRecord.
 */
@Service
public class MedicalRecordService {

    /**
     * Репозиторий для работы с данными медицинских записей
     */
    private final MedicalRecordRepository repository;

    /**
     * Конструктор с внедрением зависимости репозитория.
     *
     * @param repository Репозиторий для работы с медицинскими записями
     */
    public MedicalRecordService(MedicalRecordRepository repository) {
        this.repository = repository;
    }

    /**
     * Получает список всех медицинских записей в системе.
     *
     * @return Список всех медицинских записей
     */
    public List<MedicalRecord> findAll() {
        return repository.findAll();
    }

    /**
     * Находит медицинскую запись по ее идентификатору.
     *
     * @param id Идентификатор медицинской записи
     * @return Найденная медицинская запись
     * @throws NotFoundException Если медицинская запись с указанным идентификатором не найдена
     */
    public MedicalRecord findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("MedicalRecord not found: " + id));
    }

    /**
     * Находит все медицинские записи для указанного животного.
     *
     * @param animalId Идентификатор животного
     * @return Список медицинских записей, связанных с указанным животным
     */
    public List<MedicalRecord> findByAnimalId(Integer animalId) {
        return repository.findByAnimalId(animalId);
    }

    /**
     * Сохраняет медицинскую запись в системе.
     * Перед сохранением выполняет валидацию и нормализацию данных:
     * - Проверяет, что дата визита указана и находится в допустимом диапазоне (2000-2100 годы)
     * - Проверяет, что диагноз и тип процедуры не пустые
     * - Приводит диагноз и тип процедуры к виду с заглавной первой буквой
     *
     * @param record Объект медицинской записи для сохранения
     * @return Сохраненная медицинская запись
     * @throws IllegalArgumentException Если не пройдена валидация данных
     */
    public MedicalRecord save(MedicalRecord record) {
        if (record.getVisitDate() == null) {
            throw new IllegalArgumentException("Дата визита обязательна");
        }

        int year = record.getVisitDate().getYear();
        if (year < 2000 || year > 2100) {
            throw new IllegalArgumentException("Год визита должен быть между 2000 и 2100");
        }

        if (record.getDiagnosis() == null || record.getDiagnosis().isBlank()) {
            throw new IllegalArgumentException("Диагноз обязателен");
        }

        if (record.getProcedureType() == null || record.getProcedureType().isBlank()) {
            throw new IllegalArgumentException("Процедура обязательна");
        }

        record.setDiagnosis(capitalize(record.getDiagnosis()));
        record.setProcedureType(capitalize(record.getProcedureType()));

        return repository.save(record);
    }

    /**
     * Обновляет существующую медицинскую запись.
     *
     * @param id Идентификатор медицинской записи для обновления
     * @param record Новые данные медицинской записи (диагноз, процедура, дата визита)
     * @return Обновленная медицинская запись
     */

    public MedicalRecord update(Integer id, MedicalRecord record) {
        MedicalRecord existing = findById(id);

        record.setId(id);
        record.setAnimal(existing.getAnimal());

        return save(record);
    }

    /**
     * Удаляет медицинскую запись из системы по идентификатору.
     *
     * @param id Идентификатор медицинской записи для удаления
     */
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}