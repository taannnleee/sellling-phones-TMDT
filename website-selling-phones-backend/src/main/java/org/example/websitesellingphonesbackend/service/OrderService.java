package org.example.websitesellingphonesbackend.service;

import org.example.websitesellingphonesbackend.entities.Cart;
import org.example.websitesellingphonesbackend.entities.Customer;
import org.example.websitesellingphonesbackend.entities.Order_Product;

import java.util.List;

public interface OrderService {
    Order_Product insertOrder(Cart cart, Customer customer, String paymentType);
    List<Order_Product> getOrdersByCustomer(Customer customer);
    List<Order_Product> getAllOrder();
    Order_Product getOrderById(Long id);
    void updateStatusOrder(Long id);
    public  long countOrder();
}
