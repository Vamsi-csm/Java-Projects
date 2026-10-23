package com.vamsi.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;

@Configuration
public class SeedData {
    @Bean
    CommandLineRunner seed(BookRepository repo) {
        return args -> {
            if (repo.count() > 0) return;
            add(repo, "Effective Java", "Joshua Bloch", "42.50", 10);
            add(repo, "Clean Code", "Robert C. Martin", "35.00", 8);
            add(repo, "Spring in Action", "Craig Walls", "39.99", 5);
            add(repo, "Head First Java", "Kathy Sierra", "29.99", 12);
        };
    }
    private void add(BookRepository r, String t, String a, String p, int s) {
        Book b = new Book(); b.setTitle(t); b.setAuthor(a); b.setPrice(new BigDecimal(p)); b.setStock(s); r.save(b);
    }
}
