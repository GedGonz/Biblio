package com.biblio.domain.service.security;

import com.biblio.domain.model.security.RoleDto;
import com.biblio.domain.repository.security.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleDto> findRoleByRoleEnumIn(List<String> roleNames){
        return roleRepository.findRoleByRoleEnumIn(roleNames);
    }
}
