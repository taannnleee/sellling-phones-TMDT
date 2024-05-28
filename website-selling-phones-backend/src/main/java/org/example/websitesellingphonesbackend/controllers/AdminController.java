package org.example.websitesellingphonesbackend.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.entities.*;
import org.example.websitesellingphonesbackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin_authentication/admin")
public class AdminController {
    private final AccountService accountService;
    @Autowired
    ProductService productService;

    @Autowired
    ProductDetailService productDetailService;
    @Autowired
    CustomerService customerService;
    @Autowired
    CategoryService categoryService;

    @Autowired
    OrderService orderService;
    @GetMapping("/product")
    public String adminProduct(HttpSession session,Model model) {
        try {
            String addProductSuccessMessage = (String) session.getAttribute("addProductSuccessMessage"); // Lấy thông báo từ session
            if (addProductSuccessMessage != null) {
                model.addAttribute("addProductSuccessMessage", true); // Thêm thông báo vào model
                session.removeAttribute("addProductSuccessMessage"); // Xóa thông báo từ session sau khi đã sử dụng
            }
            List<Product> products =  productService.getAllProductsByStatus("on");
            List<Category> categories =  categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("products", products);
            return "views/adminviews/product-admin";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "views/error";
        }
    }

    @PostMapping("/product")
    public String adminProductPage(@RequestParam(value = "categoryId", required = false) Long categoryId,
                                   @RequestParam(value = "productName", required = false) String productName,
                                   Model model) {
        List<Product> products =  new ArrayList<>();
        if (productName != null && !productName.isEmpty()) {
            // Nếu tìm theo tên sản phẩm
            List<ProductDetail> productDetails =  productDetailService.getProductsContainingName(productName);
            for(ProductDetail productDetail : productDetails){
                Product product =  productService.getProductByProductDetailId(productDetail.getProductDetailId());
                products.add(product);
            }
        } else if (categoryId != null) {
            // Nếu tìm theo danh mục
            products = productService.getProductsByCategoryId(categoryId);
        } else {
            // Không có thông tin tìm kiếm, hiển thị tất cả sản phẩm
            products = productService.getAllProduct();
        }
        List<Category> categories = categoryService.getCategoriesByStatus("on");
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "views/adminviews/product-admin";
    }

    @GetMapping("/customer")
    public String adminCustomer(Model model) {
        try {
            List<Customer> customers =  customerService.getAllCustomers();
            model.addAttribute("customers", customers);
            return "views/adminviews/customer-admin";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "views/error";
        }
    }
    @GetMapping("/order")
    public String order(Model model) {
        try {
            model.addAttribute("orders", orderService.getAllOrder());
            return "views/adminviews/order-admin";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "views/error";
        }
    }
    @GetMapping("/order/{id}")
    public String openUdateOrderStatusPage(@PathVariable int id, Model model) {
        orderService.updateStatusOrder((long) id);
        return "redirect:/admin_authentication/admin/order";
    }
    @GetMapping("/category")
    public String category(HttpSession session, Model model) {
        String addProductSuccessMessage = (String) session.getAttribute("addCategorySuccessMessage"); // Lấy thông báo từ session
        if (addProductSuccessMessage != null) {
            model.addAttribute("addCategorySuccessMessage", true); // Thêm thông báo vào model
            session.removeAttribute("addCategorySuccessMessage"); // Xóa thông báo từ session sau khi đã sử dụng
        }

        List<Category> categories = categoryService.getAllCategories();
        Map<Long, Integer> productCountMap = new HashMap<>();
        for (Category category : categories) {
            int productCount = productService.countProductByCategoryAndStatus(category, "on");
            productCountMap.put(category.getCategoryId(), productCount);
        }
        model.addAttribute("categories", categories);
        model.addAttribute("productCountMap", productCountMap);
        return "views/adminviews/category-admin";
    }

    @PostMapping("/category")
    public String adminCategoryPage(
            @RequestParam(value = "categoryName", required = false) String categoryName,
            Model model) {
        List<Category> categories;
        if (categoryName == null || categoryName.isEmpty()) {
            categories = categoryService.getAllCategories();
        } else
            categories = this.categoryService.findCategoriesByCategoryNameContaining(categoryName);
        model.addAttribute("categories", categories);
        Map<Long, Integer> productCountMap = new HashMap<>();
        for (Category category : categories) {
            int productCount = productService.countProductByCategoryCategoryId(category.getCategoryId());
            productCountMap.put(category.getCategoryId(), productCount);
        }
        model.addAttribute("categories", categories);
        model.addAttribute("productCountMap", productCountMap);
        return "views/adminviews/category-admin";
    }

    @GetMapping("/statistics")
    public String statistics(Model model) {
        try {
            int countCustomer = (int) customerService.countCustomer();
            int countProduct = (int) productService.countProduct();
            int countOrder = (int) orderService.countOrder();
            List<Customer> customersRecently = customerService.getAllCustomers();
            model.addAttribute("countOrder",countOrder );
            model.addAttribute("countProduct",countProduct );
            model.addAttribute("countCustomer",countCustomer );
            model.addAttribute("customersRecently",customersRecently );

            List<Integer> monthlyRevenue = orderService.getMonthlyRevenue(2024); // Giả sử phương thức này trả về tổng doanh thu hàng tháng
            model.addAttribute("monthlyRevenue", monthlyRevenue);

            model.addAttribute("orders", orderService.getAllOrder());
            return "view/statistics";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "views/error";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session,Model model) {
        try {
            session.setAttribute("admin", false);
            return "redirect:customer_authentication/login";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "views/error";
        }
    }


}
