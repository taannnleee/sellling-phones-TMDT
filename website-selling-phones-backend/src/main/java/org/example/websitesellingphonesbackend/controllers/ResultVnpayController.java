package org.example.websitesellingphonesbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ResultVnpayController {
    @GetMapping("/home/vnpay-payment")
    public String handleVNPayResponse(Model model,
                                      @RequestParam("vnp_Amount") String vnpAmount,
                                      @RequestParam("vnp_BankCode") String vnpBankCode,
                                      @RequestParam("vnp_BankTranNo") String vnpBankTranNo,
                                      @RequestParam("vnp_CardType") String vnpCardType,
                                      @RequestParam("vnp_OrderInfo") String vnpOrderInfo,
                                      @RequestParam("vnp_PayDate") String vnpPayDate,
                                      @RequestParam("vnp_ResponseCode") String vnpResponseCode,
                                      @RequestParam("vnp_TmnCode") String vnpTmnCode,
                                      @RequestParam("vnp_TransactionNo") String vnpTransactionNo,
                                      @RequestParam("vnp_TransactionStatus") String vnpTransactionStatus,
                                      @RequestParam("vnp_TxnRef") String vnpTxnRef,
                                      @RequestParam("vnp_SecureHash") String vnpSecureHash) {

        // Đặt các thuộc tính của model với các giá trị từ URL
        model.addAttribute("vnpAmount", vnpAmount);
        model.addAttribute("vnpBankCode", vnpBankCode);
        model.addAttribute("vnpBankTranNo", vnpBankTranNo);
        model.addAttribute("vnpCardType", vnpCardType);
        model.addAttribute("vnpOrderInfo", vnpOrderInfo);
        model.addAttribute("vnpPayDate", vnpPayDate);
        model.addAttribute("vnpResponseCode", vnpResponseCode);
        model.addAttribute("vnpTmnCode", vnpTmnCode);
        model.addAttribute("vnpTransactionNo", vnpTransactionNo);
        model.addAttribute("vnpTransactionStatus", vnpTransactionStatus);
        model.addAttribute("vnpTxnRef", vnpTxnRef);
        model.addAttribute("vnpSecureHash", vnpSecureHash);

        // Trả về tên view
        return "views/resultVnPay";
    }
}
