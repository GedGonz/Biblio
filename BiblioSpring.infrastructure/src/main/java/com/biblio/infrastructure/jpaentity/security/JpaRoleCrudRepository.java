package com.biblio.infrastructure.jpaentity.security;

import com.biblio.infrastructure.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaRoleCrudRepository extends JpaRepository<Role,Long> {

     List<Role> findRoleByRoleEnumIn(List<String> roleNames);
}
