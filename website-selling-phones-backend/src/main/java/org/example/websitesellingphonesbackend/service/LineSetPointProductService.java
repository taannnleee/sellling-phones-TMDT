package org.example.websitesellingphonesbackend.service;

import org.example.websitesellingphonesbackend.entities.Cart;
import org.example.websitesellingphonesbackend.entities.LineSetPointProduct;
import org.example.websitesellingphonesbackend.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface LineSetPointProductService {
    void insertLineSetPointProduct(Cart cart, Product product);
    List<LineSetPointProduct> listLineSetPointProductOfCart(Cart cart);
    void decreaseQuantity(Cart cart, Product product);
    void increaseQuantity(Cart cart, Product product);
    void deleteLineSetPointProductById(Long lineItemId);
    BigDecimal updateTotalPrice(Cart cart);

    void deleteAllLineSetPointProductByCart(Cart delete_cart);
    void checkAndRemoveExpiredDeposits();

}