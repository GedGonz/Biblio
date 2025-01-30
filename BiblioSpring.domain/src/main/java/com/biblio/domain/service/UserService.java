package com.biblio.domain.service;

import com.biblio.domain.model.PermissionDto;
import com.biblio.domain.model.RoleDto;
import com.biblio.domain.model.UserDto;
import com.biblio.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveAll(List<UserDto> userDtos) {
        userRepository.saveAll(userDtos);
    }

    public List<UserDto> getAll(){
        return userRepository.getAll();
    }
    public List<RoleDto> getRoles(String username) throws Exception {
        return userRepository.getRoles(username);
    }

    public UserDto getByUsername(String username) throws Exception {
        return userRepository.getByUsername(username);
    }

    public List<PermissionDto> getPermission(String username) throws Exception {
        return userRepository.getPermission(username);
    }

}
