package com.kyupid.kshop.product.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private Integer stock;

    @Version
    private Integer version;

    public Product(String name, Integer price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void changeAllInfo(Product changingProduct) {
        this.name = changingProduct.getName();
        this.price = changingProduct.getPrice();
    }

    public void decreaseStock(Integer requestQuantity) {
        this.stock -= requestQuantity;
    }

    public void increaseStock(Integer requestQuantity) {
        this.stock += requestQuantity;
    }

    public boolean hasAvailableStock(Integer requestQuantity) {
        return stock >= requestQuantity;
    }
}
