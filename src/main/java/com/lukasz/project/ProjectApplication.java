package com.lukasz.project;

import com.lukasz.project.database.auth.AuthenticationService;
import com.lukasz.project.database.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
/*
	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	){
		return args -> {
			var admin = RegisterRequest.builder()
					.name("John")
					.surname("Doe")
					.pesel("12345678901")
					.country("USA")
					.email("john.email@example.com")
					.username("john_doe")
					.password("password123")
					.build();

			String token = service.registerAdmin(admin).getToken();
			System.out.println(token);
			var admin2 = RegisterRequest.builder()
					.name("John")
					.surname("Doe")
					.pesel("12345678901")
					.country("USA")
					.email("john.email2@example.com")
					.username("john_doe")
					.password("password123")
					.build();

			String token2 = service.registerAdmin(admin2).getToken();
			System.out.println(token2);
		};
	}
*/

}
