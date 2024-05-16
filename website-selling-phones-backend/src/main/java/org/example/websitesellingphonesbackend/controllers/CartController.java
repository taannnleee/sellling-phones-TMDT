package org.example.websitesellingphonesbackend.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.entities.Cart;
import org.example.websitesellingphonesbackend.entities.LineItem;
import org.example.websitesellingphonesbackend.entities.Product;
import org.example.websitesellingphonesbackend.service.CartService;
import org.example.websitesellingphonesbackend.service.LineItemService;
import org.example.websitesellingphonesbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    @Autowired
    LineItemService lineItemService;
    @Autowired
    ProductService  productService;
    @Autowired
    CartService cartService ;
    @GetMapping("/add-to-cart/{product_id}")
    public String getViewCart(HttpSession session, @PathVariable String product_id, Model model) {
        Cart cart = (Cart)session.getAttribute("cart");
        if (cart==null){
            return "views/login";
        }else {
            try {
                Product product=  productService.getProductById(Long.valueOf(product_id));
                lineItemService.insertLineItem(cart, product);
                return "redirect:/cart/load-cart";
            } catch (Exception e) {
                model.addAttribute("error", "Lỗi tải trang: " + e.getMessage());
                return "views/error";
            }
        }
    }

    @GetMapping("/load-cart")
    public String loadCart(HttpSession session, Model model) {
        Cart cart = (Cart)session.getAttribute("cart");
        if (cart==null){
            return "views/login";
        }else {
            try {

                lineItemService.updateTotalPrice(cart);
                List<LineItem> lineItemList =  lineItemService.listLineItemOfCart(cart);
                model.addAttribute("lineItemList", lineItemList);
                model.addAttribute("cart", cart);
               return "views/giohang";
            } catch (Exception e) {
                model.addAttribute("error", "Lỗi tải trang: " + e.getMessage());
                return "views/error";
            }
        }
    }

    @PostMapping("/load-cart/increaseQuantity")
    public String increaseQuantity(@RequestParam("productId") Long productId, HttpSession session) {
        Cart cart = (Cart)session.getAttribute("cart");
        Product product=  productService.getProductById(Long.valueOf(productId));
        lineItemService.increaseQuantity(cart,product);
        return "redirect:/cart/load-cart";
    }

    @PostMapping("/load-cart/decreaseQuantity")
    public String decreaseQuantity(@RequestParam("productId") Long productId, HttpSession session) {
        Cart cart = (Cart)session.getAttribute("cart");
        Product product=  productService.getProductById(Long.valueOf(productId));
        lineItemService.decreaseQuantity(cart,product);
        return "redirect:/cart/load-cart";
    }
    @GetMapping("/load-cart/removeItem/{lineItemId}")
    public String remocveItemFromCart(@PathVariable Long lineItemId) {
        lineItemService.deleteLineItemById(lineItemId);
        return "redirect:/cart/load-cart";
    }

    @GetMapping("/load-cart/removeAllItem/{cartId}")
    public String remocveAllItemFromCart(@PathVariable Long cartId) {
        Cart cart = cartService.getCartByCartId(cartId);
        lineItemService.deleteAllLineItemByCart(cart);
        return "redirect:/cart/load-cart";
    }
}
