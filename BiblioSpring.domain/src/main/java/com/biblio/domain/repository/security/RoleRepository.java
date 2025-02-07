package com.biblio.domain.repository.security;

import com.biblio.domain.model.security.RoleDto;

import java.util.List;

public interface RoleRepository {

    List<RoleDto> findRoleByRoleEnumIn(List<String> roleNames);
}
