package com.vamsi.bookstore;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    private final BookRepository books;
    private final OrderService orders;
    private final OrderRepository orderRepo;

    public BookController(BookRepository books, OrderService orders, OrderRepository orderRepo) {
        this.books = books; this.orders = orders; this.orderRepo = orderRepo;
    }

    @GetMapping("/books")
    public List<Book> list(@RequestParam(required = false) String q) {
        return (q == null || q.isBlank()) ? books.findAll()
            : books.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(q, q);
    }

    @PostMapping("/books") @ResponseStatus(HttpStatus.CREATED)
    public Book add(@Valid @RequestBody Book b) { return books.save(b); }

    @PostMapping("/orders") @ResponseStatus(HttpStatus.CREATED)
    public BookOrder order(@RequestBody OrderService.OrderRequest req) { return orders.place(req); }

    @GetMapping("/orders")
    public List<BookOrder> orders() { return orderRepo.findAll(); }
}
