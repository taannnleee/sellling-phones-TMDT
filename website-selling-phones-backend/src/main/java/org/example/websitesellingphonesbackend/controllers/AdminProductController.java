package org.example.websitesellingphonesbackend.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.DTO.ProductDetailDTO;
import org.example.websitesellingphonesbackend.Enum.EMessage;
import org.example.websitesellingphonesbackend.entities.Category;
import org.example.websitesellingphonesbackend.entities.Product;
import org.example.websitesellingphonesbackend.entities.ProductDetail;
import org.example.websitesellingphonesbackend.helper.HandleSaveUploadFile;
import org.example.websitesellingphonesbackend.service.AccountService;
import org.example.websitesellingphonesbackend.service.CategoryService;
import org.example.websitesellingphonesbackend.service.Impl.ProductDetailServiceImpl;
import org.example.websitesellingphonesbackend.service.ProductDetailService;
import org.example.websitesellingphonesbackend.service.ProductService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin_authentication/admin/product")
public class AdminProductController {
    private final AccountService accountService;
    @Autowired
    ProductService productService;

    @Autowired
    HandleSaveUploadFile handleSaveUploadFile;

    private final ProductDetailServiceImpl productDetailService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    HandleSaveUploadFile uploadService;



    @GetMapping("/create")
    public String handleCreateGetProduct(HttpSession session, Model model  ) {
        List<Category> categories =  categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "views/adminviews/create-product-admin";
    }

    @PostMapping("/create")
    public String addProduct(HttpSession session, @ModelAttribute ProductDetailDTO productDetailDTO, @RequestParam("imageUrl") MultipartFile file, Model model) {

        try {
            String imageProduct = handleSaveUploadFile.handleSaveUploadFile(file, "product");

            productDetailService.addProduct(productDetailDTO, imageProduct);
            session.setAttribute("addProductSuccessMessage", "Tạo sản phẩm mới thành công");
            return "redirect:/admin_authentication/admin/product";
        }catch (Exception e){
            model.addAttribute("error", "error"+ e);
            return "views/error";
        }
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id,
                                @RequestParam("name") String name,
                                @RequestParam("description") String description,
                                @RequestParam("price") double price,
                                @RequestParam("screen") String screen,
                                @RequestParam("os") String os,
                                @RequestParam("camera") String camera,
                                @RequestParam("cameraFront") String cameraFront,
                                @RequestParam("cpu") String cpu,
                                @RequestParam("ram") String ram,
                                @RequestParam("rom") String rom,
                                @RequestParam("microUSB") String microUSB,
                                @RequestParam("battery") String battery,
                                @RequestParam("category") Long categoryId,
                                @RequestParam("color") String color,
                                @RequestParam("imageProduct") MultipartFile imageProduct,
                                Model model) throws IOException {

        // Lấy thông tin category từ categoryId
        Category category = categoryService.getCategoryById(categoryId);
        String image = this.uploadService.handleSaveUploadFile(imageProduct, "product");

        category.setUrlImage(image);
        // Tạo đối tượng ProductDetail với thông tin từ request
        ProductDetail productDetail = new ProductDetail(name, null, null, description, new BigDecimal(price), screen, os, camera, cameraFront, cpu, ram, rom, microUSB, battery, color);

        // Gọi service để cập nhật sản phẩm
        productDetailService.updateProduct(id, productDetail, category, image);

        return "redirect:/admin_authentication/admin/product/update/" + id;
    }



    @GetMapping("/update/{id}")
    public String pageUpdateProduct(@PathVariable("id") Long id, Model model) {
        Product currentProduct = productService.getProductById(id);

        if (currentProduct != null) {
            List<Category> categories =  categoryService.getAllCategories();

            List<Category> otherCategories = getAllCategoriesExcept(currentProduct.getCategory());
            model.addAttribute("otherCategories", otherCategories);

            // Tạo danh sách màu còn lại
            List<String> otherColors = getAllColorsExcept(currentProduct.getProductDetail().getColor());
            model.addAttribute("otherColors", otherColors);

            model.addAttribute("categories", categories);
            model.addAttribute("currentProduct", currentProduct);
            return "views/adminviews/update-product-admin";
        }
        model.addAttribute("error", "error");
        return "views/error";
    }

    private List<Category> getAllCategoriesExcept(Category currentCategory) {
        List<Category> allCategories = categoryService.getAllCategories(); // Assume this method retrieves all categories
        List<Category> otherCategories = new ArrayList<>(allCategories);
        otherCategories.remove(currentCategory);
        return otherCategories;
    }

    // Phương thức này cần được cài đặt để lấy danh sách màu còn lại
    private List<String> getAllColorsExcept(String currentColor) {
        // Code để lấy danh sách màu từ cơ sở dữ liệu hoặc bất kỳ nguồn dữ liệu nào khác
        // Trong trường hợp này, chúng ta giả định rằng danh sách màu đã được xây dựng trước
        List<String> allColors = Arrays.asList("BLACK", "WHITE", "BLUE", "RED");
        List<String> otherColors = new ArrayList<>(allColors);
        otherColors.remove(currentColor);
        return otherColors;
    }

    @PostMapping("/delete")
    public String handleDeleteProduct(@RequestParam("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product != null) {
            // Cập nhật trạng thái của sản phẩm thành "off"
            product.setStatus("off");
            productService.save(product);
        }
        return "redirect:/admin_authentication/admin/product";
    }

    @PostMapping("/deleteAll")
    public String handleDeleteAllProduct(Model model) {
        List<Product> products = productService.getAllProduct();
        for (Product product : products) {
            product.setStatus("off");
            productService.save(product);
        }
        return "redirect:/admin_authentication/admin/product";
    }
}
