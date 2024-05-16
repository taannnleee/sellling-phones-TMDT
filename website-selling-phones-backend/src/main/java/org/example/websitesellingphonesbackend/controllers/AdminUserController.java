package org.example.websitesellingphonesbackend.controllers;

import org.example.websitesellingphonesbackend.DTO.CustomerDTO;
import org.example.websitesellingphonesbackend.entities.Customer;
import org.example.websitesellingphonesbackend.service.Admin.UserService;
import org.example.websitesellingphonesbackend.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
    final private CustomerService customerService;
    private final UserService userService;

    public AdminUserController(CustomerService customerService, UserService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }


    @GetMapping("/{lisCustomer}")
    public List<Customer> getAllCustomers() {
        return userService.getAllCustomers();
    }


    @GetMapping("/customer")
    public String LoadUser(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "views/adminviews/adminCustomer";
    }
    @DeleteMapping("/delete-customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Xóa sản phẩm thành công!");
    }
    @PostMapping("/add-customer")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.addCustomer(customerDTO);
        return ResponseEntity.ok("Thêm khách hàng thành công!");
    }
    @PutMapping("/update-customer/{id}")
    public ResponseEntity<String>  updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.ok("Cập nhật khách hàng thành công!");
    }
    @GetMapping("/invoice")
    public  String invoice(Model model) {
        return "views/invoice";
    }




}
