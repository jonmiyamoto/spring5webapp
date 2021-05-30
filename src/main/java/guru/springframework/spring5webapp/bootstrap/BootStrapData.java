package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher milton = new Publisher ("Milton Publishing", "919 W Barry", "Chicago", "IL", "60657");
        publisherRepository.save(milton);

        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("Domain Driven Design","123123");
        ddd.setPublisher(milton);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod","Johnson");
        Book noEJB= new Book("J2EE Development without EJB","4444");
        noEJB.setPublisher(milton);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        authorRepository.save(rod);
        bookRepository.save(noEJB);



        milton.getBooks().add(ddd);
        milton.getBooks().add(noEJB);


        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books " + bookRepository.count());
        System.out.println("Number of Authors " + authorRepository.count());
        System.out.println("Number of Publishers " + publisherRepository.count());

        System.out.println("Publisher Number of Books : " + milton.getBooks().size());


    }
}
