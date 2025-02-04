package com.biblio.domain.repository;

import com.biblio.domain.model.RoleDto;

import java.util.List;

public interface RoleRepository {

    List<RoleDto> findRoleByRoleEnumIn(List<String> roleNames);
}
