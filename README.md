# Console Log API - Projet Spring Boot Complet

[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.18-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-Proprietary-red.svg)]()

API REST complète pour la gestion des logs de console ISO20022 avec Spring Boot.

## 🚀 Démarrage Rapide

### Prérequis
- Java 11 ou supérieur
- Maven 3.6 ou supérieur

### Lancer l'application

**Windows:**
```bash
start.bat
```

**Linux/Mac:**
```bash
chmod +x start.sh
./start.sh
```

**Ou avec Maven:**
```bash
mvn spring-boot:run
```

### Accès rapide
- **API**: http://localhost:8080/api/log-console-items
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **H2 Console**: http://localhost:8080/h2-console

## 📋 Fonctionnalités

✅ **CRUD complet** pour LogConsoleItem avec toutes ses relations  
✅ **5 endpoints de recherche** avancée  
✅ **Documentation Swagger** interactive  
✅ **Base H2** pour le développement (zéro configuration)  
✅ **Support PostgreSQL** pour la production  
✅ **Gestion des exceptions** centralisée  
✅ **Scripts de test** automatisés  
✅ **Collection Postman** incluse  

## 📊 Endpoints Principaux

### CRUD
- `POST /api/log-console-items` - Créer
- `GET /api/log-console-items` - Lister tous
- `GET /api/log-console-items/{id}` - Récupérer par ID
- `PUT /api/log-console-items/{id}` - Mettre à jour
- `DELETE /api/log-console-items/{id}` - Supprimer

### Recherches
- `GET /api/log-console-items/search/by-entity-type?type=QUEU_IN`
- `GET /api/log-console-items/search/by-reference?reference=MSG001`
- `GET /api/log-console-items/search/by-type-message?type=PACS008`
- `GET /api/log-console-items/search/by-date-session?date=2024-10-22`
- `GET /api/log-console-items/search/by-bic?code=BICFRCM01`

## 🧪 Tests

### Test rapide avec curl
```bash
curl -X POST http://localhost:8080/api/log-console-items \
  -H "Content-Type: application/json" \
  -d @test-data/create_simple.json
```

### Tests automatisés
```bash
cd test-data
# Windows: test_api.bat
# Linux/Mac: ./test_api.sh
```

### Postman
Importer `test-data/LogConsoleItem_API.postman_collection.json`

## 📁 Structure du Projet

```
console/
├── src/main/java/
│   └── com/megatim/iso20022/share/
│       ├── ConsoleLogApplication.java      # Point d'entrée
│       ├── config/                         # Configuration
│       ├── exception/                      # Gestion erreurs
│       └── model/console/
│           ├── [Entités JPA]
│           ├── controller/                 # REST Controllers
│           ├── service/                    # Logique métier
│           ├── repository/                 # Accès données
│           ├── dto/                        # DTOs
│           └── mapper/                     # Conversions
├── src/main/resources/
│   ├── application.yml                     # Config principale
│   ├── application-dev.yml                 # Config dev
│   └── application-prod.yml                # Config prod
├── test-data/                              # Exemples JSON
├── pom.xml                                 # Maven
└── start.bat / start.sh                    # Scripts démarrage
```

## 🗄️ Base de Données

### Développement (H2 - Par défaut)
Aucune configuration requise. Base en mémoire.

**Console H2**: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:consoledb`
- Username: `sa`
- Password: *(vide)*

### Production (PostgreSQL)
1. Créer la base: `CREATE DATABASE console_db;`
2. Configurer `application-prod.yml`
3. Démarrer: `mvn spring-boot:run -Dspring-boot.run.profiles=prod`

## 📚 Documentation

| Fichier | Description |
|---------|-------------|
| **GUIDE_DEMARRAGE_COMPLET.md** | 📖 Guide complet de démarrage |
| **README_SPRING_BOOT.md** | ⚙️ Configuration Spring Boot |
| **POSTMAN_EXAMPLES.md** | 🔍 Exemples d'API détaillés |
| **README_CRUD_API.md** | 🏗️ Architecture et design |
| **QUICK_START.md** | ⚡ Démarrage en 5 minutes |

## 🔧 Technologies

- **Spring Boot 2.7.18** - Framework principal
- **Spring Data JPA** - Persistance
- **H2 Database** - Base de développement
- **PostgreSQL** - Base de production
- **Lombok** - Réduction du boilerplate
- **Springdoc OpenAPI** - Documentation Swagger
- **Spring Boot Actuator** - Monitoring

## 🐛 Dépannage

### Port déjà utilisé
```bash
java -jar target/console-log-api-1.0.0.jar --server.port=9090
```

### Logs détaillés
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--logging.level.root=DEBUG"
```

## 📦 Build

```bash
# Build
mvn clean package

# Exécution
java -jar target/console-log-api-1.0.0.jar
```

## 🎯 Exemple Rapide

```bash
# Créer un item
curl -X POST http://localhost:8080/api/log-console-items \
  -H "Content-Type: application/json" \
  -d '{
    "etapeTraitement": "TRAITEMENT_INITIAL",
    "typeMessage": "PACS008",
    "referenceMessage": "MSG001",
    "montantTotal": "100000.00",
    "devise": "XAF",
    "logConsoleEntityType": "QUEU_IN"
  }'

# Récupérer tous
curl http://localhost:8080/api/log-console-items

# Récupérer par ID
curl http://localhost:8080/api/log-console-items/1
```

## ✅ Checklist

- [ ] Java 11+ installé
- [ ] Maven 3.6+ installé
- [ ] Application démarrée
- [ ] Swagger accessible
- [ ] Premier test réussi

## 📞 Support

Consultez la documentation complète dans les fichiers `README_*.md` et `GUIDE_DEMARRAGE_COMPLET.md`.


**Développé avec ❤️ pour la gestion des logs console ISO20022**
