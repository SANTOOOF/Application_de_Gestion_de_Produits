#  Guide de Démarrage Rapide

## Démarrage en 3 étapes

### 1. Prérequis
- Java 11+ installé
- Maven 3.6+ installé

### 2. Démarrage automatique
```bash
./start.sh
```

### 3. Accès à l'application
- **Application** : http://localhost:8080
- **Base de données** : http://localhost:8080/h2-console

## 👥 Comptes de test

| Utilisateur | Mot de passe | Rôle |
|-------------|--------------|------|
| `admin` | `admin123` | Administrateur |
| `manager` | `manager123` | Gestionnaire |
| `user` | `user123` | Utilisateur |
| `youssef` | `youssef123` | Créateur (Tous droits) |

## 🔧 Démarrage manuel

Si le script automatique ne fonctionne pas :

```bash
# 1. Compiler
mvn clean compile

# 2. Démarrer
mvn spring-boot:run
```

## 📱 Fonctionnalités à tester

1. **Connexion** : Utilisez un des comptes ci-dessus
2. **Gestion des produits** : Ajout, modification, suppression
3. **Recherche** : Testez la recherche par nom ou catégorie
4. **Administration** : Connectez-vous en tant qu'admin pour voir le tableau de bord
5. **API REST** : Testez les endpoints `/api/products`

## 🆘 Problèmes courants

### Port 8080 occupé
```bash
# Changer le port dans application.properties
server.port=8081
```

### Erreur de compilation
```bash
# Nettoyer et recompiler
mvn clean install -DskipTests
```

### Base de données H2
- **URL JDBC** : `jdbc:h2:mem:testdb`
- **Utilisateur** : `sa`
- **Mot de passe** : (vide)

---

**Créé par Youssef Rahli** 🎯

