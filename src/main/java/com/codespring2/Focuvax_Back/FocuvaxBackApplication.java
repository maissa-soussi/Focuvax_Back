package com.codespring2.Focuvax_Back;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.codespring2.Focuvax_Back.models.User;
import com.codespring2.Focuvax_Back.repository.UserRepository;
import com.codespring2.Focuvax_Back.service.UserService;

@SpringBootApplication
public class FocuvaxBackApplication {
	@Autowired 
	UserRepository repo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(FocuvaxBackApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		User u = new User();
		u.setNom("admin");
		u.setPrenom("admin");
		u.setUsername("admin@gmail.com");
		u.setMdp("admin");
		u.setRole("ROLE_ADMIN");
		repo.save(u);
	}
	
}
