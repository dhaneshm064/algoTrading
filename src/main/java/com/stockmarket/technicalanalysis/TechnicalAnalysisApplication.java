package com.stockmarket.technicalanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.stockmarket.technicalanalysis.*")

public class TechnicalAnalysisApplication {

	public static void main(String[] args) {

		SpringApplication.run(TechnicalAnalysisApplication.class, args);

	}

}
