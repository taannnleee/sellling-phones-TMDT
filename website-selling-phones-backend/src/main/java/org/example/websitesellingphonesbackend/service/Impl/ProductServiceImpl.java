package org.example.websitesellingphonesbackend.service.Impl;

import org.example.websitesellingphonesbackend.entities.Category;
import org.example.websitesellingphonesbackend.entities.Product;
import org.example.websitesellingphonesbackend.repositories.ProductRepository;
import org.example.websitesellingphonesbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProductsByStatus(String status) {
        return productRepository.getAllByStatus(status);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setProductDetail(product.getProductDetail());
            existingProduct.setCategory(product.getCategory());
            return productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public Product getProductByProductDetailId(Long productDetailId) {
        return productRepository.findByProductDetailProductDetailId(productDetailId);
    }
    public int countProductByCategoryAndStatus(Category category, String string) {
        return productRepository.countProductByCategoryAndStatus(category, string);
    }
}