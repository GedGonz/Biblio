package com.biblio.domain.service.library;

import com.biblio.domain.model.library.AuthorDto;
import com.biblio.domain.repository.library.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository=authorRepository;
    }
    public boolean exist(String name){
        return authorRepository.exist(name);
    }

    public List<AuthorDto> getAll(){
        return authorRepository.getAll();
    }
    public AuthorDto save(AuthorDto authorDto){
        return authorRepository.save(authorDto);
    }

}
