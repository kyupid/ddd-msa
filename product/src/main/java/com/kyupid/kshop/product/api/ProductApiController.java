package com.kyupid.kshop.product.api;

import com.kyupid.kshop.product.api.dto.ProductRequestData;
import com.kyupid.kshop.product.api.dto.ProductResponseData;
import com.kyupid.kshop.product.entity.Product;
import com.kyupid.kshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductApiController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ProductResponseData getProduct(@PathVariable Long productId) {
        Product product = productService.showProductById(productId);
        return ProductResponseData.from(product);
    }

    @GetMapping
    public Page<ProductResponseData> getProductsByPagination(@ParameterObject @PageableDefault Pageable pageable) {
        return productService.showProductsByPagination(pageable)
                .map(ProductResponseData::from);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseData addProduct(@RequestBody @Valid ProductRequestData request) {
        Product savedProduct = productService.createProduct(request.toEntity());
        return ProductResponseData.from(savedProduct);
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseData updateProduct(@PathVariable Long productId,
                                             @RequestBody @Valid ProductRequestData request) {
        Product updatedProduct = productService.editProduct(productId, request.toEntity());
        return ProductResponseData.from(updatedProduct);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
}
