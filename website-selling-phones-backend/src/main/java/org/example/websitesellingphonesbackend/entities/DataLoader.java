//package org.example.websitesellingphonesbackend.entities;
//import org.example.websitesellingphonesbackend.DTO.ProductDetailDTO;
//import org.example.websitesellingphonesbackend.Enum.EColor;
//import org.example.websitesellingphonesbackend.repositories.ProductDetailRepository;
//import org.example.websitesellingphonesbackend.repositories.ProductRepository;
//import org.example.websitesellingphonesbackend.service.Impl.ProductDetailServiceImpl;
//import org.example.websitesellingphonesbackend.service.Impl.ProductServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    private final ProductDetailRepository productDetailRepository;
//
//    private final ProductDetailServiceImpl productDetailService;
//    private final ProductRepository productRepository;
//
//    @Autowired
//    public DataLoader(ProductDetailRepository productDetailRepository, ProductDetailServiceImpl productDetailService, ProductRepository productRepository) {
//        this.productDetailRepository = productDetailRepository;
//        this.productDetailService = productDetailService;
//        this.productRepository = productRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Xóa toàn bộ dữ liệu về ProductDetail trong cơ sở dữ liệu
//        productRepository.deleteAll();
//        productDetailRepository.deleteAll();
//
//        // In thông báo sau khi xóa dữ liệu
//        System.out.println("Xóa toàn bộ dữ liệu về ProductDetail thành công!");
//
//        // Insert dữ liệu mẫu
//        ProductDetailDTO productDetail1 = new ProductDetailDTO();
//        productDetail1.setName("iPhone 12");
//        productDetail1.setCategory("Apple");
//        productDetail1.setImageUrl("img/products/iphone-7-plus-32gb-hh-600x600.jpg");
//        productDetail1.setDescription("Flagship smartphone from Apple");
//        productDetail1.setPrice(999.99f);
//        productDetail1.setScreen("6.1 inches");
//        productDetail1.setOs("iOS 14");
//        productDetail1.setCamara("Dual 12 MP");
//        productDetail1.setCamaraFront("12 MP");
//        productDetail1.setCpu("A14 Bionic");
//        productDetail1.setRam("4 GB");
//        productDetail1.setRom("64 GB");
//        productDetail1.setMicroUSB("Lightning");
//        productDetail1.setBattery("2815 mAh");
//        productDetail1.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail1);
//
//
//        ProductDetailDTO productDetail2 = new ProductDetailDTO();
//        productDetail2.setName("Samsung Galaxy S21");
//        productDetail2.setCategory("Samsung");
//        productDetail2.setImageUrl("img/products/samsung-galaxy-j4-plus-pink-400x400.jpg");
//        productDetail2.setDescription("Top-tier Android smartphone from Samsung");
//        productDetail2.setPrice(899.99f);
//        productDetail2.setScreen("6.2 inches");
//        productDetail2.setOs("Android 11");
//        productDetail2.setCamara("Triple 12 MP");
//        productDetail2.setCamaraFront("10 MP");
//        productDetail2.setCpu("Exynos 2100");
//        productDetail2.setRam("8 GB");
//        productDetail2.setRom("128 GB");
//        productDetail2.setMicroUSB("USB Type-C");
//        productDetail2.setBattery("4000 mAh");
//        productDetail2.setColor(EColor.valueOf("WHITE").toString());
//
//        productDetailService.addProduct(productDetail2);
//        ProductDetailDTO productDetail3 = new ProductDetailDTO();
//        productDetail3.setName("Google Pixel 6");
//        productDetail3.setCategory("Google");
//        productDetail3.setImageUrl("img/products/xiaomi-mi-8-lite-black-1-600x600.jpg");
//        productDetail3.setDescription("Google's flagship smartphone with top-notch camera capabilities");
//        productDetail3.setPrice(799.99f);
//        productDetail3.setScreen("6.4 inches");
//        productDetail3.setOs("Android 12");
//        productDetail3.setCamara("Dual 50 MP");
//        productDetail3.setCamaraFront("12 MP");
//        productDetail3.setCpu("Google Tensor");
//        productDetail3.setRam("8 GB");
//        productDetail3.setRom("128 GB");
//        productDetail3.setMicroUSB("USB Type-C");
//        productDetail3.setBattery("4614 mAh");
//        productDetail3.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail3);
//
//        ProductDetailDTO productDetail4 = new ProductDetailDTO();
//        productDetail4.setName("OnePlus 10 Pro");
//        productDetail4.setCategory("OnePlus");
//        productDetail4.setImageUrl("img/products/oppo-f9-red-600x600.jpg");
//        productDetail4.setDescription("OnePlus flagship offering with stunning design and performance");
//        productDetail4.setPrice(899.99f);
//        productDetail4.setScreen("6.7 inches");
//        productDetail4.setOs("OxygenOS 13");
//        productDetail4.setCamara("Quad 108 MP");
//        productDetail4.setCamaraFront("32 MP");
//        productDetail4.setCpu("Snapdragon 8 Gen 2");
//        productDetail4.setRam("12 GB");
//        productDetail4.setRom("256 GB");
//        productDetail4.setMicroUSB("USB Type-C");
//        productDetail4.setBattery("5000 mAh");
//        productDetail4.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail4);
//
//        ProductDetailDTO productDetail5 = new ProductDetailDTO();
//        productDetail5.setName("Nokia 5.1 Plus");
//        productDetail5.setCategory("Nokia");
//        productDetail5.setImageUrl("img/products/nokia-51-plus-black-18thangbh-400x400.jpg");
//        productDetail5.setDescription("OnePlus flagship offering with stunning design and performance");
//        productDetail5.setPrice(899.99f);
//        productDetail5.setScreen("6.7 inches");
//        productDetail5.setOs("OxygenOS 13");
//        productDetail5.setCamara("Quad 108 MP");
//        productDetail5.setCamaraFront("32 MP");
//        productDetail5.setCpu("Snapdragon 8 Gen 2");
//        productDetail5.setRam("12 GB");
//        productDetail5.setRom("256 GB");
//        productDetail5.setMicroUSB("USB Type-C");
//        productDetail5.setBattery("5000 mAh");
//        productDetail5.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail5);
//
//        ProductDetailDTO productDetail6 = new ProductDetailDTO();
//        productDetail6.setName("Samsung Galaxy A8+ (2018)");
//        productDetail6.setCategory("Samsung");
//        productDetail6.setImageUrl("img/products/samsung-galaxy-a8-plus-2018-gold-600x600.jpg");
//        productDetail6.setDescription("OnePlus flagship offering with stunning design and performance");
//        productDetail6.setPrice(899.99f);
//        productDetail6.setScreen("6.7 inches");
//        productDetail6.setOs("OxygenOS 13");
//        productDetail6.setCamara("Quad 108 MP");
//        productDetail6.setCamaraFront("32 MP");
//        productDetail6.setCpu("Snapdragon 8 Gen 2");
//        productDetail6.setRam("12 GB");
//        productDetail6.setRom("256 GB");
//        productDetail6.setMicroUSB("USB Type-C");
//        productDetail6.setBattery("5000 mAh");
//        productDetail6.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail6);
//
//        ProductDetailDTO productDetail7 = new ProductDetailDTO();
//        productDetail7.setName("iPhone X 256GB Silver");
//        productDetail7.setCategory("Apple");
//        productDetail7.setImageUrl("img/products/iphone-x-256gb-silver-400x400.jpg");
//        productDetail7.setDescription("OnePlus flagship offering with stunning design and performance");
//        productDetail7.setPrice(899.99f);
//        productDetail7.setScreen("6.7 inches");
//        productDetail7.setOs("OxygenOS 13");
//        productDetail7.setCamara("Quad 108 MP");
//        productDetail7.setCamaraFront("32 MP");
//        productDetail7.setCpu("Snapdragon 8 Gen 2");
//        productDetail7.setRam("12 GB");
//        productDetail7.setRom("256 GB");
//        productDetail7.setMicroUSB("USB Type-C");
//        productDetail7.setBattery("5000 mAh");
//        productDetail7.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail7);
//
//        ProductDetailDTO productDetail8 = new ProductDetailDTO();
//        productDetail8.setName("Oppo A3s 32GB");
//        productDetail8.setCategory("Oppo");
//        productDetail8.setImageUrl("img/products/oppo-a3s-32gb-600x600.jpg");
//        productDetail8.setDescription("OnePlus flagship offering with stunning design and performance");
//        productDetail8.setPrice(899.99f);
//        productDetail8.setScreen("6.7 inches");
//        productDetail8.setOs("OxygenOS 13");
//        productDetail8.setCamara("Quad 108 MP");
//        productDetail8.setCamaraFront("32 MP");
//        productDetail8.setCpu("Snapdragon 8 Gen 2");
//        productDetail8.setRam("12 GB");
//        productDetail8.setRom("256 GB");
//        productDetail8.setMicroUSB("USB Type-C");
//        productDetail8.setBattery("5000 mAh");
//        productDetail8.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail8);
//
//        ProductDetailDTO productDetail9 = new ProductDetailDTO();
//        productDetail9.setName("Samsung Galaxy J8");
//        productDetail9.setCategory("Samsung");
//        productDetail9.setImageUrl("img/products/samsung-galaxy-j8-600x600-600x600.jp");
//        productDetail9.setDescription("OnePlus flagship offering with stunning design and performance");
//        productDetail9.setPrice(899.99f);
//        productDetail9.setScreen("6.7 inches");
//        productDetail9.setOs("OxygenOS 13");
//        productDetail9.setCamara("Quad 108 MP");
//        productDetail9.setCamaraFront("32 MP");
//        productDetail9.setCpu("Snapdragon 8 Gen 2");
//        productDetail9.setRam("12 GB");
//        productDetail9.setRom("256 GB");
//        productDetail9.setMicroUSB("USB Type-C");
//        productDetail9.setBattery("5000 mAh");
//        productDetail9.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail9);
//
//        ProductDetailDTO productDetail10 = new ProductDetailDTO();
//        productDetail10.setName("iPad 2018 Wifi 32GB");
//        productDetail10.setCategory("Apple");
//        productDetail10.setImageUrl("img/products/ipad-wifi-32gb-2018-thumb-600x600.jpg");
//        productDetail10.setDescription("OnePlus flagship offering with stunning design and performance");
//        productDetail10.setPrice(899.99f);
//        productDetail10.setScreen("6.7 inches");
//        productDetail10.setOs("OxygenOS 13");
//        productDetail10.setCamara("Quad 108 MP");
//        productDetail10.setCamaraFront("32 MP");
//        productDetail10.setCpu("Snapdragon 8 Gen 2");
//        productDetail10.setRam("12 GB");
//        productDetail10.setRom("256 GB");
//        productDetail10.setMicroUSB("USB Type-C");
//        productDetail10.setBattery("5000 mAh");
//        productDetail10.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail10);
//
//    }
//}
