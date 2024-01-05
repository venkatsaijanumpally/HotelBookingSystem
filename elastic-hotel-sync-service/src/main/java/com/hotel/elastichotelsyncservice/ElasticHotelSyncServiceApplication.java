package com.hotel.elastichotelsyncservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication()
public class ElasticHotelSyncServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticHotelSyncServiceApplication.class, args);
	}

}
