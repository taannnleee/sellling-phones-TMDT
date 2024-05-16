package org.example.websitesellingphonesbackend.service;

import org.example.websitesellingphonesbackend.entities.Cart;
import org.example.websitesellingphonesbackend.entities.Customer;

public interface CartService {
    void insertCart(Cart cart);
    Cart getCartByCustomer(Customer customer);
    Cart getCartByCartId(Long id);
}
