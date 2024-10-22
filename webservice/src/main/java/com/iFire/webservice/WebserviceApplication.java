package com.ifire.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class WebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebserviceApplication.class, args);
	}

	// @Bean
	// @Profile(value = "dev")
	// CommandLineRunner userCreator(UserRepository userRepository) {

	// 	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	// 	return (args) -> {
	// 		for (int i = 0; i < 25; i++) {
	// 			User user = new User();
	// 			user.setUsername("user" + i);
	// 			user.setEmail("user" + i + "@mail.com");
	// 			user.setPassword(passwordEncoder.encode("P4sswoord"));
	// 			user.setActive(true);
	// 			user.setFirstName("first" + i);
	// 			user.setLastName("last" + i);
	// 			userRepository.save(user);

	// 		}

	// 	};
	// }

}
