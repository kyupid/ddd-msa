package com.kyupid.kshop.product.presentation.dto;

import com.kyupid.kshop.product.domain.Product;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class ProductRequestData {

    @NotBlank
    private String name;

    @NotNull
    private Integer price;

    public Product toEntity() {
        return new Product(name, price);
    }
}
