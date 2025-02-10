package com.biblio.infrastructure.jpaentity.library;
import com.biblio.infrastructure.entity.library.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaBookCrudRepository extends JpaRepository<Book,Long> {

    Optional<Book> findByTitle(String title);
}
