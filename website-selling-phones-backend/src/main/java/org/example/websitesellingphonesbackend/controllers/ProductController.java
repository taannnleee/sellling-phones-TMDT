package org.example.websitesellingphonesbackend.controllers;

import org.example.websitesellingphonesbackend.entities.Product;
import org.example.websitesellingphonesbackend.service.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    @Autowired
    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping
    public List<Product> getAllProduct() {
        return productServiceImpl.getAllProductsByStatus("on");
    }
}
