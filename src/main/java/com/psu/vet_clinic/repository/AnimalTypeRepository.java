package com.psu.vet_clinic.repository;

import com.psu.vet_clinic.entity.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с сущностью AnimalType (Тип животного).
 * Наследует JpaRepository, предоставляя стандартные CRUD-операции и методы для работы с данными.
 *
 * <p>Дополнительные методы для поиска можно добавлять при помощи Spring Data JPA.</p>
 */
public interface AnimalTypeRepository extends JpaRepository<AnimalType, Integer> {
}