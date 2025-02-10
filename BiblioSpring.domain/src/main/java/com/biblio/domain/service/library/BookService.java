package com.biblio.domain.service.library;

import com.biblio.domain.model.library.BookDto;
import com.biblio.domain.repository.library.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getFindALl(){
        return bookRepository.getFindALl();
    }
    public BookDto getFindById(Long id){
        return bookRepository.getFindById(id);
    }
    public BookDto save(BookDto bookDto){
        return bookRepository.save(bookDto);
    }
    public boolean exist(String title){
        return bookRepository.exist(title);
    }

}
