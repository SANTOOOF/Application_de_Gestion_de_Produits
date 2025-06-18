package com.youssef.productmanagement.repository;

import com.youssef.productmanagement.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Recherche par nom (insensible à la casse)
    List<Product> findByNameContainingIgnoreCase(String name);
    
    // Recherche par catégorie
    List<Product> findByCategory(String category);
    
    // Recherche par catégorie (insensible à la casse)
    List<Product> findByCategoryIgnoreCase(String category);
    
    // Recherche par disponibilité
    List<Product> findByIsAvailable(Boolean isAvailable);
    
    // Recherche par plage de prix
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    
    // Recherche par quantité supérieure à
    List<Product> findByQuantityGreaterThan(Integer quantity);
    
    // Recherche combinée avec pagination
    Page<Product> findByNameContainingIgnoreCaseAndCategoryIgnoreCase(
            String name, String category, Pageable pageable);
    
    // Recherche par mot-clé dans le nom ou la description
    @Query("SELECT p FROM Product p WHERE " +
           "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> findByKeyword(@Param("keyword") String keyword);
    
    // Recherche par mot-clé avec pagination
    @Query("SELECT p FROM Product p WHERE " +
           "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Product> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
    
    // Obtenir toutes les catégories distinctes
    @Query("SELECT DISTINCT p.category FROM Product p ORDER BY p.category")
    List<String> findDistinctCategories();
    
    // Compter les produits par catégorie
    @Query("SELECT p.category, COUNT(p) FROM Product p GROUP BY p.category")
    List<Object[]> countProductsByCategory();
    
    // Recherche avancée avec plusieurs critères
    @Query("SELECT p FROM Product p WHERE " +
           "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:category IS NULL OR LOWER(p.category) = LOWER(:category)) AND " +
           "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
           "(:isAvailable IS NULL OR p.isAvailable = :isAvailable)")
    Page<Product> findWithFilters(
            @Param("name") String name,
            @Param("category") String category,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("isAvailable") Boolean isAvailable,
            Pageable pageable);
}

