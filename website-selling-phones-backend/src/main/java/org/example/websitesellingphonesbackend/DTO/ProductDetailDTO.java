package org.example.websitesellingphonesbackend.DTO;

import lombok.*;
import org.example.websitesellingphonesbackend.Enum.EColor;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class ProductDetailDTO {
    private Long id;
    private String name;
    private String category;
    private String imageUrl;
    private String description;
    private Float price;
    private String screen;
    private String os;
    private String camara;
    private String camaraFront;
    private String cpu;
    private String ram;
    private String rom;
    private String microUSB;
    private String battery;
    private String color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {return category;}

    public void setCategory(String category) {this.category = category;}

    public Float getPrice() {
        return price;
    }

    public String getCamara() {
        return camara;
    }

    public String getBattery() {
        return battery;
    }

    public String getCamaraFront() {
        return camaraFront;
    }

    public String getColor() {
        return color;
    }

    public String getCpu() {
        return cpu;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getMicroUSB() {
        return microUSB;
    }

    public String getOs() {
        return os;
    }

    public String getRam() {
        return ram;
    }

    public String getRom() {
        return rom;
    }

    public String getScreen() {
        return screen;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public void setCamara(String camara) {
        this.camara = camara;
    }

    public void setCamaraFront(String camaraFront) {
        this.camaraFront = camaraFront;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMicroUSB(String microUSB) {
        this.microUSB = microUSB;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }
}
