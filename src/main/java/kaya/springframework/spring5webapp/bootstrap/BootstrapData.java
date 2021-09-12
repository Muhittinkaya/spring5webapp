package kaya.springframework.spring5webapp.bootstrap;

import kaya.springframework.spring5webapp.model.Author;
import kaya.springframework.spring5webapp.model.Book;
import kaya.springframework.spring5webapp.repositories.AuthorRepository;
import kaya.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Bootstrap Data has started");

        Author jackLondon = new Author("Jack", "London");
        Book whiteFang = new Book("White Fang","12343232");

        jackLondon.getBooks().add(whiteFang);
        whiteFang.getAuthors().add(jackLondon);

        authorRepository.save(jackLondon);
        bookRepository.save(whiteFang);

        Author charlesDickens = new Author("Charles", "Dickens");
        Book aTaleCities = new Book("A Tale of Two Cities","3241435424");

        charlesDickens.getBooks().add(aTaleCities);
        aTaleCities.getAuthors().add(charlesDickens);

        authorRepository.save(charlesDickens);
        bookRepository.save(aTaleCities);

        System.out.println("Number of books: " + bookRepository.count());
    }
}
