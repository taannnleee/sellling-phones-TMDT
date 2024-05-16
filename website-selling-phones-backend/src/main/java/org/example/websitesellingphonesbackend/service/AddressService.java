package org.example.websitesellingphonesbackend.service;

import org.example.websitesellingphonesbackend.entities.Address;
import org.example.websitesellingphonesbackend.entities.Customer;
import org.example.websitesellingphonesbackend.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface AddressService {
    Address getAddressByCustomer(Customer customer);
}
