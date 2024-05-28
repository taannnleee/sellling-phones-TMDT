package org.example.websitesellingphonesbackend.repositories;

import org.example.websitesellingphonesbackend.entities.Customer;
import org.example.websitesellingphonesbackend.entities.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    List<ProductDetail> findProductsByNameContaining(String productName);
    ProductDetail getProductDetailByProductProductID(Long id);
}