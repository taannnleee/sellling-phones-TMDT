package org.example.websitesellingphonesbackend;

import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.Enum.ERole;
import org.example.websitesellingphonesbackend.entities.Admin;
import org.example.websitesellingphonesbackend.service.AdminService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class WebsiteSellingPhonesBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsiteSellingPhonesBackendApplication.class, args);
    }
    private final AdminService adminService;
    @Bean
    public Admin initAdmin(){
        Admin admin = Admin
                .builder()
                .email("admin@gmail.com")
                .passHash("admin")
                .role(ERole.ADMIN)
                .build();
        adminService.registerAdmin(admin);
        return  admin;
    }
}
