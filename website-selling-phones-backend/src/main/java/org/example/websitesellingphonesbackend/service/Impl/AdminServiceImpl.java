package org.example.websitesellingphonesbackend.service.Impl;

import org.example.websitesellingphonesbackend.DTO.CustomerDTO;
import org.example.websitesellingphonesbackend.Enum.EMessage;
import org.example.websitesellingphonesbackend.entities.Admin;
import org.example.websitesellingphonesbackend.entities.Customer;
import org.example.websitesellingphonesbackend.repositories.AdminRepository;
import org.example.websitesellingphonesbackend.service.AccountService;
import org.example.websitesellingphonesbackend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.example.websitesellingphonesbackend.Enum.EMessage.*;
import static org.example.websitesellingphonesbackend.Enum.EMessage.REGISTER_SUCCESS;

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
    @Override
    public boolean checkAdmin(String  email, String password) {
        try {
            Admin admin_exist = adminRepository.findByEmailAndPassHash(email, password).orElse(null);
            if(admin_exist!=null){
                return true;
            }

        }
        catch (Exception e){
            return false;
        }
        return false;
    }
}
