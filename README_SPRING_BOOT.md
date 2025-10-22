# Console Log API - Projet Spring Boot Complet

## 🚀 Démarrage rapide

### Prérequis
- **Java 11** ou supérieur
- **Maven 3.6+**
- (Optionnel) **PostgreSQL** pour la production

### Option 1: Démarrage avec le script (Recommandé)

#### Windows
```bash
start.bat
```

#### Linux/Mac
```bash
chmod +x start.sh
./start.sh
```

### Option 2: Démarrage avec Maven
```bash
mvn spring-boot:run
```

### Option 3: Build et exécution du JAR
```bash
# Build
mvn clean package

# Exécution
java -jar target/console-log-api-1.0.0.jar
```

## 📍 URLs importantes

Une fois l'application démarrée, accédez à :

- **API REST**: http://localhost:8080/api/log-console-items
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/api-docs
- **H2 Console**: http://localhost:8080/h2-console
- **Health Check**: http://localhost:8080/actuator/health

## 🗄️ Configuration de la base de données

### Mode Développement (H2 - Par défaut)
L'application démarre avec une base H2 en mémoire. Aucune configuration requise.

**Connexion H2 Console:**
- URL: `jdbc:h2:mem:consoledb`
- Username: `sa`
- Password: *(vide)*

### Mode Production (PostgreSQL)

1. **Créer la base de données**
```sql
CREATE DATABASE console_db;
```

2. **Modifier `application-prod.yml`**
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/console_db
    username: votre_username
    password: votre_password
```

3. **Démarrer avec le profil prod**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

Ou avec le JAR:
```bash
java -jar target/console-log-api-1.0.0.jar --spring.profiles.active=prod
```

## 📁 Structure du projet

```
console/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/megatim/iso20022/share/
│   │   │       ├── ConsoleLogApplication.java          # Classe principale
│   │   │       ├── config/
│   │   │       │   └── OpenApiConfig.java              # Configuration Swagger
│   │   │       ├── exception/
│   │   │       │   ├── GlobalExceptionHandler.java     # Gestion globale des erreurs
│   │   │       │   └── ResourceNotFoundException.java  # Exception personnalisée
│   │   │       └── model/console/
│   │   │           ├── LogConsoleItem.java             # Entité principale
│   │   │           ├── LogConsoleDetails.java
│   │   │           ├── LogConsoleDetailsIdentification.java
│   │   │           ├── LogConsoleItemInfo.java
│   │   │           ├── LogConsoleEntityType.java       # Enum
│   │   │           ├── controller/
│   │   │           │   └── LogConsoleItemController.java
│   │   │           ├── service/
│   │   │           │   └── LogConsoleItemService.java
│   │   │           ├── repository/
│   │   │           │   └── LogConsoleItemRepository.java
│   │   │           ├── dto/
│   │   │           │   ├── LogConsoleItemDTO.java
│   │   │           │   ├── LogConsoleDetailsDTO.java
│   │   │           │   ├── LogConsoleDetailsIdentificationDTO.java
│   │   │           │   └── LogConsoleItemInfoDTO.java
│   │   │           └── mapper/
│   │   │               └── LogConsoleItemMapper.java
│   │   └── resources/
│   │       ├── application.yml                         # Configuration principale
│   │       ├── application-dev.yml                     # Profil développement
│   │       ├── application-prod.yml                    # Profil production
│   │       └── banner.txt                              # Banner personnalisé
│   └── test/
│       └── java/
├── test-data/                                          # Fichiers de test JSON
│   ├── create_simple.json
│   ├── create_complete.json
│   ├── create_error_example.json
│   ├── update_example.json
│   ├── test_api.sh
│   ├── test_api.bat
│   └── LogConsoleItem_API.postman_collection.json
├── pom.xml                                             # Configuration Maven
├── start.bat                                           # Script de démarrage Windows
├── start.sh                                            # Script de démarrage Linux/Mac
└── build.bat                                           # Script de build Windows
```

## 🔧 Configuration

### Profils disponibles

1. **default** (dev): Base H2 en mémoire
2. **dev**: Base H2 en mémoire avec logs détaillés
3. **prod**: PostgreSQL avec optimisations

### Changer de profil

**Avec Maven:**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

**Avec le JAR:**
```bash
java -jar target/console-log-api-1.0.0.jar --spring.profiles.active=prod
```

**Variable d'environnement:**
```bash
export SPRING_PROFILES_ACTIVE=prod
java -jar target/console-log-api-1.0.0.jar
```

### Changer le port

**Dans application.yml:**
```yaml
server:
  port: 9090
