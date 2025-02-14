package com.biblio.web.controller;

import com.biblio.domain.model.library.AuthorDto;
import com.biblio.domain.service.library.AuthorService;
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
@RequestMapping("/author")
public class AuthorController {

    @Value("${cloudinary.folder.author.name}")
    private String folder;

    private final AuthorService authorService;
    private final ReportService reportService;

    private final FileStorageService fileStorageService;
    public AuthorController(AuthorService authorService, ReportService reportService, FileStorageService fileStorageService){
        this.authorService=authorService;
        this.reportService = reportService;
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
                log.warn("author {} exist",name);
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
            log.error("error was generated ", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download() throws IOException, JRException {

        String pathReport = "reports/author/ReportAuthors.jasper";
        return ReponseUtil.createByteArrayResponse(reportService.generateReport(pathReport));
    }
}
