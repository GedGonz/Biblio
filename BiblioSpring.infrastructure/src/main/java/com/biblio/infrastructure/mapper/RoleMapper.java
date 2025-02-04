package com.biblio.infrastructure.mapper;

import com.biblio.domain.model.RoleDto;
import com.biblio.infrastructure.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PermissionMapper.class})
public interface RoleMapper {
    //    UserDto userToUserDto(User user);
    RoleDto  roleToRoleDto(Role role);
    Role RoleDtoToRole(RoleDto roleDto);
    List<RoleDto> roleToRoleDtoList(List<Role> roles);
}
