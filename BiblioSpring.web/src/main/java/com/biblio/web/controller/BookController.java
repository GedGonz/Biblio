package com.biblio.web.controller;

import com.biblio.domain.enums.GenderEnum;
import com.biblio.domain.model.library.AuthorDto;
import com.biblio.domain.model.library.BookDto;
import com.biblio.domain.service.library.BookService;
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
@RequestMapping("/book")
public class BookController {

    @Value("${cloudinary.folder.book.name}")
    private String folder;

    private final BookService bookService;
    private final FileStorageService fileStorageService;

    public BookController(BookService bookService, FileStorageService fileStorageService) {
        this.bookService = bookService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> getAll(){
        return new ResponseEntity<>(bookService.getFindALl(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getFindById(@PathVariable("id") Long id){
        return new ResponseEntity<>(bookService.getFindById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<BookDto> save(@RequestParam("title") String title,
                                 @RequestParam("description") String description,
                                 @RequestParam("publishDate") LocalDate publishDate,
                                 @RequestParam("photo") MultipartFile photo,
                                 @RequestParam("id_author") Long idAuthor,
                                 @RequestParam("gender") Integer gender){

        try {


            if(bookService.exist(title)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            GenderEnum genderEnum = GenderEnum.values()[gender];

            BookDto bookDto = BookDto.builder()
                    .title(title)
                    .description(description)
                    .publishDate(publishDate)
                    .gender(genderEnum)
                    .author(AuthorDto.builder().id(idAuthor).build())
                    .build();

            BookDto bookDtoNew = bookService.save(bookDto);
            String urlPhoto = fileStorageService.uploadFile(photo,folder);
            bookDtoNew.setFront(urlPhoto);
            return new ResponseEntity<>(bookService.save(bookDtoNew), HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
