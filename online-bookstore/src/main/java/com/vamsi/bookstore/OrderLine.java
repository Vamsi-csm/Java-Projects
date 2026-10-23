package com.vamsi.bookstore;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderLine {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookId;
    private String title;
    private int quantity;
    private BigDecimal unitPrice;

    public OrderLine() {}
    public OrderLine(Long bookId, String title, int quantity, BigDecimal unitPrice) {
        this.bookId = bookId; this.title = title; this.quantity = quantity; this.unitPrice = unitPrice;
    }
    public Long getId() { return id; }
    public Long getBookId() { return bookId; }
    public String getTitle() { return title; }
    public int getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
}
