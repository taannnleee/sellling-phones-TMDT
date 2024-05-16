package org.example.websitesellingphonesbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tintuc")
public class TinTucController {
    @GetMapping
    public String index(Model model) {
        try {
            return "views/tintuc";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi tải tin tức: " + e.getMessage());
            return "views/error";
        }
    }
}