package com.psu.vet_clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс приложения.
 * Содержит точку входа в приложение и конфигурацию Spring Boot.
 */
@SpringBootApplication
public class VetClinicApplication {

	/**
	 * Точка входа в приложение.
	 * Запускает Spring Boot приложение.
	 *
	 * @param args Аргументы командной строки
	 */
	public static void main(String[] args) {
		SpringApplication.run(VetClinicApplication.class, args);
	}

}