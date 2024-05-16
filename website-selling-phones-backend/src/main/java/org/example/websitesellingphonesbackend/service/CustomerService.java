package org.example.websitesellingphonesbackend.service;

import org.example.websitesellingphonesbackend.DTO.CustomerDTO;
import org.example.websitesellingphonesbackend.Enum.EMessage;
import org.example.websitesellingphonesbackend.entities.Address;
import org.example.websitesellingphonesbackend.entities.Cart;
import org.example.websitesellingphonesbackend.entities.Customer;

import java.util.List;

public interface CustomerService {
    EMessage checkCustomer(CustomerDTO customerDTO);
    Customer authenticateCustomer(String email, String password);
    void registerCustomer(CustomerDTO customerDTO, Cart cart, Address address) ;
    Customer getCustomerById(Long customerDTO_id);
    EMessage changePassword(String email, String oldPassword, String newPassword);

    EMessage changePassword(String email,String newPassword);
    Customer getCustomerByEmail(String email);
    Customer recharge(Long cusId, String denominations);
    Customer deductMoney(Long cusId, String denominations);
    List<Customer> getAllCustomers();
    void deleteCustomer(Long id);
    void addCustomer(CustomerDTO customerDTO);
    void updateCustomer(Long id, CustomerDTO customerDTO);

}
