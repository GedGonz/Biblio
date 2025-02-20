package com.biblio.domain.repository.library;

import com.biblio.domain.model.library.BookDto;

import java.util.List;

public interface BookRepository {

    List<BookDto> getFindALl();
    BookDto getFindById(Long id);
    BookDto save(BookDto bookDto);
    boolean exist(String title);
    void delete(Long id);
}
