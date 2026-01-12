package com.psu.vet_clinic.repository;

import com.psu.vet_clinic.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий для работы с сущностью MedicalRecord (Медицинская запись).
 * Наследует JpaRepository, предоставляя стандартные CRUD-операции и методы для работы с данными.
 */
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer> {

    /**
     * Находит все медицинские записи для указанного животного.
     *
     * @param animalId Идентификатор животного
     * @return Список медицинских записей, связанных с указанным животным
     */
    List<MedicalRecord> findByAnimalId(Integer animalId);

}