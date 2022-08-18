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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ReservedStock> reservedStockList = new ArrayList<>();

    @Column(unique = true)
    private String name;

    private Integer price;

    @ColumnDefault("0")
    private Integer stock;

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, Integer price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
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

    public void commitStockSubtraction(Integer reservedQuantity) {
        this.stock -= reservedQuantity;
    }
}
