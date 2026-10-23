package com.vamsi.bookstore;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    public record Item(Long bookId, int quantity) {}
    public record OrderRequest(String customerName, List<Item> items) {}

    private final BookRepository books;
    private final OrderRepository orders;

    public OrderService(BookRepository books, OrderRepository orders) { this.books = books; this.orders = orders; }

    @Transactional
    public BookOrder place(OrderRequest req) {
        if (req.items() == null || req.items().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order has no items");
        BookOrder order = new BookOrder();
        order.setCustomerName(req.customerName());
        BigDecimal total = BigDecimal.ZERO;
        for (Item i : req.items()) {
            if (i.quantity() <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid quantity");
            Book b = books.findById(i.bookId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
            if (b.getStock() < i.quantity())
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Not enough stock for " + b.getTitle());
            b.setStock(b.getStock() - i.quantity());
            order.getLines().add(new OrderLine(b.getId(), b.getTitle(), i.quantity(), b.getPrice()));
            total = total.add(b.getPrice().multiply(BigDecimal.valueOf(i.quantity())));
        }
        order.setTotal(total);
        return orders.save(order);
    }
}
