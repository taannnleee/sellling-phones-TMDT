package org.example.websitesellingphonesbackend.service.Impl;

import org.example.websitesellingphonesbackend.Enum.EStatus;
import org.example.websitesellingphonesbackend.Enum.EpaymentMethod;
import org.example.websitesellingphonesbackend.entities.*;
import org.example.websitesellingphonesbackend.repositories.OrderRepository;
import org.example.websitesellingphonesbackend.service.CartService;
import org.example.websitesellingphonesbackend.service.LineItemService;
import org.example.websitesellingphonesbackend.service.OrderService;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartService  cartService;
    @Autowired
    LineItemService lineItemService;

    @Override
    public Order_Product insertOrder(Cart cart, Customer customer, String paymentType){
        try {


            Payment payment = new Payment();
            List<OrderDetailLine> orderDetailLines = new ArrayList<>();

            List<LineItem> lineItemList =  lineItemService.listLineItemOfCart(cart);

            Calendar calendar = Calendar.getInstance();

            // Lấy giờ, phút và giây hiện tại
            int hour = calendar.get(Calendar.HOUR_OF_DAY); // Giờ
            int minute = calendar.get(Calendar.MINUTE); // Phút
            int second = calendar.get(Calendar.SECOND); // Giây

            // Đặt thời gian cho ngày hiện tại
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Sử dụng các giá trị này cho đối tượng Date
            calendar.set(year, month, day, hour, minute, second);

            // Lấy đối tượng Date từ Calendar
            Date currentDate = calendar.getTime();

            Order_Product order = new Order_Product();
            order.setCart(cart);
            order.setCustomer(customer);
            order.setTotal(cart.getTotalPrice());
            order.setOrderStatus(EStatus.PROCESSING);
            order.setOrderDate(currentDate);

            payment.setPaymentDate(currentDate);

            EpaymentMethod paymentMethod;
            int paymentTypeInt = Integer.parseInt(paymentType);
            if (paymentTypeInt == 1) {
                paymentMethod = EpaymentMethod.CASHPAYMENT;
            } else if (paymentTypeInt == 2) {
                paymentMethod = EpaymentMethod.MOMO;
            } else {
                // Xử lý trường hợp không hợp lệ (nếu cần)
                throw new IllegalArgumentException("Invalid payment type: " + paymentTypeInt);
            }
            payment.setEpaymentMethod(paymentMethod);

            order.setPayment(payment);

            // Chuyển đổi từ LineItem sang OrderDetailLine
            for (LineItem lineItem : lineItemList) {
                OrderDetailLine orderDetailLine = new OrderDetailLine();
                orderDetailLine.setProduct(lineItem.getProduct());
                orderDetailLine.setQuanlity(lineItem.getQuanlity());
                orderDetailLine.setPrice(lineItem.getPrice());
                orderDetailLine.setOrder(order);
                // Thêm OrderDetailLine vào danh sách
                orderDetailLines.add(orderDetailLine);
            }
            order.setOrderDetailLines(orderDetailLines);
            orderRepository.save(order);

            lineItemService.deleteAllLineItemByCart(cart);


            return order;
        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Order_Product> getOrdersByCustomer(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }

    @Override
    public List<Order_Product> getAllOrder(){
        return orderRepository.findAll();
    }

    @Override
    public Order_Product getOrderById(Long id){
        return orderRepository.findByOrderId(id);
    }

    @Override
    public void updateStatusOrder(Long id){
        Order_Product orderProduct =  getOrderById(id);
        orderProduct.setOrderStatus(EStatus.COMPLETED);
        orderRepository.save(orderProduct);
    }

    @Override
    public  long countOrder(){
        return orderRepository.count();
    }



}
