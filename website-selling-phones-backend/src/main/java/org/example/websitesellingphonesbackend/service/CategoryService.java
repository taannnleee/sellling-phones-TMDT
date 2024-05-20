package org.example.websitesellingphonesbackend.service;

import org.example.websitesellingphonesbackend.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category createCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Category category);
    boolean isCategoryExistsAndStatusOn(String name);
    Category saveCategory(Category category);
    void deleteAllCategory();
    List<Category> getCategoriesByStatus(String string);
    List<Category> findCategoriesByCategoryNameContaining(String categoryName);
}
