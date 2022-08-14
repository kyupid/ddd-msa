package com.kyupid.kshop.product.repository;

import com.kyupid.kshop.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
