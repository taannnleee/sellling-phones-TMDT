package org.example.websitesellingphonesbackend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.websitesellingphonesbackend.entities.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryName(String category);
    boolean existsCategoriesByCategoryNameAndStatus(String name, String string);
    List<Category> findCategoriesByStatus(String string);

    List<Category> findCategoriesByCategoryNameContaining(String categoryName);

}