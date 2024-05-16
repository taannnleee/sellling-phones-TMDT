package org.example.websitesellingphonesbackend.controllers;


import org.example.websitesellingphonesbackend.DTO.ProductDetailDTO;
import org.example.websitesellingphonesbackend.entities.ProductDetail;
import org.example.websitesellingphonesbackend.service.Impl.ProductDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import java.util.List;

@RestController
@RequestMapping("/api/productDetails")
public class ProductDetailController {
    private final ProductDetailServiceImpl productDetailService;

    @Autowired
    public ProductDetailController(ProductDetailServiceImpl productDetailService) {
        this.productDetailService = productDetailService;
    }

    @GetMapping
    public List<ProductDetail> getAllProductDetails() {
        return productDetailService.getAllProductDetails();
    }

}