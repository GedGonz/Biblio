package com.biblio.infrastructure.adapter;

import com.biblio.infrastructure.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserCrudRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);
}