```

**En ligne de commande:**
```bash
java -jar target/console-log-api-1.0.0.jar --server.port=9090
```

## 📊 Swagger UI

L'API est documentée avec OpenAPI 3.0 (Swagger).

Accédez à: http://localhost:8080/swagger-ui.html

Vous pouvez tester tous les endpoints directement depuis l'interface Swagger.

## 🧪 Tests

### Test rapide avec curl
```bash
# Créer un item
curl -X POST http://localhost:8080/api/log-console-items \
  -H "Content-Type: application/json" \
  -d @test-data/create_simple.json

# Récupérer tous les items
curl http://localhost:8080/api/log-console-items

# Récupérer un item par ID
curl http://localhost:8080/api/log-console-items/1
```

### Tests automatisés
```bash
# Windows
cd test-data
test_api.bat

# Linux/Mac
cd test-data
chmod +x test_api.sh
./test_api.sh
```

### Importer dans Postman
1. Ouvrir Postman
2. File → Import
3. Sélectionner `test-data/LogConsoleItem_API.postman_collection.json`

## 📦 Dépendances principales

- **Spring Boot 2.7.18**
- **Spring Data JPA** - Persistance
- **Spring Web** - API REST
- **H2 Database** - Base de données en mémoire (dev)
- **PostgreSQL Driver** - Base de données (prod)
- **Lombok** - Réduction du code boilerplate
- **Springdoc OpenAPI** - Documentation Swagger
- **Spring Boot Actuator** - Monitoring
- **Spring Boot DevTools** - Rechargement automatique

## 🔍 Monitoring avec Actuator

Endpoints disponibles:
- **Health**: http://localhost:8080/actuator/health
- **Info**: http://localhost:8080/actuator/info
- **Metrics**: http://localhost:8080/actuator/metrics

## 🐛 Dépannage

### Erreur: Port 8080 déjà utilisé
```bash
# Changer le port
java -jar target/console-log-api-1.0.0.jar --server.port=9090
```

### Erreur: Maven non trouvé
```bash
# Vérifier l'installation
mvn -version

# Si non installé, télécharger depuis:
# https://maven.apache.org/download.cgi
```

### Erreur: Java non trouvé
```bash
# Vérifier l'installation
java -version

# Version requise: Java 11 ou supérieur
```

### Problème de connexion à la base de données
```bash
# Vérifier que PostgreSQL est démarré
# Vérifier les credentials dans application-prod.yml
# Vérifier que la base de données existe
```

### Logs détaillés
```bash
# Activer les logs DEBUG
java -jar target/console-log-api-1.0.0.jar --logging.level.root=DEBUG
```

## 📝 Logs

Les logs sont affichés dans la console en mode développement.

En production, les logs sont écrits dans:
- `logs/console-log-api.log`
- Rotation automatique: 10MB max, 30 jours d'historique

## 🔐 Sécurité

**Note**: Cette version n'inclut pas Spring Security. Pour ajouter l'authentification:

1. Ajouter la dépendance dans `pom.xml`:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

2. Configurer la sécurité selon vos besoins (JWT, OAuth2, etc.)

## 🚀 Déploiement

### Build pour production
```bash
mvn clean package -Pprod
```

### Exécution en production
```bash
java -jar target/console-log-api-1.0.0.jar \
  --spring.profiles.active=prod \
  --server.port=8080
```

### Docker (optionnel)
Créer un `Dockerfile`:
```dockerfile
FROM openjdk:11-jre-slim
COPY target/console-log-api-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

Build et run:
```bash
docker build -t console-log-api .
docker run -p 8080:8080 console-log-api
```

## 📚 Documentation complémentaire

- **API REST**: Voir `POSTMAN_EXAMPLES.md`
- **Architecture**: Voir `README_CRUD_API.md`
- **Guide rapide**: Voir `QUICK_START.md`

## ✅ Checklist de démarrage

- [ ] Java 11+ installé
- [ ] Maven 3.6+ installé
- [ ] Projet cloné/téléchargé
- [ ] `mvn clean install` exécuté avec succès
- [ ] Application démarrée avec `mvn spring-boot:run`
- [ ] Accès à http://localhost:8080/swagger-ui.html
- [ ] Test d'un endpoint avec Swagger ou curl
- [ ] Import de la collection Postman (optionnel)

## 🎉 Vous êtes prêt !

L'application est maintenant opérationnelle. Consultez Swagger UI pour explorer l'API.

**Support**: Pour toute question, consultez la documentation dans les fichiers README_*.md
