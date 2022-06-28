package com.example.rohlik.models;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Integer quantityInStock;
    private Integer price;



    public Product(String name, Integer price,Integer quantityInStock  ) {
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public Product(long id) {
        this.id = id;
    }

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


}
