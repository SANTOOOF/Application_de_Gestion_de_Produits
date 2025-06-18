package com.youssef.productmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@com.youssef.productmanagement.controller.Controller
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return "redirect:/products";
    }
    
    @GetMapping("/home")
    public String homePage() {
        return "redirect:/products";
    }
}

