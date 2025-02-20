package com.biblio.domain.repository.library;

import com.biblio.domain.model.library.AuthorDto;

import java.util.List;

public interface AuthorRepository {

    boolean exist(String name);
    List<AuthorDto> getAll();
    AuthorDto getFindById(Long id);
    void saveAll(List<AuthorDto> authorsDto);
    AuthorDto save (AuthorDto authorDto);
    void delete(Long id);
}
