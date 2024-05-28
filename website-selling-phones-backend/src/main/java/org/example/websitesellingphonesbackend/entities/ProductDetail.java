package org.example.websitesellingphonesbackend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.websitesellingphonesbackend.Enum.EColor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.math.BigDecimal;

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
    private BigDecimal price;

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


    public ProductDetail(String name, String category, String imageUrl, String description, BigDecimal price, String screen, String os, String camera, String cameraFront, String cpu, String ram, String rom, String microUSB, String battery, String color) {
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
        this.screen = screen;
        this.os = os;
        this.camera = camera;
        this.cameraFront = cameraFront;
        this.cpu = cpu;
        this.ram = ram;
        this.rom = rom;
        this.microUSB = microUSB;
        this.battery = battery;
        this.color = color;
    }
}
