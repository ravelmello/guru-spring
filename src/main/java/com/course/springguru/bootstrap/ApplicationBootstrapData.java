package com.course.springguru.bootstrap;

import com.course.springguru.entity.Author;
import com.course.springguru.entity.Book;
import com.course.springguru.repository.AuthorRepository;
import com.course.springguru.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Author: Ravel Mello
 * Created at: 30/09/2020
 */

@Component
public class ApplicationBootstrapData implements CommandLineRunner {


   private final AuthorRepository authorRepository;
   private final BookRepository bookRepository;

    public ApplicationBootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author robertMartin = new Author("Robert", "C Martin");
        Author ericEvans = new Author("Eric", "Evans");

        Book domainDrivenDesign = new Book("Domain Driven Design", "R12BAHjNBLKD");
        Book cleanCode = new Book("Clean Code", "R12BAHHGDUKD");

        robertMartin.getBooks().add(cleanCode);
        ericEvans.getBooks().add(domainDrivenDesign);

        authorRepository.save(robertMartin);
        authorRepository.save(ericEvans);

        bookRepository.save(cleanCode);
        bookRepository.save(domainDrivenDesign);

        System.out.println("Init bootstrap");
        System.out.println("The number of books is " + bookRepository.count());


    }
}
