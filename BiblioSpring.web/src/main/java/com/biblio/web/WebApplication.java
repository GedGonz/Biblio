package com.biblio.web;

import com.biblio.domain.enums.RoleEnum;
import com.biblio.domain.model.library.AuthorDto;
import com.biblio.domain.model.security.PermissionDto;
import com.biblio.domain.model.security.RoleDto;
import com.biblio.domain.model.security.UserDto;
import com.biblio.domain.repository.library.AuthorRepository;
import com.biblio.domain.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;



@SpringBootApplication(scanBasePackages = "com.biblio")
public class WebApplication {

	private final UserRepository userRepository;
	private final AuthorRepository authorRepository;
	@Value("${password}")
	private String password;

	public WebApplication(UserRepository userRepository,AuthorRepository authorRepository){
		this.userRepository=userRepository;
		this.authorRepository=authorRepository;
	}

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
			RoleDto roleDto3 = RoleDto.builder().roleEnum(RoleEnum.INIVITED).permissions(Set.of(permissionDto4)).build();

			UserDto userDto1 = UserDto.builder().username("gedgonz").
					password(new BCryptPasswordEncoder().encode(password)).
					roles(Set.of(roleDto1)).
					accountNoExpired(true).accountNoLocked(true).
					credentialNoExpired(true).credentialNoExpired(true).
					enabled(true).build();

			UserDto userDto2 = UserDto.builder().username("hels").
					password(new BCryptPasswordEncoder().encode(password)).
					roles(Set.of(roleDto3)).
					accountNoExpired(true).accountNoLocked(true).
					credentialNoExpired(true).credentialNoExpired(true).
					enabled(true).build();

			userRepository.saveAll(List.of(userDto1, userDto2));

			AuthorDto author1 = AuthorDto.builder()
					.birthdate(LocalDate.now())
					.name("Franz")
					.lastname("Kafka")
					.build();
			AuthorDto author2 = AuthorDto.builder()
					.birthdate(LocalDate.now())
					.name("Ernest")
					.lastname("Cline")
					.build();
			AuthorDto author3 = AuthorDto.builder()
					.birthdate(LocalDate.now())
					.name("Edgar Allan")
					.lastname("Poe")
					.build();
			List<AuthorDto> authors =List.of(author1,author2,author3);


			authorRepository.saveAll(authors);

		};
	}
}
