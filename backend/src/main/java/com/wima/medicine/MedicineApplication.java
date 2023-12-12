package com.wima.medicine;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import com.wima.medicine.service.*;

@SpringBootApplication
public class MedicineApplication {

	public static void main(String[] args) {

		SpringApplication.run(MedicineApplication.class, args);
		server.main();
	}
}
