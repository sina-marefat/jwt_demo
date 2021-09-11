package com.jwt.demo.jwt_demo;

import com.jwt.demo.jwt_demo.domain.AppUser;
import com.jwt.demo.jwt_demo.domain.Role;
import com.jwt.demo.jwt_demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtDemoApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

			userService.saveUser(new AppUser(null,"sina marefat","sina","1234",new ArrayList<>()));
			userService.saveUser(new AppUser(null,"ali hosseini","ali","5678",new ArrayList<>()));
			userService.saveUser(new AppUser(null,"mostafa zandi","mostafa","9012",new ArrayList<>()));
			userService.saveUser(new AppUser(null,"mobina marefat","mobina","1234",new ArrayList<>()));

			userService.addRoleToUser("sina","ROLE_SUPER_ADMIN");
			userService.addRoleToUser("sina","ROLE_MANAGER");
			userService.addRoleToUser("ali","ROLE_USER");
			userService.addRoleToUser("mostafa","ROLE_USER");
			userService.addRoleToUser("mobina","ROLE_ADMIN");
			userService.addRoleToUser("mobina","ROLE_MANAGER");
		};
	}

}
