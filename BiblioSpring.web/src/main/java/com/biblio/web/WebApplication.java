package com.biblio.web;

import com.biblio.domain.enums.RoleEnum;
import com.biblio.domain.model.PermissionDto;
import com.biblio.domain.model.RoleDto;
import com.biblio.domain.model.UserDto;
import com.biblio.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;



@SpringBootApplication(scanBasePackages = "com.biblio")
public class WebApplication {

	@Autowired
	UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	//Método para inicializar la base de datos
	@Bean
	CommandLineRunner init(){
		return  args -> {
			PermissionDto permissionDto1 = PermissionDto.builder().name("CREATE").build();
			PermissionDto permissionDto2 = PermissionDto.builder().name("READ").build();
			PermissionDto permissionDto3 = PermissionDto.builder().name("UPDATE").build();
			PermissionDto permissionDto4 = PermissionDto.builder().name("REFACTOR").build();

			RoleDto roleDto1 = RoleDto.builder().roleEnum(RoleEnum.ADMIN).permissions(Set.of(permissionDto1, permissionDto2, permissionDto3)).build();
			RoleDto roleDto2 = RoleDto.builder().roleEnum(RoleEnum.USER).permissions(Set.of(permissionDto1, permissionDto2)).build();
			RoleDto roleDto3 = RoleDto.builder().roleEnum(RoleEnum.INIVITED).permissions(Set.of(permissionDto4)).build();
			RoleDto roleDto4 = RoleDto.builder().roleEnum(RoleEnum.DEVOPER).permissions(Set.of(permissionDto1, permissionDto2, permissionDto3, permissionDto4)).build();

			UserDto userDto1 = UserDto.builder().username("gedgonz").
					password(new BCryptPasswordEncoder().encode("123")).
					roles(Set.of(roleDto1)).
					accountNoExpired(true).accountNoLocked(true).
					credentialNoExpired(true).credentialNoExpired(true).
					enabled(true).build();

			UserDto userDto2 = UserDto.builder().username("hels").
					password(new BCryptPasswordEncoder().encode("123")).
					roles(Set.of(roleDto3)).
					accountNoExpired(true).accountNoLocked(true).
					credentialNoExpired(true).credentialNoExpired(true).
					enabled(true).build();

			userRepository.saveAll(List.of(userDto1, userDto2));

		};
	}
}
