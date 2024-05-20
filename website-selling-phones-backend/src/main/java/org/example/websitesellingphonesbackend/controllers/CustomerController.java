package org.example.websitesellingphonesbackend.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.websitesellingphonesbackend.entities.Customer;
import org.example.websitesellingphonesbackend.service.Admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserService userService;
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
        Customer customer = (Customer) session.getAttribute("customer");
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

        // Update customer information
        customer.setFirstName(firstname);
        customer.setLastName(lastname);
        customer.setPhoneNumber(phone);
        userService.updateCustomer(customer);

        session.setAttribute("customer", customer);
        return "redirect:/nguoidung/getProfile";
    }

}
