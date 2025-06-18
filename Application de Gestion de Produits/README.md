# Application de Gestion de Produits - Spring Boot

## 📋 Description du Projet

Cette application Web JEE complète permet la gestion de produits avec toutes les fonctionnalités modernes d'une application d'entreprise. Développée avec Spring Boot, elle intègre Spring Data JPA, Hibernate, Thymeleaf et Spring Security pour offrir une solution robuste et sécurisée.

**Créé par : Youssef Rahli**

## 🚀 Fonctionnalités Principales

### ✅ Fonctionnalités Implémentées

1. **Gestion des Produits**
   - ✅ Affichage de la liste des produits avec pagination
   - ✅ Recherche avancée par nom, catégorie, prix
   - ✅ Ajout de nouveaux produits avec validation
   - ✅ Modification des produits existants
   - ✅ Suppression de produits
   - ✅ Gestion des stocks et disponibilité

2. **Authentification et Sécurité**
   - ✅ Système de connexion/déconnexion
   - ✅ Inscription de nouveaux utilisateurs
   - ✅ Gestion des rôles (ADMIN, MANAGER, USER)
   - ✅ Protection des pages selon les rôles
   - ✅ Chiffrement des mots de passe

3. **Interface Utilisateur**
   - ✅ Design responsive avec Bootstrap 5
   - ✅ Templates Thymeleaf avec layout
   - ✅ Interface moderne et intuitive
   - ✅ Validation côté client et serveur

4. **Administration**
   - ✅ Tableau de bord administrateur
   - ✅ Statistiques en temps réel
   - ✅ Graphiques interactifs
   - ✅ Gestion des utilisateurs

5. **API REST**
   - ✅ Endpoints CRUD complets
   - ✅ Pagination et filtrage
   - ✅ Documentation API
   - ✅ Support CORS

## 🛠️ Technologies Utilisées

- **Framework Principal** : Spring Boot 2.7.18
- **Base de Données** : H2 (développement) / MySQL (production)
- **ORM** : Hibernate / Spring Data JPA
- **Sécurité** : Spring Security
- **Templates** : Thymeleaf + Thymeleaf Layout Dialect
- **Frontend** : Bootstrap 5, Chart.js
- **Build Tool** : Maven
- **Java Version** : 11

## 📁 Structure du Projet

```
src/
├── main/
│   ├── java/com/youssef/productmanagement/
│   │   ├── ProductManagementApplication.java
│   │   ├── config/
│   │   │   ├── SecurityConfig.java
│   │   │   └── DataInitializer.java
│   │   ├── controller/
│   │   │   ├── HomeController.java
│   │   │   ├── ProductController.java
│   │   │   ├── AuthController.java
│   │   │   ├── AdminController.java
│   │   │   └── api/ProductApiController.java
│   │   ├── entity/
│   │   │   ├── Product.java
│   │   │   └── User.java
│   │   ├── repository/
│   │   │   ├── ProductRepository.java
│   │   │   └── UserRepository.java
│   │   └── service/
│   │       ├── ProductService.java
│   │       ├── UserService.java
│   │       └── CustomUserDetailsService.java
│   └── resources/
│       ├── templates/
│       │   ├── layout.html
│       │   ├── auth/ (login.html, register.html)
│       │   ├── products/ (list.html, form.html, detail.html)
│       │   └── admin/ (dashboard.html)
│       └── application.properties
└── test/
    └── java/com/youssef/productmanagement/
        └── repository/ProductRepositoryTest.java
```

## 🔧 Installation et Configuration

### Prérequis
- Java 11 ou supérieur
- Maven 3.6+
- MySQL (optionnel, H2 par défaut)

### Étapes d'installation

1. **Cloner le projet**
```bash
git clone <repository-url>
cd product-management-app
```

2. **Compiler le projet**
```bash
mvn clean compile
```

3. **Lancer l'application**
```bash
mvn spring-boot:run
```

4. **Accéder à l'application**
- URL : http://localhost:8080
- Console H2 : http://localhost:8080/h2-console

## 👥 Comptes de Démonstration

| Utilisateur | Mot de passe | Rôle | Description |
|-------------|--------------|------|-------------|
| admin | admin123 | ADMIN | Accès complet |
| manager | manager123 | MANAGER | Gestion produits |
| user | user123 | USER | Consultation |
| youssef | youssef123 | ADMIN+MANAGER | Créateur du projet |

## 🌐 API REST

