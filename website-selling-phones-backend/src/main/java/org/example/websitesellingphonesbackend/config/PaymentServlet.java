//package org.example.websitesellingphonesbackend.config;
//
//import com.example.ute21110120webexercises.service.VNPayService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/vnpay-payment")
//public class PaymentServlet extends HttpServlet {
//
//    private VNPayService vnPayService;
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//        vnPayService = new VNPayService();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int paymentStatus = vnPayService.orderReturn(req);
//
//        String orderInfo = req.getParameter("vnp_OrderInfo");
//        String paymentTime = req.getParameter("vnp_PayDate");
//        String transactionId = req.getParameter("vnp_TransactionNo");
//        String totalPrice = req.getParameter("vnp_Amount");
//
//        req.setAttribute("orderId", orderInfo);
//        req.setAttribute("totalPrice", totalPrice);
//        req.setAttribute("paymentTime", paymentTime);
//        req.setAttribute("transactionId", transactionId);
//
//        String url;
//        if (paymentStatus == 1) {
//            url = "/ordersuccess.jsp";
//        } else {
//            url = "/orderfail.jsp";
//        }
//        getServletContext().getRequestDispatcher(url).forward(req, resp);
//    }
//
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String orderTotalStr = req.getParameter("amount");
//        int orderTotal = Integer.parseInt(orderTotalStr);
//        String orderInfo = req.getParameter("orderInfo");
//
//        String baseUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
//        String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);
//
//        resp.sendRedirect(vnpayUrl);
//    }
//}