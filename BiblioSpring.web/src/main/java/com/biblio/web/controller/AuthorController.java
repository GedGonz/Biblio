package com.biblio.web.controller;

import com.biblio.domain.model.library.AuthorDto;
import com.biblio.domain.service.library.AuthorService;
import com.biblio.web.cloudfiles.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Value("${cloudinary.folder.author.name}")
    private String folder;
    private final AuthorService authorService;

    private final FileStorageService fileStorageService;
    public AuthorController(AuthorService authorService, FileStorageService fileStorageService){
        this.authorService=authorService;
        this.fileStorageService=fileStorageService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AuthorDto>> listAll(){
        return new ResponseEntity<>(authorService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<AuthorDto> save(@RequestParam("name") String name,
                                          @RequestParam("lastname") String lastname,
                                          @RequestParam("birthdate") LocalDate birthdate,
                                          @RequestParam("photo") MultipartFile photo){
        try {


            if(authorService.exist(name)){
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }


            AuthorDto authorDto = AuthorDto.builder()
                    .name(name)
                    .lastname(lastname)
                    .birthdate(birthdate).build();

            AuthorDto authorDtoNew = authorService.save(authorDto);
            String urlPhoto = fileStorageService.uploadFile(photo,folder);
            authorDtoNew.setPhoto(urlPhoto);

            return new ResponseEntity<>(authorService.save(authorDtoNew), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
