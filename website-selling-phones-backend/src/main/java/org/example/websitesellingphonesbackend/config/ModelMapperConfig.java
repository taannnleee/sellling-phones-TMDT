//package org.example.websitesellingphonesbackend.config;
//
//import org.example.websitesellingphonesbackend.DTO.CustomerDTO;
//import org.example.websitesellingphonesbackend.Enum.ERole;
//import org.example.websitesellingphonesbackend.entities.Admin;
//import org.example.websitesellingphonesbackend.service.AdminService;
//import org.example.websitesellingphonesbackend.service.CustomerService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ModelMapperConfig {
//    private final AdminService adminService;
//
//    public ModelMapperConfig(AdminService adminService) {
//        this.adminService = adminService;
//    }
//
//    @Bean
//    public Admin initAdmin(){
//        Admin admin = Admin
//                .builder()
//                .email("admin@admin.admin")
//                .passHash("admin")
//                .role(ERole.ADMIN)
//                .build();
//        adminService.registerAdmin(admin);
//        return  admin;
//    }
//
//}
