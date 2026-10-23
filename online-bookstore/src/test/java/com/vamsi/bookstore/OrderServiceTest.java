package com.vamsi.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {
    @Autowired OrderService service;
    @Autowired BookRepository books;

    private Book newBook(int stock) {
        Book b = new Book(); b.setTitle("T"); b.setAuthor("A"); b.setPrice(new BigDecimal("10.00")); b.setStock(stock);
        return books.save(b);
    }

    @Test
    void placesOrderAndReducesStock() {
        Book b = newBook(5);
        BookOrder o = service.place(new OrderService.OrderRequest("Ravi", List.of(new OrderService.Item(b.getId(), 2))));
        assertEquals(0, new BigDecimal("20.00").compareTo(o.getTotal()));
        assertEquals(3, books.findById(b.getId()).get().getStock());
    }

    @Test
    void rejectsWhenOutOfStock() {
        Book b = newBook(1);
        assertThrows(ResponseStatusException.class,
            () -> service.place(new OrderService.OrderRequest("Ravi", List.of(new OrderService.Item(b.getId(), 3)))));
    }
}
