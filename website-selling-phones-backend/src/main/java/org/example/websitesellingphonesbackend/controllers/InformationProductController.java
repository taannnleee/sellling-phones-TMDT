package org.example.websitesellingphonesbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.entities.ProductDetail;
import org.example.websitesellingphonesbackend.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/informationProduct")
public class InformationProductController {
    @Autowired
    ProductDetailService productDetailService;
    @GetMapping
    public String index(Model model) {
        try {
            List<ProductDetail> list_Products = productDetailService.getAllProductDetails();
            model.addAttribute("list_Products", list_Products);
            return "views/informationProduct";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi tải trang: " + e.getMessage());
            return "views/error";
        }
    }
}
