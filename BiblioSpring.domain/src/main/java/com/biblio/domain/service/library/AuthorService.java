package com.biblio.domain.service.library;

import com.biblio.domain.model.library.AuthorDto;
import com.biblio.domain.repository.library.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository=authorRepository;
    }
    public AuthorDto findById(Long id){
        return authorRepository.getFindById(id);
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

    public AuthorDto update(AuthorDto authorDto){

        AuthorDto autor = findById(authorDto.getId());
        if(!Objects.equals(autor.getName(), authorDto.getName()))
            autor.setName(authorDto.getName());
        if(!Objects.equals(autor.getLastname(), authorDto.getLastname()))
            autor.setLastname(authorDto.getLastname());
        if(autor.getBirthdate()!=authorDto.getBirthdate())
            autor.setBirthdate(authorDto.getBirthdate());
        autor.setPhoto(authorDto.getPhoto()!=null?authorDto.getPhoto():"");

        return authorRepository.save(autor);
    }

    public void delete(Long id){
        authorRepository.delete(id);
    }

}
