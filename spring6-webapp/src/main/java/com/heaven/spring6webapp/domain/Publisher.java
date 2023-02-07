package com.heaven.spring6webapp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String publisherName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "publisher",orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book){
        this.books.add(book);
        book.setPublisher(this);
    }
}
