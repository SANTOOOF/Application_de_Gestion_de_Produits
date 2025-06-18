#!/bin/bash

# Script de démarrage rapide pour l'Application de Gestion de Produits
# Créé par : Youssef Rahli

echo "🚀 Application de Gestion de Produits - Démarrage"
echo "=================================================="
echo ""

# Vérification de Java
echo "🔍 Vérification de Java..."
if command -v java &> /dev/null; then
    JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
    echo "✅ Java détecté : $JAVA_VERSION"
else
    echo "❌ Java n'est pas installé. Veuillez installer Java 11 ou supérieur."
    exit 1
fi

# Vérification de Maven
echo "🔍 Vérification de Maven..."
if command -v mvn &> /dev/null; then
    MVN_VERSION=$(mvn -version | head -n 1)
    echo "✅ Maven détecté : $MVN_VERSION"
else
    echo "❌ Maven n'est pas installé. Veuillez installer Maven 3.6 ou supérieur."
    exit 1
fi

echo ""
echo "🔧 Compilation du projet..."
mvn clean compile -q

if [ $? -eq 0 ]; then
    echo "✅ Compilation réussie !"
else
    echo "❌ Erreur lors de la compilation. Vérifiez les logs ci-dessus."
    exit 1
fi

echo ""
echo "🌱 Démarrage de l'application..."
echo "📍 L'application sera accessible sur : http://localhost:8080"
echo "🗄️  Console H2 disponible sur : http://localhost:8080/h2-console"
echo ""
echo "👥 Comptes de démonstration :"
echo "   - admin / admin123 (Administrateur)"
echo "   - manager / manager123 (Gestionnaire)"
echo "   - user / user123 (Utilisateur)"
echo "   - youssef / youssef123 (Créateur - Tous droits)"
echo ""
echo "⏹️  Pour arrêter l'application, utilisez Ctrl+C"
echo ""

# Démarrage de l'application
mvn spring-boot:run

