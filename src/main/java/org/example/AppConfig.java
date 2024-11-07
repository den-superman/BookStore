package org.example;

import java.math.BigDecimal;
import org.example.model.Book;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppConfig {

    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setTitle("harry Potter");
            book.setAuthor("Denys");
            book.setIsbn("ibsn");
            book.setPrice(BigDecimal.valueOf(999));

            bookService.save(book);

            System.out.println(bookService.findAll());
        };
    }
}
