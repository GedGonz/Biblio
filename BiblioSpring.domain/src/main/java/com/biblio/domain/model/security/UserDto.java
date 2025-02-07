package com.biblio.domain.model.security;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDto {


    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private boolean accountNoExpired;
    private boolean accountNoLocked;
    private boolean credentialNoExpired;
    private Set<RoleDto> roles;
}