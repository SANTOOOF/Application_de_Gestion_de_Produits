# Configuration IntelliJ IDEA Ultimate

## 🎯 Configuration Recommandée pour IntelliJ IDEA Ultimate

### 1. Import du Projet

1. **Ouvrir IntelliJ IDEA Ultimate**
2. **File** → **Open** → Sélectionner le dossier `product-management-app`
3. **Import as Maven Project** → Cliquer sur **Import**
4. Attendre l'indexation et le téléchargement des dépendances

### 2. Configuration du SDK

1. **File** → **Project Structure** (Ctrl+Alt+Shift+S)
2. **Project Settings** → **Project**
3. **Project SDK** : Sélectionner Java 11
4. **Project Language Level** : 11 - Local variable syntax for lambda parameters

### 3. Configuration de Maven

1. **File** → **Settings** (Ctrl+Alt+S)
2. **Build, Execution, Deployment** → **Build Tools** → **Maven**
3. **Maven home directory** : Utiliser le Maven intégré ou pointer vers votre installation
4. **User settings file** : Laisser par défaut ou pointer vers votre settings.xml

### 4. Configuration de Spring Boot

1. **File** → **Settings** → **Plugins**
2. Vérifier que **Spring Boot** est activé
3. **File** → **Settings** → **Build, Execution, Deployment** → **Application Servers**
4. Ajouter **Spring Boot** si nécessaire

### 5. Configuration de la Base de Données

1. **View** → **Tool Windows** → **Database**
2. **+** → **Data Source** → **H2**
3. **Configuration** :
   - **URL** : `jdbc:h2:mem:testdb`
   - **User** : `sa`
   - **Password** : (laisser vide)
4. **Test Connection** → **OK**

### 6. Configuration de Lombok

1. **File** → **Settings** → **Plugins**
2. Installer **Lombok Plugin** si pas déjà installé
3. **File** → **Settings** → **Build, Execution, Deployment** → **Compiler** → **Annotation Processors**
4. Cocher **Enable annotation processing**

### 7. Configuration de Thymeleaf

1. **File** → **Settings** → **Plugins**
2. Vérifier que **Thymeleaf** est activé
3. **File** → **Settings** → **Languages & Frameworks** → **Template Data Languages**
4. Ajouter le dossier `src/main/resources/templates` avec **Thymeleaf**

## 🚀 Configurations de Lancement

### Configuration Spring Boot

1. **Run** → **Edit Configurations**
2. **+** → **Spring Boot**
3. **Configuration** :
   - **Name** : `ProductManagementApplication`
   - **Main class** : `com.youssef.productmanagement.ProductManagementApplication`
   - **Use classpath of module** : `product-management`
   - **JRE** : Java 11

### Configuration Maven

1. **Run** → **Edit Configurations**
2. **+** → **Maven**
3. **Configuration** :
   - **Name** : `Maven Clean Compile`
   - **Command line** : `clean compile`
   - **Working directory** : `$PROJECT_DIR$`

### Configuration de Debug

1. **Run** → **Edit Configurations**
2. Dupliquer la configuration Spring Boot
3. **Name** : `ProductManagementApplication (Debug)`
4. Utiliser le mode Debug pour le développement

## 🔧 Plugins Recommandés

### Plugins Essentiels
- **Spring Boot** (inclus)
- **Lombok** 
- **Thymeleaf**
- **Database Tools and SQL** (inclus)

### Plugins Utiles
- **GitToolBox** - Améliorations Git
- **Rainbow Brackets** - Coloration des parenthèses
- **SonarLint** - Analyse de code
- **Maven Helper** - Outils Maven avancés

## 🎨 Configuration de l'Interface

### Thème et Apparence
1. **File** → **Settings** → **Appearance & Behavior** → **Appearance**
2. **Theme** : Darcula (recommandé) ou IntelliJ Light
3. **Font** : JetBrains Mono (recommandé)

### Éditeur de Code
1. **File** → **Settings** → **Editor** → **Font**
2. **Font** : JetBrains Mono
3. **Size** : 14
4. **Line spacing** : 1.2

## 🔍 Outils de Développement

### Live Reload
1. **File** → **Settings** → **Build, Execution, Deployment** → **Compiler**
2. Cocher **Build project automatically**
3. **Help** → **Find Action** → Taper "Registry"
4. Cocher `compiler.automake.allow.when.app.running`

### Formatage de Code
1. **File** → **Settings** → **Editor** → **Code Style** → **Java**
2. Importer le style Google Java ou utiliser le style par défaut
3. **Ctrl+Alt+L** pour formater le code

### Raccourcis Utiles

| Action | Raccourci |
|--------|-----------|
| Lancer l'application | Shift+F10 |
| Debug | Shift+F9 |
| Formater le code | Ctrl+Alt+L |
| Optimiser les imports | Ctrl+Alt+O |
| Recherche globale | Ctrl+Shift+F |
| Refactoring | Ctrl+Alt+Shift+T |

## 🧪 Configuration des Tests

### JUnit Configuration
1. **Run** → **Edit Configurations**
2. **+** → **JUnit**
3. **Test kind** : All in package
4. **Package** : `com.youssef.productmanagement`

### Coverage
1. **Run** → **Run with Coverage**
2. Voir les résultats dans l'onglet Coverage

## 🌐 Configuration Web

### Serveur de Développement
- **URL** : http://localhost:8080
- **Hot Reload** : Activé avec Spring Boot DevTools

### Browser Configuration
1. **File** → **Settings** → **Tools** → **Web Browsers**
2. Configurer votre navigateur par défaut
3. **Ctrl+Shift+F12** pour ouvrir dans le navigateur

## 📊 Monitoring et Profiling

### JVM Monitoring
1. **View** → **Tool Windows** → **Profiler**
2. Attacher au processus Spring Boot
3. Analyser les performances

### Database Console
1. **View** → **Tool Windows** → **Database**
2. Exécuter des requêtes SQL
3. Voir les données en temps réel

## 🔧 Dépannage

### Problèmes Courants

1. **Lombok ne fonctionne pas**
   - Vérifier que le plugin est installé
   - Redémarrer IntelliJ
   - Rebuild le projet

2. **Spring Boot ne démarre pas**
   - Vérifier la configuration Java
   - Nettoyer et recompiler avec Maven
   - Vérifier les logs dans la console

3. **Thymeleaf non reconnu**
   - Vérifier la configuration du plugin
   - Marquer le dossier templates comme Resources

### Commandes Utiles

```bash
# Nettoyer le projet
mvn clean

# Recompiler
mvn compile

# Lancer les tests
mvn test

# Générer le JAR
mvn package
```

---

**Configuration optimisée pour IntelliJ IDEA Ultimate**
**Projet créé par Youssef Rahli** 🎯

