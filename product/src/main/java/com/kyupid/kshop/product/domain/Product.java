package com.kyupid.kshop.product.domain;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private Integer price;

    @ColumnDefault("0")
    private Integer quantityLeft;

    protected Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, Integer price, Integer quantityLeft) {
        this.name = name;
        this.price = price;
        this.quantityLeft = quantityLeft;
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
