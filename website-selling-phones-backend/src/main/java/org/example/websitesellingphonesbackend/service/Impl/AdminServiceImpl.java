package org.example.websitesellingphonesbackend.service.Impl;

import org.example.websitesellingphonesbackend.entities.Admin;
import org.example.websitesellingphonesbackend.entities.Customer;
import org.example.websitesellingphonesbackend.repositories.AdminRepository;
import org.example.websitesellingphonesbackend.service.AccountService;
import org.example.websitesellingphonesbackend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public boolean registerAdmin(Admin admin) {
        try {
            Admin checkAdmin = new Admin();
            Admin admin_exist = adminRepository.findByEmail(admin.getEmail()).orElse(null);
            if(admin_exist == null){
                String passHash = accountService.hashPassword(admin.getPassHash());
                checkAdmin.setPassHash(passHash);
                checkAdmin.setEmail(admin.getEmail());
                checkAdmin.setRole(admin.getRole());
                adminRepository.save(admin);
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }
}
