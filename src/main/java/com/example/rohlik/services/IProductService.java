package com.example.rohlik.services;

import com.example.rohlik.models.Product;
import org.springframework.http.ResponseEntity;

public interface IProductService {

    ResponseEntity<Object> creatingProduct(String name, Integer pricePerUnit, Integer productQantityInStock);

    ResponseEntity<Object> deleteProduct(long id);

    void updateProductQuantityInStock(Product product, int quantity);

    ResponseEntity<Object> actualisationOfProduct(Product upgradeProduct, long id);

    Product findProductByName(String name);


}
