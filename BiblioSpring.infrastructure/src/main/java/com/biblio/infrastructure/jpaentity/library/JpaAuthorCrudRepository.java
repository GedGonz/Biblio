package com.biblio.infrastructure.jpaentity.library;

import com.biblio.infrastructure.entity.library.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaAuthorCrudRepository extends JpaRepository<Author,Long> {
    Optional<Author> findByName(String name);
}
