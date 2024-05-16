package org.example.websitesellingphonesbackend.repositories;

import org.example.websitesellingphonesbackend.entities.Customer;
import org.example.websitesellingphonesbackend.entities.Evaluate;
import org.example.websitesellingphonesbackend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluateRepository extends JpaRepository<Evaluate, Long> {

    public List<Evaluate> findByProduct(Product product);

}
