package com.biblio.infrastructure.mapper;

import com.biblio.domain.model.security.RoleDto;
import com.biblio.infrastructure.entity.security.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PermissionMapper.class})
public interface RoleMapper {
    RoleDto  roleToRoleDto(Role role);
    Role roleDtoToRole(RoleDto roleDto);
    List<RoleDto> roleToRoleDtoList(List<Role> roles);
}
