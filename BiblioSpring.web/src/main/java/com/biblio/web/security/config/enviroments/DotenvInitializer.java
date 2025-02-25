package com.biblio.web.security.config.enviroments;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvInitializer {

	static{
		Dotenv dotenv = Dotenv.load();
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
	}

	public static void init(){

	}
}