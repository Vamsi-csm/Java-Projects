package com.vamsi.bookstore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<BookOrder, Long> {}
