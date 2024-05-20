package org.example.websitesellingphonesbackend.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.entities.Category;
import org.example.websitesellingphonesbackend.entities.Product;
import org.example.websitesellingphonesbackend.helper.HandleSaveUploadFile;
import org.example.websitesellingphonesbackend.service.CategoryService;
import org.example.websitesellingphonesbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin_authentication/admin/category")
public class AdminCategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    HandleSaveUploadFile uploadService;

    @Autowired
    ProductService productService;
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
    @PostMapping("/deleteAll")
    public String handleDeleteAllCategory(Model model) {

        try {
            // set statusCategory về off
            categoryService.deleteAllCategory();
            return "redirect:/admin_authentication/admin/category";

        } catch (Exception e) {
            model.addAttribute("error", "error" + e);
            return "views/error";
        }
    }

    @PostMapping("/delete")
    public String handleDeleteCategory(Model model,@RequestParam("id") Long id) {

        try {
            // set statusCategory về off
            Category category  =  categoryService.getCategoryById(id);
            categoryService.deleteCategory(category);
            return "redirect:/admin_authentication/admin/category";

        } catch (Exception e) {
            model.addAttribute("error", "error" + e);
            return "views/error";
        }
    }

    @GetMapping("/update/{id}")
    public String pageUpdateCategory(@PathVariable("id") Long id, Model model) {
        Category currentCategory = categoryService.getCategoryById(id);
        model.addAttribute("newCategory", currentCategory);
        return "views/adminviews/update-category-admin";
    }
    @PostMapping("/update")
    public String handleUpdateCategory(@ModelAttribute("newCategory") Category category, Model model,
                                       @RequestParam("imageProduct") MultipartFile file) {
        Category currentCategory = categoryService.getCategoryById(category.getCategoryId());
        if (!currentCategory.getCategoryName().equals(category.getCategoryName())) {
            if (!currentCategory.getCategoryName().equals(category.getCategoryName())
                    && categoryService.isCategoryExistsAndStatusOn(category.getCategoryName())) {
                model.addAttribute("error", "Tên danh mục đã tồn tại !");
                model.addAttribute("newCategory", category);
                return "views/adminviews/update-category-admin";
            }
        }

        currentCategory.setCategoryName(category.getCategoryName());
        String imageCategory = this.uploadService.handleSaveUploadFile(file, "category");
        if (imageCategory != "")
            currentCategory.setUrlImage(imageCategory);
        categoryService.saveCategory(currentCategory);
        return "redirect:/admin_authentication/admin/category";
    }
}
