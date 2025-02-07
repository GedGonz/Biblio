package com.biblio.domain.model.security;

import com.biblio.domain.enums.RoleEnum;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
@Data
@Builder
public class RoleDto {


    private Long id;

    private RoleEnum roleEnum;
    private Set<PermissionDto> permissions;
}