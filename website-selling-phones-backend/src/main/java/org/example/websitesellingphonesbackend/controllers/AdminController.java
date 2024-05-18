package org.example.websitesellingphonesbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.entities.Customer;
import org.example.websitesellingphonesbackend.entities.Product;
import org.example.websitesellingphonesbackend.entities.ProductDetail;
import org.example.websitesellingphonesbackend.service.AccountService;
import org.example.websitesellingphonesbackend.service.CustomerService;
import org.example.websitesellingphonesbackend.service.Impl.ProductDetailServiceImpl;
import org.example.websitesellingphonesbackend.service.ProductDetailService;
import org.example.websitesellingphonesbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin_authentication/admin")
public class AdminController {
    private final AccountService accountService;
    @Autowired
    ProductService productService;

    @Autowired
    ProductDetailService productDetailService;
    @Autowired
    CustomerService customerService;
    @GetMapping()
    public String admin(Model model) {
        try {
            return "view/viewAdmin";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "views/error";
        }
    }
    @GetMapping("/product")
    public String adminProduct(Model model) {
        try {
            List<Product> products =  productService.getAllProducts();
            model.addAttribute("products", products);
            return "views/adminviews/product-admin";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "views/error";
        }
    }
    @GetMapping("/customer")
    public String adminCustomer(Model model) {
        try {
            List<Customer> customers =  customerService.getAllCustomers();
            model.addAttribute("customers", customers);
            return "views/adminviews/customer-admin";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "views/error";
        }
    }
    @GetMapping("/order")
    public String order(Model model) {
        try {
            return "view/viewAdmin";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "views/error";
        }
    }
    @GetMapping("/category")
    public String category(Model model) {
        try {
            return "view/viewAdmin";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "views/error";
        }
    }
    @GetMapping("/statistics")
    public String statistics(Model model) {
        try {
            return "view/viewAdmin";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "views/error";
        }
    }
    @GetMapping("/logout")
    public String logout(Model model) {
        try {
            return "view/viewAdmin";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "views/error";
        }
    }


}
