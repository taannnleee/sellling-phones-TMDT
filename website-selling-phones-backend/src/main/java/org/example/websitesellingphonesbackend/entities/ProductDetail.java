package org.example.websitesellingphonesbackend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.websitesellingphonesbackend.Enum.EColor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;

@Entity
@Table(name = "ProductDetail")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class ProductDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_detail_id")
    private Long productDetailId;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "decription")
    private String description;

    @Column(name = "price")
    private Float price;

    @Column(name = "screen")
    private String screen;

    @Column(name = "os")
    private String os;

    @Column(name = "camera")
    private String camera;

    @Column(name = "cameraFront")
    private String cameraFront;

    @Column(name = "cpu")
    private String cpu;

    @Column(name = "ram")
    private String ram;

    @Column(name = "rom")
    private String rom;

    @Column(name = "microUSB")
    private String microUSB;

    @Column(name = "battery")
    private String battery;

    @Column(name = "color")
    private String color;


    @OneToOne(mappedBy = "productDetail", fetch = FetchType.EAGER)
    @JsonBackReference
    private Product product;

}
