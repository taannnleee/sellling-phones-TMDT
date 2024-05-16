package org.example.websitesellingphonesbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/trungtambaohanh")
public class TrungTamBaoHanhController {

    @GetMapping
    public String index(Model model) {
        try {
            return "views/trungtambaohanh";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi tải trang trung tâm bảo hành: " + e.getMessage());
            return "views/error";
        }
    }
}