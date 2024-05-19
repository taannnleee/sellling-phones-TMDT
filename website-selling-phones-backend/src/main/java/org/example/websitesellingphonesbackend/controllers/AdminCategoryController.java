package org.example.websitesellingphonesbackend.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.entities.Category;
import org.example.websitesellingphonesbackend.helper.HandleSaveUploadFile;
import org.example.websitesellingphonesbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin_authentication/admin/category")
public class AdminCategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    HandleSaveUploadFile uploadService;
    @GetMapping("/create")
    public String adminCreateCategoryPage(HttpSession session,Model model) {
        model.addAttribute("newCategory", new Category());
        return "views/adminviews/create-category-admin";
    }
    @PostMapping("/create")
    public String handleCreateCategory(HttpSession session, @ModelAttribute("newCategory") Category category, Model model,
                                       @RequestParam("imageCategory") MultipartFile file) {

        try {
            if (categoryService.isCategoryExistsAndStatusOn(category.getCategoryName())) {
                model.addAttribute("error", "Tên danh mục đã tồn tại !");
                return "views/adminviews/create-category-admin";
            }
            String image = this.uploadService.handleSaveUploadFile(file, "category");
            category.setUrlImage(image);
            category.setStatus("on");
            categoryService.saveCategory(category);
            session.setAttribute("addCategorySuccessMessage", "Tạo danh mục mới thành công");
            return "redirect:/admin_authentication/admin/category";
        }catch (Exception e){
            model.addAttribute("error", "error"+ e);
            return "views/error";
        }
    }

}
