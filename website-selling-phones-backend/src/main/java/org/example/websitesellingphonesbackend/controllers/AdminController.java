package org.example.websitesellingphonesbackend.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.entities.Category;
import org.example.websitesellingphonesbackend.entities.Customer;
import org.example.websitesellingphonesbackend.entities.Product;
import org.example.websitesellingphonesbackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    CategoryService categoryService;
    @GetMapping("/dashboard")
    public String admin(Model model) {
        try {
            return "view/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "views/error";
        }
    }
    @GetMapping("/product")
    public String adminProduct(Model model) {
        try {
            List<Product> products =  productService.getAllProductsByStatus("on");
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
            return "dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "views/error";
        }
    }
    @GetMapping("/category")
    public String category(HttpSession session, Model model) {
        String addProductSuccessMessage = (String) session.getAttribute("addCategorySuccessMessage"); // Lấy thông báo từ session
        if (addProductSuccessMessage != null) {
            model.addAttribute("addCategorySuccessMessage", true); // Thêm thông báo vào model
            session.removeAttribute("addCategorySuccessMessage"); // Xóa thông báo từ session sau khi đã sử dụng
        }

        List<Category> categories = categoryService.getAllCategories();
        Map<Long, Integer> productCountMap = new HashMap<>();
        for (Category category : categories) {
            int productCount = productService.countProductByCategoryAndStatus(category, "on");
            productCountMap.put(category.getCategoryId(), productCount);
        }
        model.addAttribute("categories", categories);
        model.addAttribute("productCountMap", productCountMap);
        return "views/adminviews/category-admin";
    }
    @GetMapping("/statistics")
    public String statistics(Model model) {
        try {
            return "view/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "views/error";
        }
    }
    @GetMapping("/logout")
    public String logout(Model model) {
        try {
            return "view/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "views/error";
        }
    }


}
