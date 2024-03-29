package com.example.eshop.repositories;

import com.example.eshop.models.ProductQantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductQuantityRepository extends JpaRepository<ProductQantity, Long> {
    List<ProductQantity> findAllByOrder_Id(Long id);

}
