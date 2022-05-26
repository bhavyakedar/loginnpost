package com.bhavyakedar.loginpost;

import com.bhavyakedar.loginpost.components.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.time.LocalDateTime;

@SpringBootApplication(exclude=SecurityAutoConfiguration.class)
public class LoginpostApplication {

	public static User currentUser;

	public static void main(String[] args) {
		/*currentUser = new User("bhavyakedar", "Bhavya",
				"Kedar", "bhavyakedar24x7@gmail.com", LocalDateTime.now(), "bhavya");*/
		SpringApplication.run(LoginpostApplication.class, args);
	}
}
