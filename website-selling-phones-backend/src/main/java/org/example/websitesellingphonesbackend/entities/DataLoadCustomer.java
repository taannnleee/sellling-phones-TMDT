//package org.example.websitesellingphonesbackend.entities;
//import org.example.websitesellingphonesbackend.DTO.CustomerDTO;
//import org.example.websitesellingphonesbackend.Enum.EColor;
//import org.example.websitesellingphonesbackend.Enum.ERole;
//import org.example.websitesellingphonesbackend.repositories.CategoryRepository;
//import org.example.websitesellingphonesbackend.repositories.CustomerRepository;
//import org.example.websitesellingphonesbackend.repositories.ProductDetailRepository;
//import org.example.websitesellingphonesbackend.repositories.ProductRepository;
//import org.example.websitesellingphonesbackend.service.AccountService;
//import org.example.websitesellingphonesbackend.service.CustomerService;
//import org.example.websitesellingphonesbackend.service.Impl.CategoryServiceImpl;
//import org.example.websitesellingphonesbackend.service.Impl.CustomerServiceImpl;
//import org.example.websitesellingphonesbackend.service.Impl.ProductDetailServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Component
//public class DataLoadCustomer implements CommandLineRunner {
//
//    @Autowired
//    private AccountService accountService;
//
//    @Autowired
//    private CustomerServiceImpl customerServiceImpl;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Adding 10 customer data entries
//        for (int i = 1; i <= 10; i++) {
//            CustomerDTO customer = new CustomerDTO();
//            customer.setFirstName("FirstName" + i);
//            customer.setLastName("LastName" + i);
//            customer.setEmail("customer" + i + "@example.com");
//
//            // Setting date of birth
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date dateOfBirth = sdf.parse("2003-12-28");
//            customer.setDateOfBirth(dateOfBirth);
//
//            customer.setPhoneNumber("03678929" + i);
//            customer.setRole(ERole.USER);
//            customer.setPassHash(accountService.hashPassword("12345678"));
//            customerServiceImpl.addCustomer(customer);
//        }
//    }
//}
