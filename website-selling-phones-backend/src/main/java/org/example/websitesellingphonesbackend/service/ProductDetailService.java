package org.example.websitesellingphonesbackend.service;

import org.example.websitesellingphonesbackend.DTO.ProductDetailDTO;
import org.example.websitesellingphonesbackend.entities.Category;
import org.example.websitesellingphonesbackend.entities.ProductDetail;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDetailService {

    List<ProductDetail> getAllProductDetails();
    ProductDetail getProductDetail(Long id);
    void deleteProduct(Long id);
    void addProduct(ProductDetailDTO productDetailDTO, String imageProduct);
    void updateProduct(Long id, ProductDetail productDetail, Category category, String imageProduct);

    List<ProductDetail> timKiemTheoTen(String ten,List<ProductDetail> list);
    List<ProductDetail> timKiemTheoGiaTien(BigDecimal giaMin, BigDecimal giaMax, List<ProductDetail> list);
    List<ProductDetail> timKiemTheoCongTySanXuat(String tenCongTy,List<ProductDetail> list);
    List<ProductDetail> getProductsContainingName(String productName);
}