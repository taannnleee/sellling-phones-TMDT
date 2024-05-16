package org.example.websitesellingphonesbackend.repositories.Admin;

import org.example.websitesellingphonesbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Customer, Long> {
}
