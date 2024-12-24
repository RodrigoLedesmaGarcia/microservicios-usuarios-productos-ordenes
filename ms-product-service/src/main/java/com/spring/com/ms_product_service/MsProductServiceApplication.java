package com.spring.com.ms_product_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProductServiceApplication.class, args);
	}

}
