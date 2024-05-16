package org.example.websitesellingphonesbackend.controllers;

import jakarta.persistence.criteria.Order;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.entities.Customer;
import org.example.websitesellingphonesbackend.entities.Order_Product;
import org.example.websitesellingphonesbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/invoice")
public class InvoicecController {
    @Autowired
    OrderService orderService;

    @GetMapping()
    public String getViewOrder(HttpSession session, Model model) {
        Customer customer = (Customer)session.getAttribute("customer");
        try {
            List<Order_Product> orders = orderService.getOrdersByCustomer(customer);
            model.addAttribute("orders", orders);
            if (!orders.isEmpty()) {
                model.addAttribute("order", orders.get(0)); // Thêm dòng này
            }
            return "views/invoice";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi tải trang: " + e.getMessage());
            return "views/error";
        }
    }

}
