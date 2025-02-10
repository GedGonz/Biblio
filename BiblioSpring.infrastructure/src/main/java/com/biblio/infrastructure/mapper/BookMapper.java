package com.biblio.infrastructure.mapper;

import com.biblio.domain.model.library.BookDto;
import com.biblio.infrastructure.entity.library.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class})
public interface BookMapper {

    BookDto bookToBookDto(Book book);
    Book bookDtoToBook(BookDto bookDto);
    List<BookDto> bookToBookDtoList(List<Book> books);
    List<Book> bookDtoToBookList(List<BookDto> bookDtos);


}
