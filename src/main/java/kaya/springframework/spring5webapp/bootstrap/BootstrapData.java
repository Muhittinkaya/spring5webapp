package kaya.springframework.spring5webapp.bootstrap;

import kaya.springframework.spring5webapp.model.Author;
import kaya.springframework.spring5webapp.model.Book;
import kaya.springframework.spring5webapp.model.Publisher;
import kaya.springframework.spring5webapp.repositories.AuthorRepository;
import kaya.springframework.spring5webapp.repositories.BookRepository;
import kaya.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Bootstrap Data has started");

        Publisher isBankasi = new Publisher();
        isBankasi.setName("İş Bankası Yayınları");
        isBankasi.setAddress("Istanbul");
        isBankasi.setState("TUR");
        isBankasi.setZip("34100");

        publisherRepository.save(isBankasi);

        System.out.println("Publisher count: " + publisherRepository.count());

        Author jackLondon = new Author("Jack", "London");
        Book whiteFang = new Book("White Fang","12343232");

        jackLondon.getBooks().add(whiteFang);
        whiteFang.getAuthors().add(jackLondon);
        whiteFang.setPublisher(isBankasi);
        isBankasi.getBooks().add(whiteFang);

        authorRepository.save(jackLondon);
        bookRepository.save(whiteFang);
        publisherRepository.save(isBankasi);

        Author charlesDickens = new Author("Charles", "Dickens");
        Book aTaleCities = new Book("A Tale of Two Cities","3241435424");

        charlesDickens.getBooks().add(aTaleCities);
        aTaleCities.getAuthors().add(charlesDickens);
        aTaleCities.setPublisher(isBankasi);
        isBankasi.getBooks().add(aTaleCities);

        authorRepository.save(charlesDickens);
        bookRepository.save(aTaleCities);
        publisherRepository.save(isBankasi);

        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher number of books: " + isBankasi.getBooks().size());
    }
}
