package org.example.websitesellingphonesbackend.service;

import org.example.websitesellingphonesbackend.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    Product getProductByProductDetailId(Long productDetailId);
}