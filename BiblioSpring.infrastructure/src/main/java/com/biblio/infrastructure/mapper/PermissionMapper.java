package com.biblio.infrastructure.mapper;

import com.biblio.domain.model.security.PermissionDto;
import com.biblio.infrastructure.entity.security.Permission;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionDto permissionToPermissionDto(Permission permission);
    Permission permissionDtoToPermision(PermissionDto permissionDto);
    List<PermissionDto> permissionToPermissionDtoList(List<Permission> permissions);
}
