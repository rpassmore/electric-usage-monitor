package com.rpassmore.projects;

import com.rpassmore.projects.dto.Customer;
import com.rpassmore.projects.dto.CustomerRepository;
import com.rpassmore.projects.dto.ElectricReading;
import com.rpassmore.projects.dto.ElectricReadingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootApplication
@EnableScheduling
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner loadData(ElectricReadingRepository repository) {
		return (args) -> {
			repository.save(new ElectricReading(13d, LocalDateTime.now(ZoneId.systemDefault()).minusMinutes(60)));
			repository.save(new ElectricReading(56d, LocalDateTime.now(ZoneId.systemDefault()).minusMinutes(50)));
			repository.save(new ElectricReading(70d, LocalDateTime.now(ZoneId.systemDefault()).minusMinutes(40)));
			repository.save(new ElectricReading(65d, LocalDateTime.now(ZoneId.systemDefault()).minusMinutes(30)));
			repository.save(new ElectricReading(65d, LocalDateTime.now(ZoneId.systemDefault()).minusMinutes(20)));
			repository.save(new ElectricReading(34d, LocalDateTime.now(ZoneId.systemDefault()).minusMinutes(10)));
			repository.save(new ElectricReading(19d, LocalDateTime.now(ZoneId.systemDefault())));

			repository.findAll().forEach(reading -> log.info(reading.toString()));
		};
	}

	@Bean
	public CommandLineRunner loadCustomerData(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findOne(1L);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository
					.findByLastNameStartsWithIgnoreCase("Bauer")) {
				log.info(bauer.toString());
			}
			log.info("");
		};
	}

}
