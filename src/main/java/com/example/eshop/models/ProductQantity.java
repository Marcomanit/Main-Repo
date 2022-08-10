package com.example.eshop.models;

import javax.persistence.*;

@Entity
public class ProductQantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int quantityOfProduct;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Order order;


    public ProductQantity() {
    }

    public ProductQantity(Product product, int quantityOfProduct) {
        this.quantityOfProduct = quantityOfProduct;
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantityOfProduct() {
        return quantityOfProduct;
    }

    public void setQuantityOfProduct(int quantityOfProduct) {
        this.quantityOfProduct = quantityOfProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
