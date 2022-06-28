package com.example.rohlik.repositories;

import com.example.rohlik.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String name);
    Product findProductByName(String name);
}
