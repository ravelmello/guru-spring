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

        publisherRepository.save(publisher);

        System.out.println("Publisher Count "+ publisherRepository.count());

        ericEvans.getBooks().add(domainDrivenDesign);
        domainDrivenDesign.getAuthors().add(ericEvans);
        domainDrivenDesign.setPublisher(publisher);
        publisher.getBooks().add(domainDrivenDesign);

        persistEntities(ericEvans, domainDrivenDesign,publisher);

        robertMartin.getBooks().add(cleanCode);
        cleanCode.getAuthors().add(robertMartin);
        cleanCode.setPublisher(publisher);
        publisher.getBooks().add(cleanCode);

        persistEntities(robertMartin, cleanCode, publisher);

        System.out.println("Init bootstrap");
        System.out.println("The number of books is " + bookRepository.count());
        System.out.println("Publisher Count " + publisherRepository.count());
        System.out.println("Publisher number of Books " + publisher.getBooks().size());

    }

    private void persistEntities(Author author, Book book, Publisher publisher) {
        authorRepository.save(author);
        bookRepository.save(book);
        publisherRepository.save(publisher);
    }


}
