package org.example.websitesellingphonesbackend.service.Impl;

import org.example.websitesellingphonesbackend.DTO.ProductDetailDTO;
import org.example.websitesellingphonesbackend.Enum.EColor;
import org.example.websitesellingphonesbackend.entities.Category;
import org.example.websitesellingphonesbackend.entities.Product;
import org.example.websitesellingphonesbackend.entities.ProductDetail;
import org.example.websitesellingphonesbackend.repositories.CategoryRepository;
import org.example.websitesellingphonesbackend.repositories.ProductDetailRepository;
import org.example.websitesellingphonesbackend.repositories.ProductRepository;
import org.example.websitesellingphonesbackend.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    private final ProductDetailRepository productDetailRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    public ProductDetailServiceImpl(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }

    public List<ProductDetail> getAllProductDetails() {
        return productDetailRepository.findAll();
    }
    public void deleteProduct(Long id) {
        // Tìm Product tương ứng với ProductDetail
        Product product = productRepository.findByProductDetail_ProductDetailId(id);
        // Xóa Product
        productRepository.delete(product);
        // Xóa ProductDetail
        productDetailRepository.deleteById(id);
    }

    public void addProduct(ProductDetailDTO productDetailDTO, String imageProduct) {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setName(productDetailDTO.getName());
        productDetail.setCategory(productDetailDTO.getCategory());
        productDetail.setImageUrl(imageProduct);
        productDetail.setDescription(productDetailDTO.getDescription());
        productDetail.setPrice(productDetailDTO.getPrice());
        productDetail.setScreen(productDetailDTO.getScreen());
        productDetail.setOs(productDetailDTO.getOs());
        productDetail.setCamera(productDetailDTO.getCamara());
        productDetail.setCameraFront(productDetailDTO.getCamaraFront());
        productDetail.setCpu(productDetailDTO.getCpu());
        productDetail.setRam(productDetailDTO.getRam());
        productDetail.setRom(productDetailDTO.getRom());
        productDetail.setMicroUSB(productDetailDTO.getMicroUSB());
        productDetail.setBattery(productDetailDTO.getBattery());
        productDetail.setColor(productDetailDTO.getColor());


        // Kiểm tra xem category đã tồn tại chưa
        Category category = categoryRepository.findByCategoryName(productDetail.getCategory());
        if (category == null) {
            // Nếu chưa tồn tại, tạo mới
            category = new Category();
            category.setCategoryName(productDetail.getCategory());
            categoryRepository.save(category);
        }
        // Tạo mới product
        Product product = new Product();
        product.setProductDetail(productDetail);
        product.setStatus("on");
        product.setCategory(category);

        // Lưu productDetail và product vào database
        productRepository.save(product);
        productDetailRepository.save(productDetail);
    }
    public ProductDetail getProductDetail(Long id) {
        return productDetailRepository.findById(id).orElse(null);
    }
    public void updateProduct(Long id, ProductDetailDTO productDetailDTO) {
        ProductDetail existingProductDetail = productDetailRepository.findById(id).orElse(null);

        if (existingProductDetail != null) {
            existingProductDetail.setProductDetailId(productDetailDTO.getId());
            existingProductDetail.setName(productDetailDTO.getName());
            existingProductDetail.setCategory(productDetailDTO.getCategory());
//            existingProductDetail.setImageUrl(productDetailDTO.getImageUrl());
            existingProductDetail.setDescription(productDetailDTO.getDescription());
            existingProductDetail.setPrice(productDetailDTO.getPrice());
            existingProductDetail.setScreen(productDetailDTO.getScreen());
            existingProductDetail.setOs(productDetailDTO.getOs());
            existingProductDetail.setCamera(productDetailDTO.getCamara());
            existingProductDetail.setCameraFront(productDetailDTO.getCamaraFront());
            existingProductDetail.setCpu(productDetailDTO.getCpu());
            existingProductDetail.setRam(productDetailDTO.getRam());
            existingProductDetail.setRom(productDetailDTO.getRom());
            existingProductDetail.setMicroUSB(productDetailDTO.getMicroUSB());
            existingProductDetail.setBattery(productDetailDTO.getBattery());
//            existingProductDetail.setColor(EColor.valueOf(productDetailDTO.getColor()));

            productDetailRepository.save(existingProductDetail);
        }
    }
    public List<ProductDetail> timKiemTheoTen( String ten) {
        List<ProductDetail> list = getAllProductDetails();
        List<ProductDetail> result = new ArrayList<>();
        String[] keywords = ten.split(" ");

        for (ProductDetail productDetail : list) {
            boolean correct = true;
            for (String keyword : keywords) {
                if (!productDetail.getName().toUpperCase().contains(keyword.toUpperCase())) {
                    correct = false;
                    break;
                }
            }
            if (correct) {
                result.add(productDetail);
            }
        }
        return result;
    }
    public List<ProductDetail> timKiemTheoGiaTien(double giaMin, double giaMax) {
        List<ProductDetail> list = getAllProductDetails();
        List<ProductDetail> result = new ArrayList<>();

        for (ProductDetail productDetail : list) {
            double gia = productDetail.getPrice();
            if (gia >= giaMin && gia <= giaMax) {
                result.add(productDetail);
            }
        }

        return result;
    }
    public List<ProductDetail> timKiemTheoCongTySanXuat( String tenCongTy) {
        List<ProductDetail> list = getAllProductDetails();
        List<ProductDetail> result = new ArrayList<>();

        for (ProductDetail productDetail : list) {
            if (productDetail.getCategory().toUpperCase().contains(tenCongTy.toUpperCase())) {
                result.add(productDetail);
            }
        }
        return result;
    }
}