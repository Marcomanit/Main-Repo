package com.example.eshop.repositories;

import com.example.eshop.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByPaymantAndCreationDateBefore(boolean payment, Date date);
}
