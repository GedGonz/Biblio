package com.biblio.domain.model.library;

import com.biblio.domain.enums.GenderEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookDto {
    private Long id;
    private String title;
    private String description;
    private String front;
    private GenderEnum gender;
    private LocalDate publishDate;
    private AuthorDto author;
}
