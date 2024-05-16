package org.example.websitesellingphonesbackend.service;

import org.example.websitesellingphonesbackend.DTO.ProductDetailDTO;
import org.example.websitesellingphonesbackend.entities.ProductDetail;

import java.util.List;

public interface ProductDetailService {
    List<ProductDetail> getAllProductDetails();
    ProductDetail getProductDetail(Long id);
    void deleteProduct(Long id);
    void addProduct(ProductDetailDTO productDetailDTO);
    void updateProduct(Long id, ProductDetailDTO productDetailDTO);

    List<ProductDetail> timKiemTheoTen(String ten);
    List<ProductDetail> timKiemTheoGiaTien( double giaMin, double giaMax);
    List<ProductDetail> timKiemTheoCongTySanXuat(String tenCongTy);
}