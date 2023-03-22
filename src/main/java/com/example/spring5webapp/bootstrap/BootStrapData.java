package com.example.spring5webapp.bootstrap;

import com.example.spring5webapp.domain.Author;
import com.example.spring5webapp.domain.Book;
import com.example.spring5webapp.domain.Publisher;
import com.example.spring5webapp.repositories.AuthorRepository;
import com.example.spring5webapp.repositories.BookRepository;
import com.example.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private final PublisherRepository publisherRepository;

    public BootStrapData(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in bootstrap");

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        bookRepository.save(ddd);
        authorRepository.save(eric);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23452346");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        bookRepository.save(noEJB);
        authorRepository.save(rod);

        System.out.println("Number of books: " + bookRepository.count());

        Publisher publisher = new Publisher("Publisher1", "Street 1", "City 1", "State 1", "50771");
        publisherRepository.save(publisher);

        Publisher publisher2 = new Publisher("Publisher2", "Street 2", "City 2", "State 2", "50772");
        publisherRepository.save(publisher2);

        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Publishers: " + publisherRepository.findAll().toString());
    }
}
