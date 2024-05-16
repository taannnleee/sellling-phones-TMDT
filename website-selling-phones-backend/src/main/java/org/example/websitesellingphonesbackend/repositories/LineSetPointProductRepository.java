package org.example.websitesellingphonesbackend.repositories;

import org.example.websitesellingphonesbackend.entities.Cart;
import org.example.websitesellingphonesbackend.entities.LineSetPointProduct;
import org.example.websitesellingphonesbackend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineSetPointProductRepository extends JpaRepository<LineSetPointProduct, Long> {
    LineSetPointProduct findByProductAndCart(Product product, Cart cart);

    List<LineSetPointProduct> findByCart(Cart cart);
    void deleteById(Long id);

    void deleteAllByCart(Cart cart);
}
