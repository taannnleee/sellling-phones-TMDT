//
//package org.example.websitesellingphonesbackend.entities;
//
//import org.example.websitesellingphonesbackend.DTO.ProductDetailDTO;
//import org.example.websitesellingphonesbackend.Enum.EColor;
//import org.example.websitesellingphonesbackend.repositories.CategoryRepository;
//import org.example.websitesellingphonesbackend.repositories.ProductDetailRepository;
//import org.example.websitesellingphonesbackend.repositories.ProductRepository;
//import org.example.websitesellingphonesbackend.service.Impl.ProductDetailServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    private final ProductDetailRepository productDetailRepository;
//    private final CategoryRepository categoryRepository;
//    private final ProductDetailServiceImpl productDetailService;
//    private final ProductRepository productRepository;
//
//    @Autowired
//    public DataLoader(ProductDetailRepository productDetailRepository,
//                      ProductDetailServiceImpl productDetailService,
//                      ProductRepository productRepository,
//                      CategoryRepository categoryRepository) {
//        this.productDetailRepository = productDetailRepository;
//        this.productDetailService = productDetailService;
//        this.productRepository = productRepository;
//        this.categoryRepository = categoryRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Define Categories
//        Category category1 = Category.builder()
//                .urlImage("Apple.jpg")
//                .categoryName("Apple")
//                .status("on")
//                .build();
//        categoryRepository.save(category1);
//
//        Category category2 = Category.builder()
//                .urlImage("Samsung.jpg")
//                .categoryName("Samsung")
//                .status("on")
//                .build();
//        categoryRepository.save(category2);
//        Category category3 = Category.builder()
//                .urlImage("google.png")
//                .categoryName("Google")
//                .status("on")
//                .build();
//        categoryRepository.save(category3);
//
//        Category category4 = Category.builder()
//                .urlImage("OnePlus.png")
//                .categoryName("OnePlus")
//                .status("on")
//                .build();
//        categoryRepository.save(category4);
//
//        Category category5 = Category.builder()
//                .urlImage("Huawei.jpg")
//                .categoryName("Huawei")
//                .status("on")
//                .build();
//        categoryRepository.save(category5);
//
//        Category category6 = Category.builder()
//                .urlImage("Xiaomi.png")
//                .categoryName("Xiaomi")
//                .status("on")
//                .build();
//        categoryRepository.save(category6);
//
//        Category category7 = Category.builder()
//                .urlImage("Oppo.jpg")
//                .categoryName("Oppo")
//                .status("on")
//                .build();
//        categoryRepository.save(category7);
//
//        Category category8 = Category.builder()
//                .urlImage("Sony.jpg")
//                .categoryName("Sony")
//                .status("on")
//                .build();
//        categoryRepository.save(category8);
//
//        Category category9 = Category.builder()
//                .urlImage("LG.jpg")
//                .categoryName("LG")
//                .status("on")
//                .build();
//        categoryRepository.save(category9);
//
//
//        String ImageUrl = "";
//        // Insert dữ liệu mẫu
//        ProductDetailDTO productDetail1 = new ProductDetailDTO();
//        productDetail1.setName("iPhone 12");
//        productDetail1.setCategory("Apple");
//        ImageUrl = "iphone-7-plus-32gb-hh-600x600.jpg";
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
//        productDetailService.addProduct(productDetail1, ImageUrl);
//
//
//        ProductDetailDTO productDetail2 = new ProductDetailDTO();
//        productDetail2.setName("Samsung Galaxy S21");
//        productDetail2.setCategory("Samsung");
//        ImageUrl = "samsung-galaxy-j4-plus-pink-400x400.jpg";
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
//        productDetailService.addProduct(productDetail2, ImageUrl);
//
//        ProductDetailDTO productDetail3 = new ProductDetailDTO();
//        productDetail3.setName("Google Pixel 6");
//        productDetail3.setCategory("Google");
//        ImageUrl = "xiaomi-mi-8-lite-black-1-600x600.jpg";
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
//        productDetailService.addProduct(productDetail3, ImageUrl);
//
//        ProductDetailDTO productDetail4 = new ProductDetailDTO();
//        productDetail4.setName("OnePlus 10 Pro");
//        productDetail4.setCategory("OnePlus");
//        ImageUrl = "oppo-f9-red-600x600.jpg";
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
//        productDetailService.addProduct(productDetail4, ImageUrl);
//
//        ProductDetailDTO productDetail5 = new ProductDetailDTO();
//        productDetail5.setName("Nokia 5.1 Plus");
//        productDetail5.setCategory("Nokia");
//        ImageUrl="nokia5.jfif";
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
//        productDetailService.addProduct(productDetail5, ImageUrl);
//
//        ProductDetailDTO productDetail6 = new ProductDetailDTO();
//        productDetail6.setName("Samsung Galaxy A8+ (2018)");
//        productDetail6.setCategory("Samsung");
//        ImageUrl="samsung-galaxy-a8-plus-2018-gold-600x600.jpg";
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
//        productDetailService.addProduct(productDetail6, ImageUrl);
//
//        ProductDetailDTO productDetail7 = new ProductDetailDTO();
//        productDetail7.setName("iPhone X 256GB Silver");
//        productDetail7.setCategory("Apple");
//        ImageUrl="iphone-x-256gb-silver-400x400.jpg";
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
//        productDetailService.addProduct(productDetail7, ImageUrl);
//
//        ProductDetailDTO productDetail8 = new ProductDetailDTO();
//        productDetail8.setName("Oppo A3s 32GB");
//        productDetail8.setCategory("Oppo");
//        ImageUrl="oppo-a3s-32gb-600x600.jpg";
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
//        productDetailService.addProduct(productDetail8, ImageUrl);
//
//        ProductDetailDTO productDetail9 = new ProductDetailDTO();
//        productDetail9.setName("Samsung Galaxy J8");
//        productDetail9.setCategory("Samsung");
//        ImageUrl="samsung-galaxy-j8-600x600-600x600.jpg";
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
//        productDetailService.addProduct(productDetail9, ImageUrl);
//
//        ProductDetailDTO productDetail10 = new ProductDetailDTO();
//        productDetail10.setName("iPad 2018 Wifi 32GB");
//        productDetail10.setCategory("Apple");
//        ImageUrl = "ipad-wifi-32gb-2018-thumb-600x600.jpg";
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
//        productDetailService.addProduct(productDetail10, ImageUrl);
//
//        ProductDetailDTO productDetail11 = new ProductDetailDTO();
//        productDetail11.setName("Xiaomi Mi 11");
//        productDetail11.setCategory("Xiaomi");
//        ImageUrl = "xiaomi-mi-11-600x600.jfif";
//        productDetail11.setDescription("Flagship from Xiaomi with exceptional performance and camera");
//        productDetail11.setPrice(749.99f);
//        productDetail11.setScreen("6.81 inches");
//        productDetail11.setOs("MIUI 12");
//        productDetail11.setCamara("Triple 108 MP");
//        productDetail11.setCamaraFront("20 MP");
//        productDetail11.setCpu("Snapdragon 888");
//        productDetail11.setRam("8 GB");
//        productDetail11.setRom("128 GB");
//        productDetail11.setMicroUSB("USB Type-C");
//        productDetail11.setBattery("4600 mAh");
//        productDetail11.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail11, ImageUrl);
//
//        ProductDetailDTO productDetail12 = new ProductDetailDTO();
//        productDetail12.setName("Google Pixel 6");
//        productDetail12.setCategory("Google");
//        ImageUrl = "google-pixel-6-600x600.jfif";
//        productDetail12.setDescription("Google's latest Pixel with pure Android experience");
//        productDetail12.setPrice(699.99f);
//        productDetail12.setScreen("6.4 inches");
//        productDetail12.setOs("Android 12");
//        productDetail12.setCamara("Dual 50 MP");
//        productDetail12.setCamaraFront("12 MP");
//        productDetail12.setCpu("Google Tensor");
//        productDetail12.setRam("8 GB");
//        productDetail12.setRom("128 GB");
//        productDetail12.setMicroUSB("USB Type-C");
//        productDetail12.setBattery("4614 mAh");
//        productDetail12.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail12, ImageUrl);
//
//        ProductDetailDTO productDetail13 = new ProductDetailDTO();
//        productDetail13.setName("OnePlus 9 Pro");
//        productDetail13.setCategory("OnePlus");
//        ImageUrl = "oneplus-9-pro-600x600.jfif";
//        productDetail13.setDescription("OnePlus flagship with Hasselblad camera and top performance");
//        productDetail13.setPrice(969.99f);
//        productDetail13.setScreen("6.7 inches");
//        productDetail13.setOs("OxygenOS 11");
//        productDetail13.setCamara("Quad 48 MP");
//        productDetail13.setCamaraFront("16 MP");
//        productDetail13.setCpu("Snapdragon 888");
//        productDetail13.setRam("12 GB");
//        productDetail13.setRom("256 GB");
//        productDetail13.setMicroUSB("USB Type-C");
//        productDetail13.setBattery("4500 mAh");
//        productDetail13.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail13, ImageUrl);
//
//        ProductDetailDTO productDetail14 = new ProductDetailDTO();
//        productDetail14.setName("Sony Xperia 1 III");
//        productDetail14.setCategory("Sony");
//        ImageUrl = "sony-xperia-1-iii-600x600.jfif";
//        productDetail14.setDescription("Sony's flagship with 4K HDR OLED display and pro camera");
//        productDetail14.setPrice(1199.99f);
//        productDetail14.setScreen("6.5 inches");
//        productDetail14.setOs("Android 11");
//        productDetail14.setCamara("Triple 12 MP");
//        productDetail14.setCamaraFront("8 MP");
//        productDetail14.setCpu("Snapdragon 888");
//        productDetail14.setRam("12 GB");
//        productDetail14.setRom("256 GB");
//        productDetail14.setMicroUSB("USB Type-C");
//        productDetail14.setBattery("4500 mAh");
//        productDetail14.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail14, ImageUrl);
//
//        ProductDetailDTO productDetail15 = new ProductDetailDTO();
//        productDetail15.setName("Huawei P50 Pro");
//        productDetail15.setCategory("Huawei");
//        ImageUrl = "huawei-p50-pro-600x600.jfif";
//        productDetail15.setDescription("Huawei's premium phone with powerful camera setup");
//        productDetail15.setPrice(1099.99f);
//        productDetail15.setScreen("6.6 inches");
//        productDetail15.setOs("HarmonyOS 2.0");
//        productDetail15.setCamara("Quad 50 MP");
//        productDetail15.setCamaraFront("13 MP");
//        productDetail15.setCpu("Kirin 9000");
//        productDetail15.setRam("8 GB");
//        productDetail15.setRom("256 GB");
//        productDetail15.setMicroUSB("USB Type-C");
//        productDetail15.setBattery("4360 mAh");
//        productDetail15.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail15, ImageUrl);
//
//        ProductDetailDTO productDetail16 = new ProductDetailDTO();
//        productDetail16.setName("Samsung Galaxy S21 Ultra");
//        productDetail16.setCategory("Samsung");
//        ImageUrl = "samsung-galaxy-s21-ultra-600x600.jfif";
//        productDetail16.setDescription("Samsung's ultimate flagship with top-tier camera and performance");
//        productDetail16.setPrice(1199.99f);
//        productDetail16.setScreen("6.8 inches");
//        productDetail16.setOs("Android 11");
//        productDetail16.setCamara("Quad 108 MP");
//        productDetail16.setCamaraFront("40 MP");
//        productDetail16.setCpu("Exynos 2100");
//        productDetail16.setRam("12 GB");
//        productDetail16.setRom("256 GB");
//        productDetail16.setMicroUSB("USB Type-C");
//        productDetail16.setBattery("5000 mAh");
//        productDetail16.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail16, ImageUrl);
//
//        ProductDetailDTO productDetail17 = new ProductDetailDTO();
//        productDetail17.setName("Apple iPhone 12 Pro Max");
//        productDetail17.setCategory("Apple");
//        ImageUrl = "iphone-12-pro-max-600x600.jfif";
//        productDetail17.setDescription("Apple's top-tier iPhone with impressive camera and performance");
//        productDetail17.setPrice(1099.99f);
//        productDetail17.setScreen("6.7 inches");
//        productDetail17.setOs("iOS 14");
//        productDetail17.setCamara("Triple 12 MP");
//        productDetail17.setCamaraFront("12 MP");
//        productDetail17.setCpu("A14 Bionic");
//        productDetail17.setRam("6 GB");
//        productDetail17.setRom("256 GB");
//        productDetail17.setMicroUSB("Lightning");
//        productDetail17.setBattery("3687 mAh");
//        productDetail17.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail17, ImageUrl);
//
//        ProductDetailDTO productDetail18 = new ProductDetailDTO();
//        productDetail18.setName("OnePlus 8T");
//        productDetail18.setCategory("OnePlus");
//        ImageUrl = "oneplus-8t-600x600.jfif";
//        productDetail18.setDescription("OnePlus's high-end phone with fast charging and smooth performance");
//        productDetail18.setPrice(749.99f);
//        productDetail18.setScreen("6.55 inches");
//        productDetail18.setOs("OxygenOS 11");
//        productDetail18.setCamara("Quad 48 MP");
//        productDetail18.setCamaraFront("16 MP");
//        productDetail18.setCpu("Snapdragon 865");
//        productDetail18.setRam("12 GB");
//        productDetail18.setRom("256 GB");
//        productDetail18.setMicroUSB("USB Type-C");
//        productDetail18.setBattery("4500 mAh");
//        productDetail18.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail18, ImageUrl);
//
//        ProductDetailDTO productDetail19 = new ProductDetailDTO();
//        productDetail19.setName("Google Pixel 5");
//        productDetail19.setCategory("Google");
//        ImageUrl = "google-pixel-5-600x600.jfif";
//        productDetail19.setDescription("Google's mid-range offering with excellent camera performance");
//        productDetail19.setPrice(699.99f);
//        productDetail19.setScreen("6.0 inches");
//        productDetail19.setOs("Android 11");
//        productDetail19.setCamara("Dual 12.2 MP");
//        productDetail19.setCamaraFront("8 MP");
//        productDetail19.setCpu("Snapdragon 765G");
//        productDetail19.setRam("8 GB");
//        productDetail19.setRom("128 GB");
//        productDetail19.setMicroUSB("USB Type-C");
//        productDetail19.setBattery("4080 mAh");
//        productDetail19.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail19, ImageUrl);
//
//        ProductDetailDTO productDetail20 = new ProductDetailDTO();
//        productDetail20.setName("Sony Xperia 5 II");
//        productDetail20.setCategory("Sony");
//        ImageUrl = "sony-xperia-5-ii-600x600.jfif";
//        productDetail20.setDescription("Sony's compact flagship with stunning display and camera");
//        productDetail20.setPrice(949.99f);
//        productDetail20.setScreen("6.1 inches");
//        productDetail20.setOs("Android 10");
//        productDetail20.setCamara("Triple 12 MP");
//        productDetail20.setCamaraFront("8 MP");
//        productDetail20.setCpu("Snapdragon 865");
//        productDetail20.setRam("8 GB");
//        productDetail20.setRom("128 GB");
//        productDetail20.setMicroUSB("USB Type-C");
//        productDetail20.setBattery("4000 mAh");
//        productDetail20.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail20, ImageUrl);
//
//        ProductDetailDTO productDetail21 = new ProductDetailDTO();
//        productDetail21.setName("Huawei Mate 40 Pro");
//        productDetail21.setCategory("Huawei");
//        ImageUrl = "huawei-mate-40-pro-600x600.jfif";
//        productDetail21.setDescription("Huawei's flagship with powerful camera and performance");
//        productDetail21.setPrice(1099.99f);
//        productDetail21.setScreen("6.76 inches");
//        productDetail21.setOs("HarmonyOS 2.0");
//        productDetail21.setCamara("Quad 50 MP");
//        productDetail21.setCamaraFront("13 MP");
//        productDetail21.setCpu("Kirin 9000");
//        productDetail21.setRam("8 GB");
//        productDetail21.setRom("256 GB");
//        productDetail21.setMicroUSB("USB Type-C");
//        productDetail21.setBattery("4400 mAh");
//        productDetail21.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail21, ImageUrl);
//
//        ProductDetailDTO productDetail22 = new ProductDetailDTO();
//        productDetail22.setName("Xiaomi Mi 10 Ultra");
//        productDetail22.setCategory("Xiaomi");
//        ImageUrl = "xiaomi-mi-10-ultra-600x600.jfif";
//        productDetail22.setDescription("Xiaomi's premium phone with 120W fast charging and impressive camera");
//        productDetail22.setPrice(899.99f);
//        productDetail22.setScreen("6.67 inches");
//        productDetail22.setOs("MIUI 12");
//        productDetail22.setCamara("Quad 48 MP");
//        productDetail22.setCamaraFront("20 MP");
//        productDetail22.setCpu("Snapdragon 865");
//        productDetail22.setRam("12 GB");
//        productDetail22.setRom("256 GB");
//        productDetail22.setMicroUSB("USB Type-C");
//        productDetail22.setBattery("4500 mAh");
//        productDetail22.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail22, ImageUrl);
//
//        ProductDetailDTO productDetail23 = new ProductDetailDTO();
//        productDetail23.setName("Oppo Find X3 Pro");
//        productDetail23.setCategory("Oppo");
//        ImageUrl = "oppo-find-x3-pro-600x600.jfif";
//        productDetail23.setDescription("Oppo's flagship with unique design and exceptional display");
//        productDetail23.setPrice(999.99f);
//        productDetail23.setScreen("6.7 inches");
//        productDetail23.setOs("ColorOS 11.2");
//        productDetail23.setCamara("Quad 50 MP");
//        productDetail23.setCamaraFront("32 MP");
//        productDetail23.setCpu("Snapdragon 888");
//        productDetail23.setRam("12 GB");
//        productDetail23.setRom("256 GB");
//        productDetail23.setMicroUSB("USB Type-C");
//        productDetail23.setBattery("4500 mAh");
//        productDetail23.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail23, ImageUrl);
//
//        ProductDetailDTO productDetail24 = new ProductDetailDTO();
//        productDetail24.setName("Nokia 8.3 5G");
//        productDetail24.setCategory("Nokia");
//        ImageUrl = "nokia-8-3-5g-600x600.jfif";
//        productDetail24.setDescription("Nokia's 5G phone with solid performance and clean Android experience");
//        productDetail24.setPrice(699.99f);
//        productDetail24.setScreen("6.81 inches");
//        productDetail24.setOs("Android 10");
//        productDetail24.setCamara("Quad 64 MP");
//        productDetail24.setCamaraFront("24 MP");
//        productDetail24.setCpu("Snapdragon 765G");
//        productDetail24.setRam("8 GB");
//        productDetail24.setRom("128 GB");
//        productDetail24.setMicroUSB("USB Type-C");
//        productDetail24.setBattery("4500 mAh");
//        productDetail24.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail24, ImageUrl);
//
//        ProductDetailDTO productDetail25 = new ProductDetailDTO();
//        productDetail25.setName("Motorola Edge Plus");
//        productDetail25.setCategory("Motorola");
//        ImageUrl = "motorola-edge-plus-600x600.jfif";
//        productDetail25.setDescription("Motorola's flagship with curved display and powerful performance");
//        productDetail25.setPrice(999.99f);
//        productDetail25.setScreen("6.7 inches");
//        productDetail25.setOs("Android 10");
//        productDetail25.setCamara("Triple 108 MP");
//        productDetail25.setCamaraFront("25 MP");
//        productDetail25.setCpu("Snapdragon 865");
//        productDetail25.setRam("12 GB");
//        productDetail25.setRom("256 GB");
//        productDetail25.setMicroUSB("USB Type-C");
//        productDetail25.setBattery("5000 mAh");
//        productDetail25.setColor(EColor.valueOf("BLACK").toString());
//        productDetailService.addProduct(productDetail25, ImageUrl);
//    }
//}
//
