package com.learn_spring_full_stack.full_stack_backend;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.learn_spring_full_stack.full_stack_backend.enumeration.Status;
import com.learn_spring_full_stack.full_stack_backend.model.Server;
import com.learn_spring_full_stack.full_stack_backend.repository.ServerRepository;

@SpringBootApplication
public class FullStackBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullStackBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner populateDatabase(ServerRepository serverRepository) {
		final String imageUrl = "http://localhost:8080/server/image/server1.jpg";

		return args -> {
			serverRepository.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "16 GB", "Personal PC",
					imageUrl, Status.SERVER_UP));

			serverRepository.save(new Server(null, "192.168.1.151", "Red Hat", "2 GB", "Super Computer",
					imageUrl, Status.SERVER_DOWN));

			serverRepository.save(new Server(null, "192.16.1.160", "Debian", "16 GB", "Raspberry PIE",
					imageUrl, Status.SERVER_UP));

			serverRepository.save(new Server(null, "192.168.0.160", "Fedora", "4 GB", "Personal PC",
					imageUrl, Status.SERVER_DOWN));
		};
	}

	@Bean
	CorsFilter corsFilter() {

		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();

		CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowCredentials(true);

		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));

		configuration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept",
				"Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method",
				"Access-Control-Request-Headers"));

		configuration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));

		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));

		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);

		return new CorsFilter(urlBasedCorsConfigurationSource);

	}

}
