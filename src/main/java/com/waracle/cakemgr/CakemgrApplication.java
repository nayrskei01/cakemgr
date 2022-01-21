package com.waracle.cakemgr;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CakemgrApplication {

	/**
	 * Entry point for the Application.
	 *
	 * @param args
	 */
	public static void main(final String[] args) {
		SpringApplication.run(CakemgrApplication.class, args);
	}

	/**
	 * Bean type for ModelMapper.
	 *
	 * @return modelMapper
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
