package org.example.websitesellingphonesbackend.repositories;
import org.example.websitesellingphonesbackend.entities.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.websitesellingphonesbackend.entities.Category;
@Repository

public interface GLineItemReponsitory extends JpaRepository<LineItem, Long> {
}
