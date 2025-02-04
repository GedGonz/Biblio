package com.biblio.infrastructure.adapter;

import com.biblio.domain.model.PermissionDto;
import com.biblio.domain.model.RoleDto;
import com.biblio.domain.model.UserDto;
import com.biblio.domain.repository.UserRepository;
import com.biblio.infrastructure.JpaEntities.JpaRoleCrudRepository;
import com.biblio.infrastructure.entity.Permission;
import com.biblio.infrastructure.entity.Role;
import com.biblio.infrastructure.entity.User;
import com.biblio.infrastructure.JpaEntities.JpaUserCrudRepository;
import com.biblio.infrastructure.mapper.PermissionMapper;
import com.biblio.infrastructure.mapper.RoleMapper;
import com.biblio.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositorio implements UserRepository {

    final private JpaUserCrudRepository jpaUserCrudRepository;
    final private JpaRoleCrudRepository jpaRoleCrudRepository;
    final private UserMapper userMapper;
    final private RoleMapper roleMapper;
    final private PermissionMapper permissionMapper;

    public UserRepositorio(JpaUserCrudRepository jpaUserCrudRepository,JpaRoleCrudRepository jpaRoleCrudRepository, UserMapper userMapper, RoleMapper roleMapper, PermissionMapper permissionMapper) {
        this.jpaUserCrudRepository = jpaUserCrudRepository;
        this.jpaRoleCrudRepository=jpaRoleCrudRepository;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.permissionMapper = permissionMapper;
    }


    @Override
    public UserDto save(UserDto userdto) {
       User _user =userMapper.userDtoToUser(userdto);

        Set<Role> roles = new HashSet<>(jpaRoleCrudRepository.findRoleByRoleEnumIn(userdto.getRoles().stream().map(role -> role.getRoleEnum().name()).toList()));
        _user.setRoles(roles);
       return userMapper.userToUserDto(jpaUserCrudRepository.save(_user));
    }

    @Override
    public void saveAll(List<UserDto> userDtos) {
        List<User> _users = userMapper.userDtoToUserList(userDtos).stream().toList();
        jpaUserCrudRepository.saveAll(_users);
    }

    @Override
    public List<UserDto> getAll() {
        var users = (List<User>) jpaUserCrudRepository.findAll();
        return userMapper.userToUserDtoList(users);
    }

    @Override
    public UserDto getByUsername(String username) throws Exception {
        User user = jpaUserCrudRepository.findByUsername(username).orElseThrow(() -> new Exception("User not found"));
        return userMapper.userToUserDto(user);
    }

    @Override
    public List<RoleDto> getRoles(String username) throws Exception {
        User user = jpaUserCrudRepository.findByUsername(username).orElseThrow(() -> new Exception("User not found"));
        List<Role> roles = user.getRoles().stream().toList();
        return roleMapper.roleToRoleDtoList(roles);
    }

    @Override
    public List<PermissionDto> getPermission(String username) throws Exception {
        User user = jpaUserCrudRepository.findByUsername(username).orElseThrow(() -> new Exception("User not found"));
        List<Permission> permisions = user.getRoles().stream().flatMap(role -> role.getPermissions().stream()).toList();
        return permissionMapper.permissionToPermissionDtoList(permisions);
    }
}
