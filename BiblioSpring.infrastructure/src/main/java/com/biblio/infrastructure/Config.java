package com.biblio.infrastructure;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//Configuración necesaria para que Spring Data JPA pueda encontrar los repositorios y las entidades
@Configuration
@EnableJpaRepositories(basePackages = {"com.biblio.infrastructure.adapter","com.biblio.infrastructure.jpaentity"})
@EntityScan(basePackages = "com.biblio.infrastructure.entity")

public class Config {
}
