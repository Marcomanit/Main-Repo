package com.example.rohlik.services;

import com.example.rohlik.models.ProductOrderDTO;
import com.example.rohlik.models.ProductQantity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOrderService {

    ResponseEntity<Object> creatingOrder(List<ProductOrderDTO> productsOrderDTO);

    ResponseEntity<Object> cancellationOrder(long id);

    ResponseEntity<Object> paymantOfTheOrder(long id);

    List<ProductQantity> createItemsInOrder(List<ProductOrderDTO> productsOrderDTO);
}
