package com.youssef.productmanagement.controller;

import com.youssef.productmanagement.entity.Product;
import com.youssef.productmanagement.entity.User;
import com.youssef.productmanagement.service.ProductService;
import com.youssef.productmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    
    private final ProductService productService;
    private final UserService userService;
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Statistiques des produits
        List<Product> allProducts = productService.findAll();
        long totalProducts = allProducts.size();
        long availableProducts = allProducts.stream().filter(Product::getIsAvailable).count();
        long outOfStockProducts = allProducts.stream().filter(p -> p.getQuantity() == 0).count();
        
        // Valeur totale du stock
        BigDecimal totalStockValue = allProducts.stream()
                .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        // Statistiques par catégorie
        Map<String, Long> productsByCategory = allProducts.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
        
        // Statistiques des utilisateurs
        List<User> allUsers = userService.findAll();
        long totalUsers = allUsers.size();
        long activeUsers = allUsers.stream().filter(User::getEnabled).count();
        
        // Statistiques par rôle
        Map<String, Long> usersByRole = allUsers.stream()
                .flatMap(user -> user.getRoles().stream())
                .collect(Collectors.groupingBy(Enum::name, Collectors.counting()));
        
        // Produits les plus chers
        List<Product> expensiveProducts = allProducts.stream()
                .sorted((p1, p2) -> p2.getPrice().compareTo(p1.getPrice()))
                .limit(5)
                .collect(Collectors.toList());
        
        // Produits en rupture de stock
        List<Product> lowStockProducts = allProducts.stream()
                .filter(p -> p.getQuantity() <= 5)
                .sorted((p1, p2) -> Integer.compare(p1.getQuantity(), p2.getQuantity()))
                .collect(Collectors.toList());
        
        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("availableProducts", availableProducts);
        model.addAttribute("outOfStockProducts", outOfStockProducts);
        model.addAttribute("totalStockValue", totalStockValue);
        model.addAttribute("productsByCategory", productsByCategory);
        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("activeUsers", activeUsers);
        model.addAttribute("usersByRole", usersByRole);
        model.addAttribute("expensiveProducts", expensiveProducts);
        model.addAttribute("lowStockProducts", lowStockProducts);
        
        return "admin/dashboard";
    }
    
    @GetMapping("/users")
    public String manageUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/users";
    }
    
    @GetMapping("/reports")
    public String reports(Model model) {
        // Ici on pourrait ajouter des rapports plus détaillés
        return "admin/reports";
    }
}

