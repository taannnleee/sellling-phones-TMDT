package org.example.websitesellingphonesbackend.repositories;

import org.example.websitesellingphonesbackend.entities.Address;
import org.example.websitesellingphonesbackend.entities.Admin;
import org.example.websitesellingphonesbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    public Address getAddressByCustomer(Customer customer);
}
