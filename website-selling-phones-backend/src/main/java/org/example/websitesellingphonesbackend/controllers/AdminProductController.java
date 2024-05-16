package org.example.websitesellingphonesbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.DTO.ProductDetailDTO;
import org.example.websitesellingphonesbackend.entities.Product;
import org.example.websitesellingphonesbackend.entities.ProductDetail;
import org.example.websitesellingphonesbackend.service.AccountService;
import org.example.websitesellingphonesbackend.service.Impl.ProductDetailServiceImpl;
import org.example.websitesellingphonesbackend.service.ProductDetailService;
import org.example.websitesellingphonesbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/adminProduct")
public class AdminProductController {
    private final AccountService accountService;
    @Autowired
    ProductService productService;

    private final ProductDetailServiceImpl productDetailService;


    @GetMapping()
    public String Login(Model model) {
        try {
            List<Product> listProduct = productService.getAllProducts();

            model.addAttribute("listProduct", listProduct);

            return "views/adminviews/adminProduct";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "views/error";
        }
    }
    @PostMapping("/add-product")
    public ResponseEntity<String> addProduct(@RequestBody ProductDetailDTO productDetailDTO) {
        productDetailService.addProduct(productDetailDTO);
        return ResponseEntity.ok("Thêm sản phẩm thành công!");
    }
    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productDetailService.deleteProduct(id);
        return ResponseEntity.ok("Thêm sản phẩm thành công!");
    }
    @GetMapping("/{id}")
    public ProductDetail getProductDetail(@PathVariable Long id) {
        return productDetailService.getProductDetail(id);
    }
    @PutMapping("/update-product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductDetailDTO productDetailDTO) {
        productDetailService.updateProduct(id, productDetailDTO);
        return ResponseEntity.ok("Cập nhật sản phẩm thành công!");    }
}
