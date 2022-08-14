package com.kyupid.kshop.product.application;

import com.kyupid.kshop.product.domain.Product;
import com.kyupid.kshop.product.domain.ProductRepository;
import com.kyupid.kshop.product.exception.DuplicatedNameException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public Product showProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException(productId.toString()));
    }

    public Page<Product> showProductsByPagination(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional
    public Product createProduct(Product savingProduct) {
        boolean hasSameName = productRepository.existsByName(savingProduct.getName());
        if (hasSameName) {
            throw new DuplicatedNameException();
        }
        return productRepository.save(savingProduct);
    }

    @Transactional
    public Product editProduct(Long productId, Product changingProduct) {
        Product productById = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException(productId.toString()));
        productById.changeAllInfo(changingProduct);
        return productById;
    }

    @Transactional
    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new NoSuchElementException(productId.toString());
        }

        productRepository.deleteById(productId);
    }
}
