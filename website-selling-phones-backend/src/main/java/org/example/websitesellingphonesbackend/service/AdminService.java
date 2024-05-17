package org.example.websitesellingphonesbackend.service;

import org.example.websitesellingphonesbackend.entities.Admin;

public interface AdminService {
    boolean registerAdmin(Admin admin);
    boolean checkAdmin(String email, String password);
}
