package net.croods.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "net.croods.journalApp.repository")
public class JournaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournaApplication.class, args);
	}

}
