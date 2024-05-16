package org.example.websitesellingphonesbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.entities.Evaluate;
import org.example.websitesellingphonesbackend.entities.Product;
import org.example.websitesellingphonesbackend.service.EvaluateService;
import org.example.websitesellingphonesbackend.service.Impl.EvaluateServiceImpl;
import org.example.websitesellingphonesbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/evaluate")
public class EvaluateController {
    @Autowired
    EvaluateService evaluateService;

    @Autowired
    ProductService productService;
    @PostMapping("/product/review")
    public String submitProductReview(
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("review") String review,
            @RequestParam("rating") int rating,
            @RequestParam("productId") int productId) {


        Product product_review = productService.getProductById((long) productId);
        Evaluate newEvaluate = new Evaluate();
        newEvaluate.setAuthorName(name);
        newEvaluate.setAuthorPhone(phone);
        newEvaluate.setReview(review);
        newEvaluate.setRating(rating);
        newEvaluate.setProduct(product_review);
        evaluateService.insetEvaluate(newEvaluate);
        return "redirect:/product-detail/" + productId;
    }

}
