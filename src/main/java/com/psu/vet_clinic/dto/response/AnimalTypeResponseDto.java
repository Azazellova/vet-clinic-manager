package com.psu.vet_clinic.dto.response;

/**
 * DTO для ответов о типе животных
 */
public class AnimalTypeResponseDto {

    private Integer id;

    private String name;

    private Integer averageLifespan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAverageLifespan() {
        return averageLifespan;
    }

    public void setAverageLifespan(Integer averageLifespan) {
        this.averageLifespan = averageLifespan;
    }
}