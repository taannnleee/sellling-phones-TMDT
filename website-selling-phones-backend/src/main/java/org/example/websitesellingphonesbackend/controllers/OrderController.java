package org.example.websitesellingphonesbackend.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.entities.*;
import org.example.websitesellingphonesbackend.model.OrderModel;
import org.example.websitesellingphonesbackend.service.AddressService;
import org.example.websitesellingphonesbackend.service.CartService;
import org.example.websitesellingphonesbackend.service.LineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    @Autowired
    CartService cartService;

    @Autowired
    LineItemService lineItemService;
    @Autowired
    AddressService addressService;
    @Autowired
    OrderModel orderModel;
    @GetMapping()
    public String getViewHome(HttpSession session, Model model) {
        Cart cart = (Cart)session.getAttribute("cart");
        Customer  customer = (Customer)session.getAttribute("customer");
        try {
            Cart cart_order =  cartService.getCartByCartId(cart.getCartId());
            List<LineItem> listItem =  orderModel.listLineItemOfCart(cart_order);
            if(listItem ==null ||listItem.isEmpty()){
                return "redirect:/cart/load-cart";
            }
            else {
                Address address =  addressService.getAddressByCustomer(customer);
                model.addAttribute("cart_order", cart_order);
                model.addAttribute("listItem", listItem);
                model.addAttribute("customer", customer);
                model.addAttribute("address", address);
                return "views/order";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi tải trang: " + e.getMessage());
            return "views/error";
        }
    }

}
