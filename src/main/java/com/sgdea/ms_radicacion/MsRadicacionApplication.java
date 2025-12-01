package com.sgdea.ms_radicacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sgdea.ms_radicacion", "com.sgdea.commons"})
public class MsRadicacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsRadicacionApplication.class, args);
	}

}