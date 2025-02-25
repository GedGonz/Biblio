package com.biblio.web.security.config.enviroments;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvInitializer {

	public static void loadEnv() {
		Dotenv dotenv = Dotenv.load();
		dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));
	}
}