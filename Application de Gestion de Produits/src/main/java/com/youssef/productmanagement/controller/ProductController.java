package com.youssef.productmanagement.controller;

import com.youssef.productmanagement.entity.Product;
import com.youssef.productmanagement.service.ProductService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;
    
    // Page d'accueil - Liste des produits avec pagination
    @GetMapping
    public String listProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Boolean isAvailable,
            Model model) {
        
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
                   Sort.by(sortBy).descending() : 
                   Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage;
        
        // Recherche avec filtres ou liste complète
        if (keyword != null && !keyword.trim().isEmpty()) {
            productPage = productService.findByKeyword(keyword.trim(), pageable);
            model.addAttribute("keyword", keyword);
        } else if (hasFilters(category, minPrice, maxPrice, isAvailable)) {
            productPage = productService.findWithFilters(null, category, minPrice, maxPrice, isAvailable, pageable);
        } else {
            productPage = productService.findAll(pageable);
        }
        
        // Ajouter les attributs au modèle
        model.addAttribute("productPage", productPage);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("totalElements", productPage.getTotalElements());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        
        // Filtres
        model.addAttribute("category", category);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("isAvailable", isAvailable);
        model.addAttribute("categories", productService.findAllCategories());
        
        return "products/list";
    }
    
    // Afficher le formulaire d'ajout de produit
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", productService.findAllCategories());
        return "products/form";
    }
    
    // Traiter l'ajout d'un nouveau produit
    @PostMapping("/new")
    public String addProduct(@Valid @ModelAttribute Product product, 
                           BindingResult result, 
                           Model model,
                           RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            model.addAttribute("categories", productService.findAllCategories());
            return "products/form";
        }
        
        try {
            productService.save(product);
            redirectAttributes.addFlashAttribute("successMessage", 
                "Produit '" + product.getName() + "' ajouté avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", 
                "Erreur lors de l'ajout du produit : " + e.getMessage());
        }
        
        return "redirect:/products";
    }
    
    // Afficher les détails d'un produit
    @GetMapping("/{id}")
    public String showProduct(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Product> productOpt = productService.findById(id);
        
        if (productOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Produit non trouvé !");
            return "redirect:/products";
        }
        
        model.addAttribute("product", productOpt.get());
        return "products/detail";
    }
    
    // Afficher le formulaire d'édition
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Product> productOpt = productService.findById(id);
        
        if (productOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Produit non trouvé !");
            return "redirect:/products";
        }
        
        model.addAttribute("product", productOpt.get());
        model.addAttribute("categories", productService.findAllCategories());
        return "products/form";
    }
    
    // Traiter la mise à jour d'un produit
    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable Long id,
                              @Valid @ModelAttribute Product product,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            model.addAttribute("categories", productService.findAllCategories());
            return "products/form";
        }
        
        try {
            product.setId(id);
            productService.save(product);
            redirectAttributes.addFlashAttribute("successMessage", 
                "Produit '" + product.getName() + "' mis à jour avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", 
                "Erreur lors de la mise à jour : " + e.getMessage());
        }
        
        return "redirect:/products";
    }
    
    // Supprimer un produit
    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Optional<Product> productOpt = productService.findById(id);
            if (productOpt.isPresent()) {
                String productName = productOpt.get().getName();
                productService.deleteById(id);
                redirectAttributes.addFlashAttribute("successMessage", 
                    "Produit '" + productName + "' supprimé avec succès !");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Produit non trouvé !");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", 
                "Erreur lors de la suppression : " + e.getMessage());
        }
        
        return "redirect:/products";
    }
    
    // Basculer la disponibilité d'un produit
    @PostMapping("/{id}/toggle-availability")
    public String toggleAvailability(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Optional<Product> productOpt = productService.findById(id);
            if (productOpt.isPresent()) {
                Product product = productOpt.get();
                product.setIsAvailable(!product.getIsAvailable());
                productService.save(product);
                
                String status = product.getIsAvailable() ? "disponible" : "indisponible";
                redirectAttributes.addFlashAttribute("successMessage", 
                    "Produit '" + product.getName() + "' marqué comme " + status + " !");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Produit non trouvé !");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", 
                "Erreur lors de la mise à jour : " + e.getMessage());
        }
        
        return "redirect:/products";
    }
    
    // Méthode utilitaire pour vérifier s'il y a des filtres
    private boolean hasFilters(String category, BigDecimal minPrice, BigDecimal maxPrice, Boolean isAvailable) {
        return (category != null && !category.trim().isEmpty()) ||
               minPrice != null ||
               maxPrice != null ||
               isAvailable != null;
    }
}

