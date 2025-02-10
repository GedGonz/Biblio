package com.biblio.infrastructure.entity.library;

import com.biblio.infrastructure.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String title;
    @Column(columnDefinition = "TEXT")
    public String description;
    public String front;
    @Enumerated(EnumType.ORDINAL)
    public GenderEnum gender;
    public LocalDate publishDate;

    @ManyToOne()
    @JoinColumn(name = "id_author")
    public Author author;



}
