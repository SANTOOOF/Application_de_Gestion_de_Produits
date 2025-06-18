package com.youssef.productmanagement.repository;

import com.youssef.productmanagement.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Test
    public void testSaveAndFindProduct() {
        // Créer un produit de test
        Product product = new Product();
        product.setName("Produit Test");
        product.setDescription("Description du produit test");
        product.setPrice(new BigDecimal("99.99"));
        product.setQuantity(10);
        product.setCategory("Test");
        product.setIsAvailable(true);
        
        // Sauvegarder le produit
        Product savedProduct = productRepository.save(product);
        
        // Vérifier que le produit a été sauvegardé
        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo("Produit Test");
        
        // Rechercher le produit par ID
        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());
        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getName()).isEqualTo("Produit Test");
    }
    
    @Test
    public void testFindByNameContainingIgnoreCase() {
        // Créer des produits de test
        Product product1 = new Product();
        product1.setName("Ordinateur Portable");
        product1.setDescription("Description");
        product1.setPrice(new BigDecimal("999.99"));
        product1.setQuantity(5);
        product1.setCategory("Électronique");
        product1.setIsAvailable(true);
        
        Product product2 = new Product();
        product2.setName("Smartphone");
        product2.setDescription("Description");
        product2.setPrice(new BigDecimal("599.99"));
        product2.setQuantity(10);
        product2.setCategory("Électronique");
        product2.setIsAvailable(true);
        
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.flush();
        
        // Tester la recherche par nom
        List<Product> products = productRepository.findByNameContainingIgnoreCase("ordinateur");
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getName()).isEqualTo("Ordinateur Portable");
    }
    
    @Test
    public void testFindByCategory() {
        // Créer des produits de test
        Product product1 = new Product();
        product1.setName("Produit 1");
        product1.setDescription("Description");
        product1.setPrice(new BigDecimal("99.99"));
        product1.setQuantity(5);
        product1.setCategory("Électronique");
        product1.setIsAvailable(true);
        
        Product product2 = new Product();
        product2.setName("Produit 2");
        product2.setDescription("Description");
        product2.setPrice(new BigDecimal("199.99"));
        product2.setQuantity(10);
        product2.setCategory("Vêtements");
        product2.setIsAvailable(true);
        
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.flush();
        
        // Tester la recherche par catégorie
        List<Product> electronicsProducts = productRepository.findByCategoryIgnoreCase("électronique");
        assertThat(electronicsProducts).hasSize(1);
        assertThat(electronicsProducts.get(0).getCategory()).isEqualTo("Électronique");
    }
    
    @Test
    public void testFindByPriceBetween() {
        // Créer des produits de test avec différents prix
        Product product1 = new Product();
        product1.setName("Produit Pas Cher");
        product1.setDescription("Description");
        product1.setPrice(new BigDecimal("50.00"));
        product1.setQuantity(5);
        product1.setCategory("Test");
        product1.setIsAvailable(true);
        
        Product product2 = new Product();
        product2.setName("Produit Moyen");
        product2.setDescription("Description");
        product2.setPrice(new BigDecimal("150.00"));
        product2.setQuantity(10);
        product2.setCategory("Test");
        product2.setIsAvailable(true);
        
        Product product3 = new Product();
        product3.setName("Produit Cher");
        product3.setDescription("Description");
        product3.setPrice(new BigDecimal("500.00"));
        product3.setQuantity(2);
        product3.setCategory("Test");
        product3.setIsAvailable(true);
        
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(product3);
        entityManager.flush();
        
        // Tester la recherche par plage de prix
        List<Product> productsInRange = productRepository.findByPriceBetween(
                new BigDecimal("100.00"), new BigDecimal("200.00"));
        assertThat(productsInRange).hasSize(1);
        assertThat(productsInRange.get(0).getName()).isEqualTo("Produit Moyen");
    }
}

