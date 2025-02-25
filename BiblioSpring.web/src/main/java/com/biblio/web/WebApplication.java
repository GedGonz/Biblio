package com.biblio.web;

import com.biblio.web.security.config.enviroments.DotenvInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = "com.biblio")
public class WebApplication {
	public static void main(String[] args) {
		DotenvInitializer.init();
		SpringApplication.run(WebApplication.class, args);
	}
}
