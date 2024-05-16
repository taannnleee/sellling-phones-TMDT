package org.example.websitesellingphonesbackend.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.entities.Cart;
import org.example.websitesellingphonesbackend.entities.LineSetPointProduct;
import org.example.websitesellingphonesbackend.entities.Product;
import org.example.websitesellingphonesbackend.service.CartService;
import org.example.websitesellingphonesbackend.service.LineSetPointProductService;
import org.example.websitesellingphonesbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/setpointproduct")
public class LineSetPointProductController {
    @Autowired
    LineSetPointProductService lineSetPointProductService;
    @Autowired
    ProductService  productService;
    @Autowired
    CartService cartService ;

    @GetMapping("/add-to-setpointproduct/{product_id}")
    public String getViewSetPointProduct(HttpSession session, @PathVariable String product_id, Model model) {
        Cart cart = (Cart)session.getAttribute("cart");
        if (cart==null){
            return "views/login";
        }else {
            try {
                Product product=  productService.getProductById(Long.valueOf(product_id));
                lineSetPointProductService.insertLineSetPointProduct(cart, product);
                return "redirect:/setpointproduct/load-setpointproduct";
            } catch (Exception e) {
                model.addAttribute("error", "Lỗi tải trang: " + e.getMessage());
                return "views/error";
            }
        }
    }

    @GetMapping("/load-setpointproduct")
    public String loadSetPointProduct(HttpSession session, Model model) {
        Cart cart = (Cart)session.getAttribute("cart");
        if (cart==null){
            return "views/login";
        }else {
            try {
                lineSetPointProductService.updateTotalPrice(cart);
                List<LineSetPointProduct> lineSetPointProductList =  lineSetPointProductService.listLineSetPointProductOfCart(cart);
                model.addAttribute("lineSetPointProductList", lineSetPointProductList);
                model.addAttribute("cart", cart);
                return "views/SetPointProduct";
            } catch (Exception e) {
                model.addAttribute("error", "Lỗi tải trang: " + e.getMessage());
                return "views/error";
            }
        }
    }

    @PostMapping("/load-setpointproduct/increaseQuantity")
    public String increaseQuantity(@RequestParam("productId") Long productId, HttpSession session) {
        Cart cart = (Cart)session.getAttribute("cart");
        Product product=  productService.getProductById(Long.valueOf(productId));
        lineSetPointProductService.increaseQuantity(cart,product);
        return "redirect:/setpointproduct/load-setpointproduct";
    }

    @PostMapping("/load-setpointproduct/decreaseQuantity")
    public String decreaseQuantity(@RequestParam("productId") Long productId, HttpSession session) {
        Cart cart = (Cart)session.getAttribute("cart");
        Product product=  productService.getProductById(Long.valueOf(productId));
        lineSetPointProductService.decreaseQuantity(cart,product);
        return "redirect:/setpointproduct/load-setpointproduct";
    }
    @GetMapping("/load-setpointproduct/removeItem/{lineSetPointProductId}")
    public String remocveItemFromSetPointProduct(@PathVariable Long lineSetPointProductId) {
        lineSetPointProductService.deleteLineSetPointProductById(lineSetPointProductId);
        return "redirect:/setpointproduct/load-setpointproduct";
    }

    @GetMapping("/load-setpointproduct/removeAllItem/{cartId}")
    public String remocveAllItemFromSetPointProduct(@PathVariable Long cartId) {
        Cart cart = cartService.getCartByCartId(cartId);
        lineSetPointProductService.deleteAllLineSetPointProductByCart(cart);
        return "redirect:/setpointproduct/load-setpointproduct";
    }
}
