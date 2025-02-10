package com.biblio.infrastructure.adapter.library;

import com.biblio.domain.model.library.BookDto;
import com.biblio.domain.repository.library.BookRepository;
import com.biblio.infrastructure.entity.library.Author;
import com.biblio.infrastructure.entity.library.Book;
import com.biblio.infrastructure.jpaentity.library.JpaAuthorCrudRepository;
import com.biblio.infrastructure.jpaentity.library.JpaBookCrudRepository;
import com.biblio.infrastructure.mapper.BookMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositorio implements BookRepository {

    private final JpaBookCrudRepository jpaBookCrudRepository;
    private final JpaAuthorCrudRepository jpaAuthorCrudRepository;
    private final BookMapper bookMapper;

    public BookRepositorio(JpaBookCrudRepository jpaBookCrudRepository, JpaAuthorCrudRepository jpaAuthorCrudRepository, BookMapper bookMapper) {
        this.jpaBookCrudRepository = jpaBookCrudRepository;
        this.jpaAuthorCrudRepository = jpaAuthorCrudRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDto> getFindALl() {
        var result =jpaBookCrudRepository.findAll();
        return bookMapper.bookToBookDtoList(result);
    }

    @Override
    public BookDto getFindById(Long id) {
        return bookMapper.bookToBookDto(jpaBookCrudRepository.findById(id).orElse(null));
    }

    @Override
    public BookDto save(BookDto bookDto) {
      Book book = bookMapper.bookDtoToBook(bookDto);
      Optional<Author> author = jpaAuthorCrudRepository.findById(bookDto.getAuthor().getId());

      if(author.isEmpty())
          throw new EntityNotFoundException("author not exist!");

      book.setAuthor(author.get());
      return bookMapper.bookToBookDto(jpaBookCrudRepository.save(book));
    }

    @Override
    public boolean exist(String title) {
        return jpaBookCrudRepository.findByTitle(title).isPresent();
    }
}
