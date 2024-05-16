package org.example.websitesellingphonesbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.entities.Product;
import org.example.websitesellingphonesbackend.entities.ProductDetail;
import org.example.websitesellingphonesbackend.service.AccountService;
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
@RequestMapping("/admin_authentication")
public class AdminController {
    private final AccountService accountService;
    @Autowired
    ProductService productService;

    @Autowired
    ProductDetailService productDetailService;
        @GetMapping("/admin")
    public String Login(Model model) {
        try {
            return "view/viewAdmin";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "views/error";
        }
    }
//    @GetMapping("/admin")
//    public String Login(Model model) {
//        try {
//            return "views/adminviews/admin";
//        } catch (Exception e) {
//            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
//            return "views/error";
//        }
//    }
//
//    @GetMapping("/product_details")
//    public String ViewDetails(Model model) {
//        try {
//            return "views/chitietsanpham";
//        } catch (Exception e) {
//            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
//            return "views/error";
//        }
//    }
//    @GetMapping("/create_products")
//    public String CreateProduct(Model model) {
//        try {
//            return "views/adminviews/createProductJson";
//        } catch (Exception e) {
//            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
//            return "views/error";
//        }
//    }
}
