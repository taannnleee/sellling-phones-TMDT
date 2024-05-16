package org.example.websitesellingphonesbackend.service;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface EmailService {
    public void sendEmail(String to, String subject, String text);
//    public void sendInvoiceEmail(String to, String subject, List<Ticket> tickets, Transaction transaction, List<ShoppingCartItem> shoppingCartItems);
}
