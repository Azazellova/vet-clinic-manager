# Итоговая работа по дисциплине "Язык программирования Java"
<h2>
  <p>Работа выполнена:</p>
  <p>Мазур София</p>
  <p>7 группа</p>
</h2>

<img width="1919" height="1030" alt="image" src="https://github.com/user-attachments/assets/d25c4aeb-b908-4233-82fa-d36e60f1499d" />


Итоговая работа представляет собой веб-приложение для учёта пациентов ветеринарной клиники.
## Функциональность
- Добавление, редактирование, удаление животных(пациентов)
- Управление типами животных
- Добавление, редактирование, удаление медицинских записей

## Стек технологий

Backend:
- Java
- Spring Boot
- Spring Web (REST)
- Spring Data JPA + Hibernate
- PostgreSQL
  
Frontend:
- HTML
- CSS
- JavaScript(JS)
## Структура таблиц в базе данных
<img width="870" height="440" alt="image" src="https://github.com/user-attachments/assets/4cae2ade-a102-4167-b5c6-7583ce0abbd7" />

## Структура проекта
```
vet-clinic/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/psu/vet_clinic/
│   │   │       │
│   │   │       ├── controller/
│   │   │       │   ├── AnimalController.java
│   │   │       │   ├── AnimalTypeController.java
│   │   │       │   └── MedicalRecordController.java
│   │   │       │
│   │   │       ├── service/
│   │   │       │   ├── AnimalService.java
│   │   │       │   ├── AnimalTypeService.java
│   │   │       │   └── MedicalRecordService.java
│   │   │       │
│   │   │       ├── repository/
│   │   │       │   ├── AnimalRepository.java
│   │   │       │   ├── AnimalTypeRepository.java
│   │   │       │   └── MedicalRecordRepository.java
│   │   │       │
│   │   │       ├── entity/
│   │   │       │   ├── Animal.java
│   │   │       │   ├── AnimalType.java
│   │   │       │   └── MedicalRecord.java
│   │   │       │
│   │   │       ├── exception/
│   │   │       │   ├── NotFoundException.java
│   │   │       │   └── GlobalExceptionHandler.java
│   │   │       │
│   │   │       ├── util/
│   │   │       │   └── TextNormalizer.java
│   │   │       │
│   │   │       └── VetClinicApplication.java
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── index.html
│   │       │   ├── animals.html
│   │       │   ├── animal-types.html
│   │       │   ├── medical-records.html
│   │       │   └── style.css
│   │       └── application.properties
│   │
│   └── test/
│       └── java/
│           └── com/psu/vet_clinic/
│               └── VetClinicApplicationTests.java
├── .gitignore
├── pom.xml
└── README.md
```

