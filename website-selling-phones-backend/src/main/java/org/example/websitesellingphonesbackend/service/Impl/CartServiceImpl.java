package org.example.websitesellingphonesbackend.service.Impl;

import org.example.websitesellingphonesbackend.entities.Cart;
import org.example.websitesellingphonesbackend.entities.Customer;
import org.example.websitesellingphonesbackend.repositories.Admin.UserRepository;
import org.example.websitesellingphonesbackend.repositories.CartRepository;
import org.example.websitesellingphonesbackend.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    @Autowired
    CartRepository cartRepository;
    @Override
    public void insertCart(Cart cart){
        try{
            cartRepository.save(cart);
        }catch(Exception e){
            logger.error("Lỗi " + e.getMessage());
        }

    }

    @Override
    public Cart getCartByCustomer(Customer customer) {
        try {
            return cartRepository.findByCustomer(customer);

        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace(); // Hoặc log ngoại lệ
            return null; // hoặc thực hiện hành động phù hợp khác
        }
    }

    @Override
    public Cart getCartByCartId(Long id) {
        try {
            return cartRepository.findByCartId(id);

        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace(); // Hoặc log ngoại lệ
            return null; // hoặc thực hiện hành động phù hợp khác
        }
    }



}
