package com.devsuperior.dsmeta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DsmetaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DsmetaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}