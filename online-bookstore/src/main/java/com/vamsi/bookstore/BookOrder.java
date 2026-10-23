package com.vamsi.bookstore;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book_order")
public class BookOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private Instant createdAt = Instant.now();
    private BigDecimal total = BigDecimal.ZERO;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderLine> lines = new ArrayList<>();

    public Long getId() { return id; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String v) { customerName = v; }
    public Instant getCreatedAt() { return createdAt; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal v) { total = v; }
    public List<OrderLine> getLines() { return lines; }
}
