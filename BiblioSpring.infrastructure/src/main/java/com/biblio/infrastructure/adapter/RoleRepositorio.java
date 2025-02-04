package com.biblio.infrastructure.adapter;

import com.biblio.domain.model.RoleDto;
import com.biblio.domain.repository.RoleRepository;
import com.biblio.infrastructure.JpaEntities.JpaRoleCrudRepository;
import com.biblio.infrastructure.mapper.RoleMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepositorio implements RoleRepository {

    private JpaRoleCrudRepository jpaRoleCrudRepository;
    private RoleMapper roleMapper;

    public RoleRepositorio(RoleMapper roleMapper, JpaRoleCrudRepository jpaRoleCrudRepository) {
        this.roleMapper = roleMapper;
        this.jpaRoleCrudRepository = jpaRoleCrudRepository;
    }

    @Override
    public List<RoleDto> findRoleByRoleEnumIn(List<String> roleNames) {
        return roleMapper.roleToRoleDtoList(jpaRoleCrudRepository.findRoleByRoleEnumIn(roleNames));
    }
}
