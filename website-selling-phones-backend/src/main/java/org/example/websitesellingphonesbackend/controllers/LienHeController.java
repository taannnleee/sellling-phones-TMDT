package org.example.websitesellingphonesbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lienhe")
public class LienHeController {
    @GetMapping
    public String index(Model model) {
        try {
            return "views/lienhe";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi tải trang: " + e.getMessage());
            return "views/error";
        }
    }
}
