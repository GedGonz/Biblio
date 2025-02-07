package com.biblio.domain.model.library;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class AuthorDto {

    private Long id;
    private String name;
    private String lastname;
    private LocalDate birthdate;
    private String photo;
    private Set<BookDto> books;
}
