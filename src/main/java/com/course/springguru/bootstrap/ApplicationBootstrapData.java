package com.course.springguru.bootstrap;

import com.course.springguru.entity.Author;
import com.course.springguru.entity.Book;
import com.course.springguru.entity.Publisher;
import com.course.springguru.repository.AuthorRepository;
import com.course.springguru.repository.BookRepository;
import com.course.springguru.repository.PublisherRepository;
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
   private final PublisherRepository publisherRepository;

    public ApplicationBootstrapData(AuthorRepository authorRepository,
                                    BookRepository bookRepository,
                                    PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author robertMartin = new Author("Robert", "C Martin");
        Author ericEvans = new Author("Eric", "Evans");

        Publisher publisher = new Publisher("Addison-Wesley",
                "St Addison-Wesley",
                "Massachussets - USA", "Boston",
                "922159");

        Book domainDrivenDesign = new Book("Domain Driven Design", "R12BAHjNBLKD");
        Book cleanCode = new Book("Clean Code", "R12BAHHGDUKD");

        robertMartin.getBooks().add(cleanCode);
        ericEvans.getBooks().add(domainDrivenDesign);

        cleanCode.setPublisher(publisher);
        domainDrivenDesign.setPublisher(publisher);

        publisherRepository.save(publisher);

        authorRepository.save(robertMartin);
        authorRepository.save(ericEvans);

        bookRepository.save(cleanCode);
        bookRepository.save(domainDrivenDesign);


        System.out.println("Init bootstrap");
        System.out.println("The number of books is " + bookRepository.count());
        System.out.println("The number of publishers is " + publisherRepository.count());


    }
}
