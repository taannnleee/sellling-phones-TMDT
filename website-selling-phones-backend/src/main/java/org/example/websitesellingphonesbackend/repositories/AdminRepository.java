package org.example.websitesellingphonesbackend.repositories;

import org.example.websitesellingphonesbackend.entities.Admin;
import org.example.websitesellingphonesbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends  JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}

