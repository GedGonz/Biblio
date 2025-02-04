package com.biblio.infrastructure.JpaEntities;

import com.biblio.infrastructure.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaRoleCrudRepository extends JpaRepository<Role,Long> {

     List<Role> findRoleByRoleEnumIn(List<String> roleNames);
}
