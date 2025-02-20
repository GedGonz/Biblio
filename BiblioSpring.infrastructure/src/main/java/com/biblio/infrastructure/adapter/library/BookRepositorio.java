package com.biblio.infrastructure.adapter.library;

import com.biblio.domain.model.library.BookDto;
import com.biblio.domain.repository.library.BookRepository;
import com.biblio.infrastructure.entity.library.Author;
import com.biblio.infrastructure.entity.library.Book;
import com.biblio.infrastructure.jpaentity.library.JpaAuthorCrudRepository;
import com.biblio.infrastructure.jpaentity.library.JpaBookCrudRepository;
import com.biblio.infrastructure.mapper.BookMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Log4j2
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
      Book bookNew=  jpaBookCrudRepository.save(book);

      if(bookNew.getFront()==null) log.info("book is save!");
      else log.info("photo upload and update book!");

      return bookMapper.bookToBookDto(bookNew);
    }

    @Override
    public boolean exist(String title) {
        return jpaBookCrudRepository.findByTitle(title).isPresent();
    }

    @Override
    public void delete(Long id) {

        Book book = jpaBookCrudRepository.findById(id).orElse(null);
        if(book!=null) {
            jpaBookCrudRepository.delete(book);
            log.info("book delete!");
        }
    }
}
