package com.kyupid.kshop.product.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer price;

    protected Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public void changeAllInfo(Product changingProduct) {
        this.name = changingProduct.getName();
        this.price = changingProduct.getPrice();
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changePrice(Integer price) {
        this.price = price;
    }
}
