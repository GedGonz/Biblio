package com.biblio.domain.repository;

import com.biblio.domain.model.PermissionDto;
import com.biblio.domain.model.RoleDto;
import com.biblio.domain.model.UserDto;

import java.util.List;
import java.util.Optional;


public interface UserRepository {

    void saveAll(List<UserDto> userDtos);
    List<UserDto> getAll();
    UserDto getByUsername(String username) throws Exception;
    List<RoleDto> getRoles(String username) throws Exception;
    List<PermissionDto> getPermission(String username) throws Exception;

}
