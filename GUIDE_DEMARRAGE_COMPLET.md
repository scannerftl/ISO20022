# 🚀 Guide de Démarrage Complet - Console Log API

## ✅ Projet Spring Boot Prêt à Démarrer

Vous disposez maintenant d'un projet Spring Boot **100% fonctionnel** avec :
- ✅ Configuration Maven complète
- ✅ Base de données H2 (développement) et PostgreSQL (production)
- ✅ API REST complète avec CRUD
- ✅ Documentation Swagger intégrée
- ✅ Gestion des exceptions
- ✅ Scripts de démarrage
- ✅ Exemples de test JSON

---

## 📋 Étapes pour démarrer

### 1️⃣ Vérifier les prérequis

```bash
# Vérifier Java (version 11 minimum requise)
java -version

# Vérifier Maven
mvn -version
```

**Si non installés:**
- Java 11+: https://adoptium.net/
- Maven 3.6+: https://maven.apache.org/download.cgi

### 2️⃣ Démarrer l'application

**Option A - Avec le script (Recommandé)**
```bash
# Windows
start.bat

# Linux/Mac
chmod +x start.sh
./start.sh
```

**Option B - Avec Maven directement**
```bash
mvn spring-boot:run
```

**Option C - Build puis exécution**
```bash
# Build
mvn clean package

# Exécution
java -jar target/console-log-api-1.0.0.jar
```

### 3️⃣ Vérifier que l'application fonctionne

Une fois démarrée, vous verrez ce message dans la console:
```
========================================
✅ Console Log API démarrée avec succès!
========================================
📍 URL de l'API: http://localhost:8080/api/log-console-items
📖 Swagger UI: http://localhost:8080/swagger-ui.html
📊 Actuator: http://localhost:8080/actuator/health
🗄️  Console H2: http://localhost:8080/h2-console
========================================
```

### 4️⃣ Tester l'API

**Méthode 1: Swagger UI (Le plus simple)**
1. Ouvrir http://localhost:8080/swagger-ui.html
2. Cliquer sur "LogConsoleItem"
3. Tester les endpoints directement

**Méthode 2: curl**
```bash
# Créer un item
curl -X POST http://localhost:8080/api/log-console-items \
  -H "Content-Type: application/json" \
  -d "{\"etapeTraitement\":\"TEST\",\"typeMessage\":\"PACS008\",\"referenceMessage\":\"MSG001\",\"montantTotal\":\"100000.00\",\"devise\":\"XAF\",\"logConsoleEntityType\":\"QUEU_IN\"}"

# Récupérer tous les items
curl http://localhost:8080/api/log-console-items

# Récupérer l'item ID=1
curl http://localhost:8080/api/log-console-items/1
```

**Méthode 3: Postman**
1. Importer `test-data/LogConsoleItem_API.postman_collection.json`
2. Exécuter les requêtes

**Méthode 4: Scripts de test automatisés**
```bash
cd test-data

# Windows
test_api.bat

# Linux/Mac
chmod +x test_api.sh
./test_api.sh
```

---

## 🗄️ Configuration de la base de données

### Mode Développement (Par défaut - H2)

L'application démarre automatiquement avec H2 en mémoire. **Aucune configuration nécessaire !**

**Accès à la console H2:**
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:consoledb`
- Username: `sa`
- Password: *(laisser vide)*

### Mode Production (PostgreSQL)

1. **Installer PostgreSQL** (si pas déjà fait)

2. **Créer la base de données**
```sql
CREATE DATABASE console_db;
```

3. **Modifier les credentials dans `src/main/resources/application-prod.yml`**
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/console_db
    username: votre_username
    password: votre_password
```

4. **Démarrer avec le profil production**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

---

## 📊 Endpoints disponibles

### CRUD de base
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/log-console-items` | Créer un item |
| GET | `/api/log-console-items` | Lister tous |
| GET | `/api/log-console-items/{id}` | Récupérer par ID |
| PUT | `/api/log-console-items/{id}` | Mettre à jour |
| DELETE | `/api/log-console-items/{id}` | Supprimer |

### Recherches avancées
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/log-console-items/search/by-entity-type?type=QUEU_IN` | Par type d'entité |
| GET | `/api/log-console-items/search/by-reference?reference=MSG001` | Par référence |
| GET | `/api/log-console-items/search/by-type-message?type=PACS008` | Par type de message |
| GET | `/api/log-console-items/search/by-date-session?date=2024-10-22` | Par date |
| GET | `/api/log-console-items/search/by-bic?code=BICFRCM01` | Par code BIC |

---

## 📁 Structure du projet créé

