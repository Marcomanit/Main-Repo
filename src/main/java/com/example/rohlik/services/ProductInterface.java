package com.example.rohlik.services;

import com.example.rohlik.models.Product;
import com.example.rohlik.models.ProductQantity;
import org.springframework.http.ResponseEntity;

public interface ProductInterface {

    ResponseEntity<Object> creatingProduct(String name, Integer pricePerUnit, Integer productQantityInStock);

    ResponseEntity<Object> deleteProduct(long id);

    void updateProductQuantityInStock(Product product, int quantity);

    ResponseEntity<Object> actualisationOfProduct(Product upgradeProduct, long id);

    Product findProductByName(String name);


}
