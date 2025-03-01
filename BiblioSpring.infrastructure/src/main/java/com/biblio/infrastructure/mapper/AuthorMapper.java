package com.biblio.infrastructure.mapper;

import com.biblio.domain.model.library.AuthorDto;
import com.biblio.infrastructure.entity.library.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(target = "books", ignore = true)
    AuthorDto authorToAuthorDto(Author author);
    Author authorDtoToAuthor(AuthorDto authorDto);
    List<Author> authorsDtoToAuthors(List<AuthorDto> authorsDto);
    List<AuthorDto> authorsToAuthorsDto(List<Author> authors);
}
