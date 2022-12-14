package com.kyupid.kshop.product.application;

import com.kyupid.kshop.product.domain.Product;
import com.kyupid.kshop.product.infra.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public Product showProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
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
                .orElseThrow(() -> new ProductNotFoundException(productId));
        boolean hasSameName = productRepository.existsByName(changingProduct.getName());
        if (hasSameName) {
            throw new DuplicatedNameException();
        }
        productById.changeAllInfo(changingProduct);
        return productById;
    }

    @Transactional
    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException(productId);
        }

        productRepository.deleteById(productId);
    }
}
