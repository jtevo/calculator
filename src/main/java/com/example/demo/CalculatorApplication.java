package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@java.lang.SuppressWarnings  //("squid:S2078")
public class CalculatorApplication {   //NOSONAR

	public static void main(String[] args) {
		
		SpringApplication.run(CalculatorApplication.class, args); //NOSONAR
	}
}
