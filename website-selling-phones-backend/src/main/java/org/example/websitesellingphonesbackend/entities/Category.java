package org.example.websitesellingphonesbackend.entities;



import jakarta.persistence.*;
import lombok.*;
import org.example.websitesellingphonesbackend.Enum.EColor;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Category")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "url_image")
    private String urlImage;

    @Column(name = "status")
    private String status;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Product> listproducts;


}

