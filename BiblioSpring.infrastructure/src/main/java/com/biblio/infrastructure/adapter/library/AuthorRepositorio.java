package com.biblio.infrastructure.adapter.library;

import com.biblio.domain.model.library.AuthorDto;
import com.biblio.domain.repository.library.AuthorRepository;
import com.biblio.infrastructure.jpaentity.library.JpaAuthorCrudRepository;
import com.biblio.infrastructure.entity.library.Author;
import com.biblio.infrastructure.mapper.AuthorMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;
@Log4j2
@Repository
public class AuthorRepositorio implements AuthorRepository {

    private final JpaAuthorCrudRepository jpaAuthorCrudRepository;
    private final AuthorMapper authorMapper;

    public AuthorRepositorio(JpaAuthorCrudRepository jpaAuthorCrudRepository, AuthorMapper authorMapper){
        this.jpaAuthorCrudRepository=jpaAuthorCrudRepository;
        this.authorMapper=authorMapper;
    }


    @Override
    public boolean exist(String name) {
        return jpaAuthorCrudRepository.findByName(name).isPresent();
    }

    @Override
    public List<AuthorDto> getAll() {
        return authorMapper.authorsToAuthorsDto(jpaAuthorCrudRepository.findAll());
    }

    @Override
    public AuthorDto getFindById(Long id) {
        return authorMapper.authorToAuthorDto(jpaAuthorCrudRepository.findById(id).orElse(null));
    }

    @Override
    public void saveAll(List<AuthorDto> authorsDto) {
         jpaAuthorCrudRepository.saveAll(authorMapper.authorsDtoToAuthors(authorsDto));
    }

    @Override
    public AuthorDto save(AuthorDto authorDto) {
        Author author = jpaAuthorCrudRepository.save(authorMapper.authorDtoToAuthor(authorDto));
        if(author.getPhoto().isEmpty()) log.info("author is save!");
        else log.info("photo upload and update author!");
        return authorMapper.authorToAuthorDto(author);
    }
}
