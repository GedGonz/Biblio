package com.biblio.web.security.config.enviroments;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvInitializer {

	public static void loadEnv() {
		String activeProfile = System.getenv("SPRING_PROFILES_ACTIVE");

		// Solo cargar .env si NO estamos en producción
		if (activeProfile == null || !activeProfile.equalsIgnoreCase("prod")) {
			Dotenv dotenv = Dotenv.load();
			dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));
		}
	}
}