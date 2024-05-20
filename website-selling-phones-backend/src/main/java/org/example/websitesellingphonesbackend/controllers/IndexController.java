package org.example.websitesellingphonesbackend.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.entities.Product;
import org.example.websitesellingphonesbackend.entities.ProductDetail;
import org.example.websitesellingphonesbackend.service.Impl.ProductDetailServiceImpl;
import org.example.websitesellingphonesbackend.service.ProductDetailService;
import org.example.websitesellingphonesbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/index")
public class IndexController {

    @Autowired
    ProductDetailService productDetailService;
    @Autowired
    ProductService productService;
    @GetMapping
    public String index(HttpSession  session, Model model) {

        try {
            List<ProductDetail> list_Products = getProductsOn();
            model.addAttribute("list_Products", list_Products);
            String loginSuccessMessage = (String) session.getAttribute("loginSuccessMessage"); // Lấy thông báo từ session
            if (loginSuccessMessage != null) {
                model.addAttribute("loginSuccessMessage", true); // Thêm thông báo vào model
                session.removeAttribute("loginSuccessMessage"); // Xóa thông báo từ session sau khi đã sử dụng
            }

            return "views/index";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi tải trang: " + e.getMessage());
            return "views/error";
        }
    }
    @GetMapping("/timKiemTheoTen")
    public ResponseEntity<List<ProductDetail>> searchProductDetailsByName(@RequestParam String ten, Model model) {
        List<ProductDetail> listSearchProduct = productDetailService.timKiemTheoTen(ten,getProductsOn());
        model.addAttribute("listSearchProduct", listSearchProduct);
        return new ResponseEntity<>(listSearchProduct, HttpStatus.OK);
    }

    @GetMapping("/timKiemTheoGiaTien")
    public ResponseEntity<List<ProductDetail>> searchProductDetailsByPrice(@RequestParam Double giaMin,@RequestParam Double giaMax,Model model) {
        List<ProductDetail> listSearchPriceProduct = productDetailService.timKiemTheoGiaTien(giaMin,giaMax,getProductsOn());
        model.addAttribute("listSearchPriceProduct", listSearchPriceProduct);
        return new ResponseEntity<>(listSearchPriceProduct, HttpStatus.OK);
    }

    @GetMapping("/timKiemTheoCongTySanXuat")
    public ResponseEntity<List<ProductDetail>> searchProductDetailsByCategory(@RequestParam String tenCongTy, Model model) {
        List<ProductDetail> listSearchCategoryProduct = productDetailService.timKiemTheoCongTySanXuat(tenCongTy,getProductsOn());
        model.addAttribute("listSearchCategoryProduct", listSearchCategoryProduct);
        return new ResponseEntity<>(listSearchCategoryProduct, HttpStatus.OK);
    }

    public List<ProductDetail> getProductsOn()
    {
        List<Product> list = productService.getAllProductsByStatus("on");

        List<ProductDetail> list_Products = new ArrayList<>();
        for (Product p: list) {
            list_Products.add(p.getProductDetail());
        }
        return list_Products;
    }
}
