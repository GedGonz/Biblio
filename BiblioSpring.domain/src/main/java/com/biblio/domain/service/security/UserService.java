package com.biblio.domain.service.security;

import com.biblio.domain.model.security.UserDto;
import com.biblio.domain.repository.security.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto save(UserDto userDto){ return userRepository.save(userDto);}

    public void saveAll(List<UserDto> userDtos) {
        userRepository.saveAll(userDtos);
    }

    public List<UserDto> getAll(){
        return userRepository.getAll();
    }

    public UserDto getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

}
