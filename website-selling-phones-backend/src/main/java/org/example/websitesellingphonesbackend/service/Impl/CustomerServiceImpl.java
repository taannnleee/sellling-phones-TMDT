package org.example.websitesellingphonesbackend.service.Impl;

import org.example.websitesellingphonesbackend.DTO.CustomerDTO;
import org.example.websitesellingphonesbackend.Enum.EMessage;
import org.example.websitesellingphonesbackend.Enum.ERole;
import org.example.websitesellingphonesbackend.entities.Address;
import org.example.websitesellingphonesbackend.entities.Cart;
import org.example.websitesellingphonesbackend.entities.Customer;
import org.example.websitesellingphonesbackend.mapper.Mappers;
import org.example.websitesellingphonesbackend.repositories.CartRepository;
import org.example.websitesellingphonesbackend.repositories.CustomerRepository;
import org.example.websitesellingphonesbackend.service.AccountService;
import org.example.websitesellingphonesbackend.service.CartService;
import org.example.websitesellingphonesbackend.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.example.websitesellingphonesbackend.Enum.EMessage.*;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountService accountService;
    @Autowired
    private CartRepository   cartRepository ;

    @Override
    public Customer authenticateCustomer(String email, String password) {
        Customer customer = customerRepository.findByEmail(email).orElse(null);
        if (customer != null) {
            String passHash = accountService.hashPassword(password);
            if (passHash.equals(customer.getPassHash())) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public void registerCustomer(CustomerDTO customerDTO, Cart cart, Address address) {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Định dạng lại ngày giờ để loại bỏ phần nghìn giây
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(currentDate);
        try {
            Customer customer_exist = customerRepository.findByEmail(customerDTO.getEmail()).orElse(null);
            if(customer_exist==null){
                String passHash = accountService.hashPassword(customerDTO.getPassHash());
                customerDTO.setPassHash(passHash);
                customerDTO.setAccountBalance(0);
                customerDTO.setRole(ERole.USER);
                Customer customer = Mappers.convertToEntity(customerDTO, Customer.class);

                cart.setCustomer(customer);
                cart.setCreateDate(sdf.parse(formattedDate));
                cart.setTotalPrice(0F);
                customer.setCart(cart);

                address.setCustomer(customer);

                customer.setAddresss(address);

                customerRepository.save(customer);
            }
        }
        catch (Exception e){
            System.out.println("Error: "+e);
        }
    }

    @Override
    public EMessage checkCustomer(CustomerDTO customerDTO) {
        try {
            Customer customer_exist = customerRepository.findByEmail(customerDTO.getEmail()).orElse(null);
            if(customer_exist!=null){
                return CUSTOMER_EXIST;
            }
            if (!customerDTO.getPassHash().equals(customerDTO.getRepeatPassword())) {
                return  CONFIRM_PASSWORD_NOT_MATCH;
            }
        }
        catch (Exception e){
            return REGISTER_FAIL;
        }
        return REGISTER_SUCCESS;
    }

    @Override
    public Customer getCustomerById(Long customerDTO_id) {
        return null;
    }


    @Override
    public EMessage changePassword(String email, String oldPassword, String newPassword) {
        try{
            String oldPasswordHash = accountService.hashPassword(oldPassword);
            String newPasswordHash = accountService.hashPassword(newPassword);
            Customer customer = customerRepository.findByEmail(email).orElse(null);
            if(customer == null)
                return EMessage.CUSTOMER_NOT_EXIST;
            if(customer.getPassHash().equals(oldPasswordHash)){
                customer.setPassHash(newPasswordHash);
                customerRepository.save(customer);
                return EMessage.CHANGE_PASS_WORD_SUCCESS;
            }
            return EMessage.OLD_PASS_NOT_MATCH;
        }catch(Exception e){
            logger.error("Lỗi khi đổi mật khẩu cho khách hàng: " + email + ". Lỗi: " + e.getMessage());
            return EMessage.CHANGE_PASS_WORD_NOT_SUCCESS;
        }
    }

    @Override
    public EMessage changePassword(String email, String newPassword) {
        Customer customer =  customerRepository.findByEmail(email).orElse(null);
        if(customer == null)
            return EMessage.CUSTOMER_NOT_EXIST;
        customer.setPassHash(accountService.hashPassword(newPassword));
        customerRepository.save(customer);
        return EMessage.CHANGE_PASS_WORD_SUCCESS;
    }


    @Override
    public Customer getCustomerByEmail(String email) {
        Optional<Customer> customer =  customerRepository.findByEmail(email);
        return customer.orElse(null);
    }

    @Override
    public Customer recharge(Long cusId, String denominations){
//        Optional<Customer> customer =  customerRepository.findById(cusId);
//        if(customer!=null){
//            int tempBalance = Integer.parseInt(denominations) + customer.get().getAccountBalance();
//            customer.get().setAccountBalance(tempBalance);
//            customerRepository.save(customer.get());
//            return  customer.get();
//        }
//        else
//            return null;
        return  null;
    }

    @Override
    public Customer deductMoney(Long cusId, String denominations){
//        Optional<Customer> customer =  customerRepository.findById(cusId);
//        if(customer.isPresent()){
//            int tempBalance =customer.get().getAccountBalance() - Integer.parseInt(denominations);
//            customer.get().setAccountBalance(tempBalance);
//            customerRepository.save(customer.get());
//            return  customer.get();
//        }
//        else
//            return null;
        return null;
    }
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
    @Override
    public void addCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setPassHash(accountService.hashPassword(customerDTO.getPassHash()));
        // Add other fields as needed

        customerRepository.save(customer);
    }
    public void updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);

        if (existingCustomer != null) {
            existingCustomer.setCustomerId(customerDTO.getCustomerId());
            existingCustomer.setFirstName(customerDTO.getFirstName());
            existingCustomer.setLastName(customerDTO.getLastName());
            existingCustomer.setEmail(customerDTO.getEmail());
            existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
            existingCustomer.setPassHash(customerDTO.getPassHash());
            // Add other customer fields as needed

            customerRepository.save(existingCustomer);
        }
    }



}
