package org.example.websitesellingphonesbackend.service;

import org.example.websitesellingphonesbackend.entities.Cart;
import org.example.websitesellingphonesbackend.entities.Customer;
import org.example.websitesellingphonesbackend.entities.Order_Product;

import java.util.List;

public interface OrderService {
    void insertOrder(Cart cart, Customer customer, String paymentType);
    List<Order_Product> getOrdersByCustomer(Customer customer);
}
