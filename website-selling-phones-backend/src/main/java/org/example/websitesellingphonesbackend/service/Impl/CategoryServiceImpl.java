// CategoryServiceImpl.java
package org.example.websitesellingphonesbackend.service.Impl;

import org.example.websitesellingphonesbackend.entities.Category;
import org.example.websitesellingphonesbackend.entities.Product;
import org.example.websitesellingphonesbackend.repositories.CategoryRepository;
import org.example.websitesellingphonesbackend.service.CategoryService;
import org.example.websitesellingphonesbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // xóa category là phải xóa product thuộc category đó

    @Override
    public void deleteAllCategory(){
        List<Category> categories =  categoryRepository.findAll();
        for (Category category: categories){
            deleteCategory(category);
        }
    }
    @Override
    public void deleteCategory(Category category){
        category.setStatus("off");
        categoryRepository.save(category);
        List<Product> products =  productService.getAllByCategory(category);
        for (Product product : products) {
            product.setStatus("off");
            productService.save(product);
        }
    }
    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = categoryRepository.findById(id).orElse(null);
        if (existingCategory != null) {
            existingCategory.setCategoryName(category.getCategoryName());
            return categoryRepository.save(existingCategory);
        }
        return null;
    }



    @Override
    public boolean isCategoryExistsAndStatusOn(String name) {
        return this.categoryRepository.existsCategoriesByCategoryNameAndStatus(name, "on");
    }
    public Category saveCategory(Category category) {
        return this.categoryRepository.save(category);
    }
    @Override
    public List<Category> getCategoriesByStatus(String string) {
        return this.categoryRepository.findCategoriesByStatus(string);
    }

    @Override
    public List<Category> findCategoriesByCategoryNameContaining(String categoryName) {
        return this.categoryRepository.findCategoriesByCategoryNameContaining(categoryName);
    }
}
