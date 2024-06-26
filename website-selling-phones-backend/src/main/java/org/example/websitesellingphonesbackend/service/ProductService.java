package org.example.websitesellingphonesbackend.service;

import org.example.websitesellingphonesbackend.entities.Category;
import org.example.websitesellingphonesbackend.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProductsByStatus(String status);
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    Product getProductByProductDetailId(Long productDetailId);
    int countProductByCategoryAndStatus(Category category, String string);
    List<Product> getAllByCategoryAndStatus(Category category, String status);
    void save(Product product);
    List<Product> getAllByCategory(Category category);
    List<Product> getProductsByCategoryId(Long id);
    List<Product> getAllProduct();
    int countProductByCategoryCategoryId(long id);
    public  long countProduct();
}