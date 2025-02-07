package com.biblio.infrastructure.adapter.security;

import com.biblio.domain.model.security.PermissionDto;
import com.biblio.domain.model.security.RoleDto;
import com.biblio.domain.model.security.UserDto;
import com.biblio.domain.repository.security.UserRepository;
import com.biblio.infrastructure.jpaentity.security.JpaRoleCrudRepository;
import com.biblio.infrastructure.entity.security.Permission;
import com.biblio.infrastructure.entity.security.Role;
import com.biblio.infrastructure.entity.security.User;
import com.biblio.infrastructure.jpaentity.security.JpaUserCrudRepository;
import com.biblio.infrastructure.mapper.PermissionMapper;
import com.biblio.infrastructure.mapper.RoleMapper;
import com.biblio.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositorio implements UserRepository {

    private final JpaUserCrudRepository jpaUserCrudRepository;
    private final JpaRoleCrudRepository jpaRoleCrudRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final PermissionMapper permissionMapper;

    public UserRepositorio(JpaUserCrudRepository jpaUserCrudRepository,JpaRoleCrudRepository jpaRoleCrudRepository, UserMapper userMapper, RoleMapper roleMapper, PermissionMapper permissionMapper) {
        this.jpaUserCrudRepository = jpaUserCrudRepository;
        this.jpaRoleCrudRepository=jpaRoleCrudRepository;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.permissionMapper = permissionMapper;
    }


    @Override
    public UserDto save(UserDto userdto) {
       User user =userMapper.userDtoToUser(userdto);

        Set<Role> roles = new HashSet<>(jpaRoleCrudRepository.findRoleByRoleEnumIn(userdto.getRoles().stream().map(role -> role.getRoleEnum().name()).toList()));
        user.setRoles(roles);
       return userMapper.userToUserDto(jpaUserCrudRepository.save(user));
    }

    @Override
    public void saveAll(List<UserDto> userDtos) {
        List<User> users = userMapper.userDtoToUserList(userDtos).stream().toList();
        jpaUserCrudRepository.saveAll(users);
    }

    @Override
    public List<UserDto> getAll() {
        var users = (List<User>) jpaUserCrudRepository.findAll();
        return userMapper.userToUserDtoList(users);
    }

    @Override
    public UserDto getByUsername(String username) {
        User user = getUser(username);
        return userMapper.userToUserDto(user);
    }

    @Override
    public List<RoleDto> getRoles(String username) {
        User user = getUser(username);
        if(user==null) return new ArrayList<>();

        List<Role> roles = user.getRoles().stream().toList();
        return roleMapper.roleToRoleDtoList(roles);
    }

    @Override
    public List<PermissionDto> getPermission(String username){
        User user = getUser(username);
        if(user==null) return new ArrayList<>();
        List<Permission> permissions = user.getRoles().stream().flatMap(role -> role.getPermissions().stream()).toList();
        return permissionMapper.permissionToPermissionDtoList(permissions);
    }

    private User getUser(String username) {
        try {
            return jpaUserCrudRepository.findByUsername(username).orElseThrow(() -> new Exception("User not found"));
        }catch(Exception e){
            return null;
        }

    }
}
