package com.heaven.spring6webapp.bootstrap;

import com.heaven.spring6webapp.domain.Author;
import com.heaven.spring6webapp.domain.Book;
import com.heaven.spring6webapp.domain.Publisher;
import com.heaven.spring6webapp.repositories.AuthorRepository;
import com.heaven.spring6webapp.repositories.BookRepository;
import com.heaven.spring6webapp.repositories.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Cruse");


        Book b1 = new Book("Gone with him", "0-4543-4880-0");


        Author rod = new Author("Rody", "Lake");


        Book b2 = new Book("Data Abstraction", "0-5064-5615-3");

        authorRepository.save(eric);
        authorRepository.save(rod);

        b1.addAuthor(eric);
        b2.addAuthor(eric);
        b2.addAuthor(rod);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("My Publisher");
        publisher.setAddress("123 Main");
        publisher.addBook(b1);
        publisher.addBook(b2);
        publisherRepository.save(publisher);






    }
}
