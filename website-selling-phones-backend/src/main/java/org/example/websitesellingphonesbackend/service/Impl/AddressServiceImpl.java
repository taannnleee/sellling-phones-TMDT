package org.example.websitesellingphonesbackend.service.Impl;

import org.example.websitesellingphonesbackend.entities.Address;
import org.example.websitesellingphonesbackend.entities.Customer;
import org.example.websitesellingphonesbackend.repositories.AddressRepository;
import org.example.websitesellingphonesbackend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;
    @Override
    public Address  getAddressByCustomer(Customer customer){
        try {
             return addressRepository.getAddressByCustomer(customer);
        }catch (Exception e) {
            System.out.println("Error" + e);
        }
        return null;
    }

}
