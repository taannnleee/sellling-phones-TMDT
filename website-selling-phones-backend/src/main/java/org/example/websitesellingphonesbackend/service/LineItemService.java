package org.example.websitesellingphonesbackend.service;

import org.example.websitesellingphonesbackend.entities.Cart;
import org.example.websitesellingphonesbackend.entities.LineItem;
import org.example.websitesellingphonesbackend.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface LineItemService {
    void insertLineItem(Cart cart, Product product);
    List<LineItem> listLineItemOfCart(Cart cart);
    void decreaseQuantity(Cart cart, Product product);
    void increaseQuantity(Cart cart, Product product);
    void deleteLineItemById(Long lineItemId);
    BigDecimal updateTotalPrice(Cart cart);

    void deleteAllLineItemByCart(Cart delete_cart);
}
