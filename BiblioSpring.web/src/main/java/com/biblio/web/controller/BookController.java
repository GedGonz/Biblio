package com.biblio.web.controller;

import com.biblio.domain.enums.GenderEnum;
import com.biblio.domain.model.library.AuthorDto;
import com.biblio.domain.model.library.BookDto;
import com.biblio.domain.service.library.BookService;
import com.biblio.infrastructure.adapter.service.ReportService;
import com.biblio.web.cloudfiles.service.FileStorageService;
import com.biblio.web.utils.ReponseUtil;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Log4j2
@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/book")
public class BookController {

    @Value("${cloudinary.folder.book.name}")
    private String folder;

    private final BookService bookService;
    private final FileStorageService fileStorageService;
    private final ReportService reportService;

    public BookController(BookService bookService, FileStorageService fileStorageService, ReportService reportService) {
        this.bookService = bookService;
        this.fileStorageService = fileStorageService;
        this.reportService = reportService;
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
                log.warn("book {} exist", title);
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
            log.error("error was generated ", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/update/{id}")
    public ResponseEntity<BookDto> update(@PathVariable("id") Long id, @RequestParam("title") String title,
                                        @RequestParam("description") String description,
                                        @RequestParam("publishDate") LocalDate publishDate,
                                        @RequestParam("photo") MultipartFile photo,
                                        @RequestParam("id_author") Long idAuthor,
                                        @RequestParam("gender") Integer gender){

        try {

            BookDto bookExist = bookService.getFindById(id);
            if(bookExist==null){
                log.warn("book {} not exist ", id);
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

            if(!photo.isEmpty()){
                fileStorageService.deleteFile(bookExist.getFront(),folder);
                String urlPhoto = fileStorageService.uploadFile(photo,folder);
                bookDtoNew.setFront(urlPhoto);
                return new ResponseEntity<>(bookService.save(bookDtoNew), HttpStatus.OK);
            }

            return new ResponseEntity<>(bookDtoNew, HttpStatus.OK);

        } catch (Exception e) {
            log.error("error was generated ", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {
        fileStorageService.deleteFile(bookService.getFindById(id).getFront(),folder);
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download() throws IOException, JRException {

        String pathReport = "reports/book/ReportBooks.jasper";
        return ReponseUtil.createByteArrayResponse(reportService.generateReport(pathReport));
    }

}
