package com.vamsi.bookstore;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String title;
    @NotBlank private String author;
    @NotNull @Positive private BigDecimal price;
    @PositiveOrZero private int stock;

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String v) { title = v; }
    public String getAuthor() { return author; }
    public void setAuthor(String v) { author = v; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal v) { price = v; }
    public int getStock() { return stock; }
    public void setStock(int v) { stock = v; }
}
