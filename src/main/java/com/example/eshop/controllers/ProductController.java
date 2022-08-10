package com.example.eshop.controllers;

import com.example.eshop.models.Product;
import com.example.eshop.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductServiceImpl productService;
    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> creatingProduct(@RequestBody Product product){
        return productService.creatingProduct(product.getName(),product.getPrice(),product.getQuantityInStock());

    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> deletingProduct(@PathVariable long id){
       return productService.deleteProduct(id);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatingProduct(@RequestBody Product product,@PathVariable long id){
        return productService.actualisationOfProduct(product,id);
    }


}
