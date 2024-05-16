package org.example.websitesellingphonesbackend.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.entities.Cart;
import org.example.websitesellingphonesbackend.entities.Customer;
import org.example.websitesellingphonesbackend.entities.LineItem;
import org.example.websitesellingphonesbackend.entities.Order_Product;
import org.example.websitesellingphonesbackend.service.CartService;
import org.example.websitesellingphonesbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    CartService cartService;
    @Autowired
    OrderService orderService;
    @PostMapping()
    public String checkout(HttpSession session,@RequestParam("paymentType") String paymentType, @RequestParam("cartId") Long cartId, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        try {
            Cart cart = cartService.getCartByCartId(cartId);
            orderService.insertOrder(cart,customer,paymentType);
            List<Order_Product> orders = orderService.getOrdersByCustomer(customer);
            model.addAttribute("orders", orders);
            if (!orders.isEmpty()) {
                Order_Product order = orders.get(orders.size() - 1);
                if (order.getOrderDetailLines() != null) {
                    model.addAttribute("order", order);
                }
            }
            return "views/invoice";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi tải trang: " + e.getMessage());
            return "views/error";
        }
    }
    @PostMapping("cancel")
    public String cancel(HttpSession session, Model model) {

        try {
            return "redirect:/cart/load-cart";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi tải trang: " + e.getMessage());
            return "views/error";
        }
    }

}