```
console/
├── pom.xml                                    ✅ Configuration Maven
├── start.bat                                  ✅ Script démarrage Windows
├── start.sh                                   ✅ Script démarrage Linux/Mac
├── build.bat                                  ✅ Script de build
├── .gitignore                                 ✅ Fichiers à ignorer
│
├── src/main/
│   ├── java/com/megatim/iso20022/
│   │   ├── share/
│   │   │   ├── ConsoleLogApplication.java     ✅ Classe principale
│   │   │   ├── config/
│   │   │   │   └── OpenApiConfig.java         ✅ Config Swagger
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java ✅ Gestion erreurs
│   │   │   │   └── ResourceNotFoundException.java
│   │   │   └── model/console/
│   │   │       ├── LogConsoleItem.java        ✅ Entité principale
│   │   │       ├── LogConsoleDetails.java
│   │   │       ├── LogConsoleDetailsIdentification.java
│   │   │       ├── LogConsoleItemInfo.java
│   │   │       ├── LogConsoleEntityType.java
│   │   │       ├── controller/
│   │   │       │   └── LogConsoleItemController.java ✅ REST Controller
│   │   │       ├── service/
│   │   │       │   └── LogConsoleItemService.java ✅ Logique métier
│   │   │       ├── repository/
│   │   │       │   └── LogConsoleItemRepository.java ✅ Accès BD
│   │   │       ├── dto/
│   │   │       │   └── [4 DTOs]               ✅ Objets de transfert
│   │   │       └── mapper/
│   │   │           └── LogConsoleItemMapper.java ✅ Conversions
│   │   └── systac/model/enumeration/
│   │       └── VersionMessage.java            ✅ Enum
│   │
│   └── resources/
│       ├── application.yml                    ✅ Config principale
│       ├── application-dev.yml                ✅ Config dev
│       ├── application-prod.yml               ✅ Config prod
│       └── banner.txt                         ✅ Banner personnalisé
│
├── test-data/                                 ✅ Fichiers de test
│   ├── create_simple.json
│   ├── create_complete.json
│   ├── create_error_example.json
│   ├── update_example.json
│   ├── test_api.sh
│   ├── test_api.bat
│   └── LogConsoleItem_API.postman_collection.json
│
└── Documentation/
    ├── README_SPRING_BOOT.md                  ✅ Guide Spring Boot
    ├── POSTMAN_EXAMPLES.md                    ✅ Exemples API
    ├── README_CRUD_API.md                     ✅ Architecture
    ├── QUICK_START.md                         ✅ Démarrage rapide
    └── GUIDE_DEMARRAGE_COMPLET.md            ✅ Ce fichier
```

---

## 🎯 Exemples de test rapides

### Créer un LogConsoleItem simple
```bash
curl -X POST http://localhost:8080/api/log-console-items \
  -H "Content-Type: application/json" \
  -d @test-data/create_simple.json
```

### Créer avec toutes les relations
```bash
curl -X POST http://localhost:8080/api/log-console-items \
  -H "Content-Type: application/json" \
  -d @test-data/create_complete.json
```

### Récupérer tous les items
```bash
curl http://localhost:8080/api/log-console-items
```

### Rechercher par type
```bash
curl "http://localhost:8080/api/log-console-items/search/by-entity-type?type=QUEU_IN"
```

---

## 🔧 Configuration avancée

### Changer le port
```bash
# Dans application.yml
server:
  port: 9090

# Ou en ligne de commande
java -jar target/console-log-api-1.0.0.jar --server.port=9090
```

### Activer les logs détaillés
```bash
java -jar target/console-log-api-1.0.0.jar --logging.level.root=DEBUG
```

### Utiliser un profil spécifique
```bash
# Profil dev
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Profil prod
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

---

## 🐛 Résolution de problèmes

### ❌ Port 8080 déjà utilisé
```bash
# Solution 1: Arrêter le processus utilisant le port
# Windows: netstat -ano | findstr :8080
# Linux: lsof -i :8080

# Solution 2: Changer le port
java -jar target/console-log-api-1.0.0.jar --server.port=9090
```

### ❌ Maven non trouvé
```bash
# Vérifier l'installation
mvn -version

# Si non installé:
# Windows: Télécharger depuis https://maven.apache.org/download.cgi
# Linux: sudo apt install maven
# Mac: brew install maven
```

### ❌ Java non trouvé ou version incorrecte
```bash
# Vérifier la version
java -version

# Version requise: Java 11 ou supérieur
# Télécharger depuis: https://adoptium.net/
```

### ❌ Erreur de compilation
```bash
# Nettoyer et recompiler
mvn clean install

# Si problème persiste, supprimer le cache Maven
rm -rf ~/.m2/repository
mvn clean install
```

---

## 📚 Documentation disponible

| Fichier | Contenu |
|---------|---------|
| `README_SPRING_BOOT.md` | Configuration Spring Boot détaillée |
| `POSTMAN_EXAMPLES.md` | Tous les exemples d'API avec curl |
| `README_CRUD_API.md` | Architecture et design patterns |
| `QUICK_START.md` | Guide de démarrage en 5 minutes |
| `GUIDE_DEMARRAGE_COMPLET.md` | Ce fichier - Vue d'ensemble |

---

## ✅ Checklist de vérification

- [ ] Java 11+ installé (`java -version`)
- [ ] Maven 3.6+ installé (`mvn -version`)
- [ ] Projet téléchargé/cloné
- [ ] Dépendances téléchargées (`mvn clean install`)
- [ ] Application démarrée (`mvn spring-boot:run` ou `start.bat`)
- [ ] Swagger accessible (http://localhost:8080/swagger-ui.html)
- [ ] Test d'un endpoint réussi
- [ ] Base de données H2 accessible (http://localhost:8080/h2-console)

---

## 🎉 Félicitations !

Votre projet Spring Boot est maintenant **100% opérationnel** !

### Prochaines étapes suggérées:

1. **Explorer l'API avec Swagger**: http://localhost:8080/swagger-ui.html
2. **Tester avec Postman**: Importer la collection fournie
3. **Consulter la base H2**: http://localhost:8080/h2-console
4. **Lire la documentation**: Parcourir les fichiers README_*.md
5. **Personnaliser**: Adapter le code à vos besoins spécifiques

### Besoin d'aide ?

- 📖 Consultez `README_SPRING_BOOT.md` pour la configuration
- 🔍 Consultez `POSTMAN_EXAMPLES.md` pour les exemples d'API
- 🏗️ Consultez `README_CRUD_API.md` pour l'architecture

---

**Bon développement ! 🚀**
