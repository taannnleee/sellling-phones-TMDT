package org.example.websitesellingphonesbackend.repositories;
import org.example.websitesellingphonesbackend.entities.Category;
import org.example.websitesellingphonesbackend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    Product findByProductDetail_ProductDetailId(Long productDetailId);
    Product getProductsByProductID(Long id);
    Product findByProductDetailProductDetailId(Long productDetailId);
    List<Product> getAllByStatus(String status);
    int countProductByCategoryAndStatus(Category category, String string);

}
