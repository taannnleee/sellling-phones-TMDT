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


    @GetMapping("/create")
    public String handleCreateGetProduct(HttpSession session, Model model  ) {
        String addProductSuccessMessage = (String) session.getAttribute("addProductSuccessMessage"); // Lấy thông báo từ session
        if (addProductSuccessMessage != null) {
            model.addAttribute("addProductSuccessMessage", true); // Thêm thông báo vào model
            session.removeAttribute("addProductSuccessMessage"); // Xóa thông báo từ session sau khi đã sử dụng
        }
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
            return "redirect:/admin_authentication/admin/product/create";
        }catch (Exception e){
            model.addAttribute("error", "error"+ e);
            return "views/error";
        }
    }

    @GetMapping("/update/{id}")
    public String pageUpdateProduct(@PathVariable("id") Long id, Model model) {
        Product currentProduct = productService.getProductById(id);

        if (currentProduct != null) {
            model.addAttribute("newProduct", currentProduct);
            return "views/adminviews/update-product-admin";
        }
        model.addAttribute("error", "error");
        return "views/error";
    }

}
