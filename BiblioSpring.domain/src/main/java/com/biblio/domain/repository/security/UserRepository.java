package com.biblio.domain.repository.security;

import com.biblio.domain.model.security.PermissionDto;
import com.biblio.domain.model.security.RoleDto;
import com.biblio.domain.model.security.UserDto;

import java.util.List;


public interface UserRepository {

    UserDto save(UserDto userdto);
    void saveAll(List<UserDto> userDtos);
    List<UserDto> getAll();
    UserDto getByUsername(String username);
    List<RoleDto> getRoles(String username);
    List<PermissionDto> getPermission(String username);

}
