package com.biblio.infrastructure.entity.library;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;
    public String lastname;
    public LocalDate birthdate;
    public String photo;
    @OneToMany(mappedBy = "author")
    public Set<Book> books;

}
