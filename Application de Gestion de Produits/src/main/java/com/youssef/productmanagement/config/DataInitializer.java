package com.youssef.productmanagement.config;

import com.youssef.productmanagement.entity.Product;
import com.youssef.productmanagement.entity.User;
import com.youssef.productmanagement.repository.ProductRepository;
import com.youssef.productmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            initializeUsers();
        }
        
        if (productRepository.count() == 0) {
            initializeProducts();
        }
    }
    
    private void initializeUsers() {
        // Utilisateur Admin
        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@example.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setFirstName("Admin");
        admin.setLastName("Système");
        admin.setRoles(Set.of(User.Role.ADMIN));
        admin.setEnabled(true);
        admin.setAccountNonExpired(true);
        admin.setAccountNonLocked(true);
        admin.setCredentialsNonExpired(true);
        
        // Utilisateur Manager
        User manager = new User();
        manager.setUsername("manager");
        manager.setEmail("manager@example.com");
        manager.setPassword(passwordEncoder.encode("manager123"));
        manager.setFirstName("Manager");
        manager.setLastName("Produits");
        manager.setRoles(Set.of(User.Role.MANAGER));
        manager.setEnabled(true);
        manager.setAccountNonExpired(true);
        manager.setAccountNonLocked(true);
        manager.setCredentialsNonExpired(true);
        
        // Utilisateur standard
        User user = new User();
        user.setUsername("user");
        user.setEmail("user@example.com");
        user.setPassword(passwordEncoder.encode("user123"));
        user.setFirstName("Utilisateur");
        user.setLastName("Standard");
        user.setRoles(Set.of(User.Role.USER));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        
        // Youssef Rahli - Créateur du projet
        User youssef = new User();
        youssef.setUsername("youssef");
        youssef.setEmail("youssef.rahli@example.com");
        youssef.setPassword(passwordEncoder.encode("youssef123"));
        youssef.setFirstName("Youssef");
        youssef.setLastName("Rahli");
        youssef.setRoles(Set.of(User.Role.ADMIN, User.Role.MANAGER));
        youssef.setEnabled(true);
        youssef.setAccountNonExpired(true);
        youssef.setAccountNonLocked(true);
        youssef.setCredentialsNonExpired(true);
        
        userRepository.save(admin);
        userRepository.save(manager);
        userRepository.save(user);
        userRepository.save(youssef);
        
        System.out.println("Utilisateurs de démonstration créés avec succès !");
    }
    
    private void initializeProducts() {
        // Produits électroniques
        Product laptop = new Product();
        laptop.setName("Ordinateur Portable HP");
        laptop.setDescription("Ordinateur portable HP avec processeur Intel i7, 16GB RAM, 512GB SSD");
        laptop.setPrice(new BigDecimal("899.99"));
        laptop.setQuantity(15);
        laptop.setCategory("Électronique");
        laptop.setImageUrl("https://via.placeholder.com/300x200?text=Laptop");
        laptop.setIsAvailable(true);
        
        Product smartphone = new Product();
        smartphone.setName("Smartphone Samsung Galaxy");
        smartphone.setDescription("Smartphone Samsung Galaxy avec écran AMOLED 6.5 pouces, 128GB");
        smartphone.setPrice(new BigDecimal("649.99"));
        smartphone.setQuantity(25);
        smartphone.setCategory("Électronique");
        smartphone.setImageUrl("https://via.placeholder.com/300x200?text=Smartphone");
        smartphone.setIsAvailable(true);
        
        Product tablet = new Product();
        tablet.setName("Tablette iPad Air");
        tablet.setDescription("Tablette Apple iPad Air avec écran 10.9 pouces, 64GB WiFi");
        tablet.setPrice(new BigDecimal("599.99"));
        tablet.setQuantity(10);
        tablet.setCategory("Électronique");
        tablet.setImageUrl("https://via.placeholder.com/300x200?text=Tablet");
        tablet.setIsAvailable(true);
        
        // Produits de mode
        Product tshirt = new Product();
        tshirt.setName("T-shirt Coton Bio");
        tshirt.setDescription("T-shirt en coton biologique, disponible en plusieurs couleurs");
        tshirt.setPrice(new BigDecimal("29.99"));
        tshirt.setQuantity(50);
        tshirt.setCategory("Vêtements");
        tshirt.setImageUrl("https://via.placeholder.com/300x200?text=T-shirt");
        tshirt.setIsAvailable(true);
        
        Product jeans = new Product();
        jeans.setName("Jean Slim Fit");
        jeans.setDescription("Jean slim fit en denim stretch, coupe moderne");
        jeans.setPrice(new BigDecimal("79.99"));
        jeans.setQuantity(30);
        jeans.setCategory("Vêtements");
        jeans.setImageUrl("https://via.placeholder.com/300x200?text=Jeans");
        jeans.setIsAvailable(true);
        
        // Produits de maison
        Product canape = new Product();
        canape.setName("Canapé 3 Places");
        canape.setDescription("Canapé confortable 3 places en tissu gris, design moderne");
        canape.setPrice(new BigDecimal("799.99"));
        canape.setQuantity(5);
        canape.setCategory("Mobilier");
        canape.setImageUrl("https://via.placeholder.com/300x200?text=Canape");
        canape.setIsAvailable(true);
        
        Product table = new Product();
        table.setName("Table Basse en Bois");
        table.setDescription("Table basse en bois massif, style scandinave");
        table.setPrice(new BigDecimal("299.99"));
        table.setQuantity(8);
        table.setCategory("Mobilier");
        table.setImageUrl("https://via.placeholder.com/300x200?text=Table");
        table.setIsAvailable(true);
        
        // Produits de sport
        Product velo = new Product();
        velo.setName("Vélo VTT 26 pouces");
        velo.setDescription("Vélo tout terrain 26 pouces, 21 vitesses, freins à disque");
        velo.setPrice(new BigDecimal("449.99"));
        velo.setQuantity(12);
        velo.setCategory("Sport");
        velo.setImageUrl("https://via.placeholder.com/300x200?text=Velo");
        velo.setIsAvailable(true);
        
        Product ballon = new Product();
        ballon.setName("Ballon de Football");
        ballon.setDescription("Ballon de football officiel, taille 5, cuir synthétique");
        ballon.setPrice(new BigDecimal("24.99"));
        ballon.setQuantity(40);
        ballon.setCategory("Sport");
        ballon.setImageUrl("https://via.placeholder.com/300x200?text=Ballon");
        ballon.setIsAvailable(true);
        
        // Produit en rupture de stock
        Product produitRupture = new Product();
        produitRupture.setName("Produit en Rupture");
        produitRupture.setDescription("Ce produit est temporairement en rupture de stock");
        produitRupture.setPrice(new BigDecimal("99.99"));
        produitRupture.setQuantity(0);
        produitRupture.setCategory("Divers");
        produitRupture.setImageUrl("https://via.placeholder.com/300x200?text=Rupture");
        produitRupture.setIsAvailable(false);
        
        // Sauvegarder tous les produits
        productRepository.save(laptop);
        productRepository.save(smartphone);
        productRepository.save(tablet);
        productRepository.save(tshirt);
        productRepository.save(jeans);
        productRepository.save(canape);
        productRepository.save(table);
        productRepository.save(velo);
        productRepository.save(ballon);
        productRepository.save(produitRupture);
        
        System.out.println("Données de test initialisées avec succès !");
    }
}

