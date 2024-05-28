package org.example.websitesellingphonesbackend.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.entities.Customer;
import org.example.websitesellingphonesbackend.service.AccountService;
import org.example.websitesellingphonesbackend.service.Admin.UserService;
import org.example.websitesellingphonesbackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.example.websitesellingphonesbackend.DTO.CustomerDTO;

@Controller
@RequiredArgsConstructor
@RequestMapping("/nguoidung")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;
//    @GetMapping
//    public String index(Model model) {
//        try {
//            return "views/nguoidung";
//        } catch (Exception e) {
//            model.addAttribute("error", "Lỗi tải trang: " + e.getMessage());
//            return "views/error";
//        }
//    }

    @GetMapping("/getProfile")
    public String getProfile(Model model, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customerForProfile");
        if (customer == null){
            customer = (Customer) session.getAttribute("customer");
        }
        if (customer != null){
            try {
                model.addAttribute("customer", customer);
                return "views/profile";
            } catch (Exception e) {
                model.addAttribute("error", "Lỗi tải trang: " + e.getMessage());
                return "views/error";
            }
        } else {
            return "redirect:/index";
        }

    }

    @PostMapping("/updateProfile")
    public String handleProfileUpdate(
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            HttpSession session) {

        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/index";
        }

        CustomerDTO customerDTO = new CustomerDTO(firstname, lastname, email, phone, customer.getPassHash());

        // Update customer information
        customerService.updateCustomer(customer.getCustomerId(), customerDTO);
        Customer new_customer = customerService.getCustomerByEmail(customerDTO.getEmail());
        System.out.println(">>ronaldo98" + new_customer.getFirstName());

        session.setAttribute("customerForProfile", new_customer);
        return "redirect:/nguoidung/getProfile";
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestParam("currentPassword") String currentPassword,
                                                 @RequestParam("newPassword") String newPassword,
                                                 @RequestParam("confirmNewPassword") String confirmNewPassword,
                                                 HttpSession session) {

        Customer customer = (Customer) session.getAttribute("customerForProfile");
        if (customer == null){
            customer = (Customer) session.getAttribute("customer");
        }

        if (customer == null) {
            return ResponseEntity.badRequest().body("No customer is logged in");
        }

        //mật khẩu cũ không đúng
        if (!customer.getPassHash().equals(accountService.hashPassword(currentPassword))) {
            return ResponseEntity.badRequest().body("Current password is incorrect");
        }

        if (!newPassword.equals(confirmNewPassword)) {
            return ResponseEntity.badRequest().body("New passwords do not match");
        }

        String newPass = accountService.hashPassword(newPassword);
        CustomerDTO customerDTO = new CustomerDTO(customer.getFirstName(), customer.getLastName(),
                customer.getEmail(), customer.getPhoneNumber(), newPass);

        // Update customer information
        customerService.updateCustomer(customer.getCustomerId(), customerDTO);
        Customer newCustomer = customerService.getCustomerByEmail(customerDTO.getEmail());
        session.setAttribute("customerForProfile", newCustomer);

        return ResponseEntity.ok("Password changed successfully");
    }



}
