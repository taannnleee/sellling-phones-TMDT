package org.example.websitesellingphonesbackend.config;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.IOException;

@Controller
public class PaymentController {

    @Autowired
    VNPayService vnPayService;

    @GetMapping("/vnpay-payment")
    public String handleGet(HttpServletRequest request, @RequestParam("vnp_OrderInfo") String orderInfo,
                            @RequestParam("vnp_PayDate") String paymentTime,
                            @RequestParam("vnp_TransactionNo") String transactionId,
                            @RequestParam("vnp_Amount") String totalPrice) {
        int paymentStatus = vnPayService.orderReturn(request);

        if (paymentStatus == 1) {
            return "views/ordersuccess";
        } else {
            return "views/orderfail";
        }
    }

    @PostMapping("/vnpay-payment")
    public void handlePost(HttpServletResponse resp,
                           @RequestParam("amount") String orderTotalStr,
                           @RequestParam("orderInfo") String orderInfo) throws IOException {
        int orderTotal = Integer.parseInt(orderTotalStr);

        String baseUrl = resp.encodeRedirectURL("");
        String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);

        resp.sendRedirect(vnpayUrl);
    }
}
