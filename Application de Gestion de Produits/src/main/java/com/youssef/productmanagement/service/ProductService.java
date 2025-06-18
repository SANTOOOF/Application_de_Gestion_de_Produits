package com.youssef.productmanagement.service;

import com.youssef.productmanagement.entity.Product;
import com.youssef.productmanagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    
    private final ProductRepository productRepository;
    
    // Sauvegarder un produit
    public Product save(Product product) {
        return productRepository.save(product);
    }
    
    // Obtenir tous les produits
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    
    // Obtenir tous les produits avec pagination
    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    
    // Obtenir un produit par ID
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
    
    // Supprimer un produit par ID
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
    
    // Vérifier si un produit existe
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }
    
    // Rechercher par nom
    @Transactional(readOnly = true)
    public List<Product> findByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
    
    // Rechercher par catégorie
    @Transactional(readOnly = true)
    public List<Product> findByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }
    
    // Rechercher par mot-clé
    @Transactional(readOnly = true)
    public List<Product> findByKeyword(String keyword) {
        return productRepository.findByKeyword(keyword);
    }
    
    // Rechercher par mot-clé avec pagination
    @Transactional(readOnly = true)
    public Page<Product> findByKeyword(String keyword, Pageable pageable) {
        return productRepository.findByKeyword(keyword, pageable);
    }
    
    // Rechercher par plage de prix
    @Transactional(readOnly = true)
    public List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
    
    // Obtenir toutes les catégories
    @Transactional(readOnly = true)
    public List<String> findAllCategories() {
        return productRepository.findDistinctCategories();
    }
    
    // Recherche avancée avec filtres
    @Transactional(readOnly = true)
    public Page<Product> findWithFilters(String name, String category, 
                                       BigDecimal minPrice, BigDecimal maxPrice, 
                                       Boolean isAvailable, Pageable pageable) {
        return productRepository.findWithFilters(name, category, minPrice, maxPrice, isAvailable, pageable);
    }
    
    // Mettre à jour la disponibilité d'un produit
    public Product updateAvailability(Long id, Boolean isAvailable) {
        Optional<Product> productOpt = findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setIsAvailable(isAvailable);
            return save(product);
        }
        throw new RuntimeException("Produit non trouvé avec l'ID: " + id);
    }
    
    // Mettre à jour la quantité d'un produit
    public Product updateQuantity(Long id, Integer quantity) {
        Optional<Product> productOpt = findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setQuantity(quantity);
            return save(product);
        }
        throw new RuntimeException("Produit non trouvé avec l'ID: " + id);
    }
}