### Endpoints Principaux

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/products` | Liste des produits (avec pagination) |
| GET | `/api/products/{id}` | Détail d'un produit |
| POST | `/api/products` | Créer un produit |
| PUT | `/api/products/{id}` | Modifier un produit |
| DELETE | `/api/products/{id}` | Supprimer un produit |
| GET | `/api/products/search?keyword=...` | Rechercher des produits |
| GET | `/api/products/categories` | Liste des catégories |

### Exemple d'utilisation

```bash
# Obtenir tous les produits
curl -X GET "http://localhost:8080/api/products" \
  -H "Authorization: Basic dXNlcjp1c2VyMTIz"

# Créer un nouveau produit
curl -X POST "http://localhost:8080/api/products" \
  -H "Content-Type: application/json" \
  -H "Authorization: Basic YWRtaW46YWRtaW4xMjM=" \
  -d '{
    "name": "Nouveau Produit",
    "description": "Description du produit",
    "price": 99.99,
    "quantity": 10,
    "category": "Électronique",
    "isAvailable": true
  }'
```

## 🔒 Sécurité

### Configuration de Sécurité

- **Authentification** : Form-based login
- **Autorisation** : Role-based access control
- **Chiffrement** : BCrypt pour les mots de passe
- **Session** : Gestion des sessions Spring Security
- **CSRF** : Protection activée (sauf pour H2 Console)

### Règles d'Accès

| URL Pattern | Rôles Requis | Description |
|-------------|--------------|-------------|
| `/login`, `/register` | Public | Pages d'authentification |
| `/products/**` | USER+ | Consultation des produits |
| `/products/new`, `/products/*/edit` | MANAGER+ | Gestion des produits |
| `/admin/**` | ADMIN | Administration |
| `/api/**` | USER+ | API REST |

## 📊 Fonctionnalités Avancées

### Tableau de Bord Administrateur
- Statistiques en temps réel
- Graphiques interactifs (Chart.js)
- Alertes de stock faible
- Analyse par catégorie

### Recherche et Filtrage
- Recherche textuelle multi-critères
- Filtres par catégorie, prix, disponibilité
- Pagination intelligente
- Tri personnalisable

### Validation des Données
- Validation côté serveur (Bean Validation)
- Validation côté client (JavaScript)
- Messages d'erreur contextuels
- Indicateurs visuels

## 🧪 Tests

### Tests Unitaires
```bash
mvn test
```

### Tests d'Intégration
```bash
mvn verify
```

### Couverture de Code
```bash
mvn jacoco:report
```

## 📈 Performance et Optimisation

- **Pagination** : Évite le chargement de grandes listes
- **Lazy Loading** : Chargement différé des relations
- **Cache** : Mise en cache des requêtes fréquentes
- **Indexation** : Index sur les colonnes de recherche

## 🚀 Déploiement

### Profils d'Environnement

1. **Développement** (par défaut)
   - Base de données H2 en mémoire
   - Logs détaillés
   - Hot reload activé

2. **Production**
```bash
mvn spring-boot:run -Dspring.profiles.active=prod
```

### Configuration MySQL (Production)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/product_management
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

## 📝 Logs et Monitoring

- **Logs** : Logback avec rotation automatique
- **Métriques** : Spring Boot Actuator
- **Santé** : Health checks intégrés

## 🔄 Évolutions Futures

### Fonctionnalités Prévues
- [ ] Upload d'images pour les produits
- [ ] Système de notifications
- [ ] Export/Import Excel
- [ ] Historique des modifications
- [ ] API GraphQL
- [ ] Tests automatisés complets

### Améliorations Techniques
- [ ] Migration vers Spring Boot 3.x
- [ ] Containerisation Docker
- [ ] CI/CD Pipeline
- [ ] Monitoring avancé

## 🤝 Contribution

Pour contribuer au projet :

1. Fork le repository
2. Créer une branche feature (`git checkout -b feature/nouvelle-fonctionnalite`)
3. Commit les changements (`git commit -am 'Ajout nouvelle fonctionnalité'`)
4. Push vers la branche (`git push origin feature/nouvelle-fonctionnalite`)
5. Créer une Pull Request

## 📞 Support

Pour toute question ou support :
- **Créateur** : Youssef Rahli
- **Email** : youssef.rahli@example.com
- **Documentation** : Voir ce README

## 📄 Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.

---

**Développé avec ❤️ par Youssef Rahli**

*Application de Gestion de Produits - Version 1.0.0*

