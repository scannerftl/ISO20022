#!/bin/bash

echo "========================================"
echo "Démarrage de Console Log API"
echo "========================================"
echo ""

# Vérifier si Maven est installé
if ! command -v mvn &> /dev/null
then
    echo "[ERREUR] Maven n'est pas installé ou n'est pas dans le PATH"
    echo "Veuillez installer Maven: https://maven.apache.org/download.cgi"
    exit 1
fi

echo "[INFO] Compilation et démarrage de l'application..."
echo ""

# Démarrer l'application Spring Boot
mvn spring-boot:run
