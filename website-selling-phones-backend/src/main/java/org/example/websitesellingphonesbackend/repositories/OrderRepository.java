package org.example.websitesellingphonesbackend.repositories;

import org.example.websitesellingphonesbackend.entities.Customer;
import org.example.websitesellingphonesbackend.entities.Order_Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order_Product, Long> {
    List<Order_Product> findByCustomer(Customer customer);
    Order_Product findByOrderId(Long id);

}
