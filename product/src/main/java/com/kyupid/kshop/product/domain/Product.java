package com.kyupid.kshop.product.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    @Column(unique = true)
    private String name;

    private Integer price;

    @ColumnDefault("0")
    private Integer quantityLeft;

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
