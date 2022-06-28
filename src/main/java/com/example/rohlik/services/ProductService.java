package com.example.rohlik.services;

import com.example.rohlik.models.Product;
import com.example.rohlik.models.ProductQantity;
import com.example.rohlik.repositories.OrderRepository;
import com.example.rohlik.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements ProductInterface{
    private ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ResponseEntity<Object> creatingProduct(String name, Integer price, Integer quantityInStock) {
        productRepository.save(new Product(name,price,quantityInStock));
        return ResponseEntity.status(HttpStatus.CREATED).body("Product was created");
    }

    @Override
    public ResponseEntity<Object> deleteProduct(long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Product was dedleted");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product wasn't found");
        }
    }
    @Override
    public void updateProductQuantityInStock(Product product, int quantity) {
        product.setQuantityInStock(product.getQuantityInStock() - quantity);
        productRepository.save(product);
    }

    @Override
    public ResponseEntity<Object> actualisationOfProduct(Product upgradeProduct, long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            if (upgradeProduct.getName()!=null){
                product.get().setName(upgradeProduct.getName());
            }
            if (upgradeProduct.getPrice()!=null){
                product.get().setPrice(upgradeProduct.getPrice());
            }
            if (upgradeProduct.getQuantityInStock()!=null){
                product.get().setQuantityInStock(upgradeProduct.getQuantityInStock());
            }
            productRepository.save(product.get());
            return ResponseEntity.status(HttpStatus.OK).body("Product is updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This product does not exist.");
    }
    @Override
    public Product findProductByName(String name) {
        return productRepository.findProductByName(name);
    }


}
