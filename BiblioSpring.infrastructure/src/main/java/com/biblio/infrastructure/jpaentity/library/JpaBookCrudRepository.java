package com.biblio.infrastructure.jpaentity.library;

import com.biblio.infrastructure.entity.library.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBookCrudRepository extends JpaRepository<Book,Long> {
}
