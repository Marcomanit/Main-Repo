package com.example.eshop.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean paymant;
    private Date creationDate;
    @OneToMany
    private List<ProductQantity> productQantity;


    public Order(List<ProductQantity> productQantity) {
        this.paymant=false;
        this.productQantity = productQantity;
        this.creationDate = new Date();
    }

    public Order() {
        this.paymant = paymant;
        this.creationDate = creationDate;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPaymant() {
        return paymant;
    }

    public void setPaymant(boolean paymant) {
        this.paymant = paymant;
    }

    public List<ProductQantity> getProductQantity() {
        return productQantity;
    }

    public void setProductQantity(List<ProductQantity> productQantity) {
        this.productQantity = productQantity;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
