package org.example.websitesellingphonesbackend.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.entities.Cart;
import org.example.websitesellingphonesbackend.entities.Evaluate;
import org.example.websitesellingphonesbackend.entities.Product;
import org.example.websitesellingphonesbackend.entities.ProductDetail;
import org.example.websitesellingphonesbackend.service.EvaluateService;
import org.example.websitesellingphonesbackend.service.ProductDetailService;
import org.example.websitesellingphonesbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product-detail")
public class CustomerProductDetail {
    @Autowired
    ProductDetailService productDetailService;
    @Autowired
    ProductService productService;

    @Autowired
    EvaluateService  evaluateService;

//    @GetMapping()
//    public String getProductDetail1(HttpSession session, Model model,@RequestParam("productName") String productName) {
//        try {
//            System.out.println("hehe"+ productName);
//            return "views/error";
//        } catch (Exception e) {
//            model.addAttribute("error", "Lỗi tải trang: " + e.getMessage());
//            return "views/error";
//        }
//
//    }
    @GetMapping("/{productId}")
    public String getProductDetail(HttpSession session,@PathVariable String productId, Model model) {
        try {
            Product products =  productService.getProductByProductDetailId(Long.valueOf(productId));
            List<Evaluate> evaluateList =  evaluateService.findEvaluateByProduct(products);
            model.addAttribute("products", products);
            model.addAttribute("evaluateList", evaluateList);
            return "views/chitietsanpham";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi tải trang: " + e.getMessage());
            return "views/error";
        }

    }
    @PostMapping("/compare_products/add")
    public String addProductToCompare(@RequestParam("productId") Long productId, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        Product product = productService.getProductByProductDetailId(productId);
        if (product != null) {
            List<Product> listProduct = (List<Product>) session.getAttribute("compareList");
            if (listProduct == null) {
                listProduct = new ArrayList<>();
            } else {
                for (Product p : listProduct) {
                    if (p.getProductID().equals(productId)) {
                        session.setAttribute("compareList", listProduct);
                        model.addAttribute("listProduct", listProduct);
                        return "views/compareProduct";
                    }
                }
            }
            listProduct.add(product);
            session.setAttribute("compareList", listProduct);
            model.addAttribute("listProduct", listProduct);
        }
        return "views/compareProduct";
    }
    @PostMapping("/compare_products/delete")
    public String deleteProductToCompare(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        session.removeAttribute("compareList");
        model.addAttribute("listProduct", new ArrayList<>());
        return "views/compareProduct";
    }
}
