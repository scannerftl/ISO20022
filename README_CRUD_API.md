# API CRUD pour LogConsoleItem

## Vue d'ensemble

Cette API REST complète permet de gérer les entités `LogConsoleItem` avec toutes leurs relations en cascade :
- **LogConsoleDetails** (transactions détaillées)
- **LogConsoleDetailsIdentification** (identifications émetteur/destinataire)
- **LogConsoleItemInfo** (informations complémentaires clé-valeur)

## Architecture

### Structure des fichiers créés

```
console/
├── controller/
│   └── LogConsoleItemController.java          # Endpoints REST
├── service/
│   └── LogConsoleItemService.java             # Logique métier
├── repository/
│   └── LogConsoleItemRepository.java          # Accès base de données
├── dto/
│   ├── LogConsoleItemDTO.java                 # DTO principal
│   ├── LogConsoleDetailsDTO.java              # DTO détails transaction
│   ├── LogConsoleDetailsIdentificationDTO.java # DTO identification
│   └── LogConsoleItemInfoDTO.java             # DTO info complémentaire
├── mapper/
│   └── LogConsoleItemMapper.java              # Conversion Entity <-> DTO
├── test-data/
│   ├── create_simple.json                     # Exemple création simple
│   ├── create_complete.json                   # Exemple création complète
│   ├── create_error_example.json              # Exemple avec erreur
│   └── update_example.json                    # Exemple mise à jour
├── POSTMAN_EXAMPLES.md                        # Documentation API complète
└── README_CRUD_API.md                         # Ce fichier
```

## Fonctionnalités

### Opérations CRUD de base

1. **CREATE** - Créer un nouveau LogConsoleItem
   - Endpoint: `POST /api/log-console-items`
   - Gestion automatique des relations en cascade

2. **READ** - Récupérer les LogConsoleItem
   - Tous: `GET /api/log-console-items`
   - Par ID: `GET /api/log-console-items/{id}`

3. **UPDATE** - Mettre à jour un LogConsoleItem
   - Endpoint: `PUT /api/log-console-items/{id}`
   - Mise à jour complète avec relations

4. **DELETE** - Supprimer un LogConsoleItem
   - Endpoint: `DELETE /api/log-console-items/{id}`
   - Suppression en cascade des relations

### Recherches avancées

1. **Par type d'entité**
   - `GET /api/log-console-items/search/by-entity-type?type=QUEU_IN`

2. **Par référence de message**
   - `GET /api/log-console-items/search/by-reference?reference=MSG123`

3. **Par type de message**
   - `GET /api/log-console-items/search/by-type-message?type=PACS008`

4. **Par date de session**
   - `GET /api/log-console-items/search/by-date-session?date=2024-10-22`

5. **Par code BIC**
   - `GET /api/log-console-items/search/by-bic?code=BICFRCM01`

## Gestion des relations

### Cascade ALL + orphanRemoval

Les relations sont configurées avec `cascade = CascadeType.ALL` et `orphanRemoval = true` :

- **Création** : Les entités enfants sont automatiquement créées
- **Mise à jour** : Les modifications sont propagées
- **Suppression** : Les enfants sont supprimés automatiquement

### Exemple de structure complète

```json
{
  "id": 1,
  "referenceMessage": "MSG20241022001",
  "typeMessage": "PACS008",
  "montantTotal": "500000.00",
  "logConsoleDetails": [
    {
      "id": 1,
      "referenceTransaction": "TRX001",
      "montantTransaction": "250000.00",
      "informationIdentificationsEmetteur": [
        {
          "id": 1,
          "identification": "ID-001",
          "libelle": "Numéro employé"
        }
      ],
      "informationIdentificationsDestinataire": [...]
    }
  ],
  "informationsComplementaires": [
    {
      "id": 1,
      "cle": "departement",
      "valeur": "RH"
    }
  ]
}
```

## Utilisation rapide

### 1. Créer un LogConsoleItem simple

```bash
curl -X POST http://localhost:8080/api/log-console-items \
  -H "Content-Type: application/json" \
  -d @test-data/create_simple.json
```

### 2. Créer avec toutes les relations

```bash
curl -X POST http://localhost:8080/api/log-console-items \
  -H "Content-Type: application/json" \
  -d @test-data/create_complete.json
```

### 3. Récupérer tous les items

```bash
curl -X GET http://localhost:8080/api/log-console-items
```

### 4. Récupérer un item spécifique

```bash
curl -X GET http://localhost:8080/api/log-console-items/1
```

### 5. Mettre à jour un item

```bash
curl -X PUT http://localhost:8080/api/log-console-items/1 \
  -H "Content-Type: application/json" \
  -d @test-data/update_example.json
```

### 6. Supprimer un item

```bash
curl -X DELETE http://localhost:8080/api/log-console-items/1
```

## Codes de retour HTTP

| Code | Description |
|------|-------------|
| 200 OK | Opération réussie (GET, PUT) |
| 201 CREATED | Création réussie (POST) |
| 204 NO_CONTENT | Suppression réussie ou liste vide |
| 404 NOT_FOUND | Ressource non trouvée |
| 500 INTERNAL_SERVER_ERROR | Erreur serveur |

## Types d'entités disponibles

```java
public enum LogConsoleEntityType {
    QUEU_ERROR,
    QUEU_IN,
    QUEU_IN_ARCHIVAGE,
    JOB_QUEUE,
    JOB_QUEUE_ARCHIVAGE
}
```

## Exemples de scénarios métier

### Scénario 1 : Traitement d'un paiement normal

1. Créer un item avec `logConsoleEntityType = QUEU_IN`
2. Ajouter les détails de transaction
3. Mettre à jour le statut à `COMPLETE`
4. Archiver avec `logConsoleEntityType = QUEU_IN_ARCHIVAGE`

### Scénario 2 : Gestion d'une erreur

1. Créer un item avec `logConsoleEntityType = QUEU_ERROR`
2. Renseigner `typeRejet` et `motifRejet`
3. Incrémenter `nbreRejeu` si rejeu
4. Utiliser `create_error_example.json` comme modèle

### Scénario 3 : Recherche et reporting

1. Rechercher par date de session
2. Filtrer par type de message
3. Grouper par code BIC
4. Analyser les montants totaux

## Configuration requise

### Dépendances Spring Boot

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

### Configuration application.properties

```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/console_db
spring.datasource.username=your_username
spring.datasource.password=your_password

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Logging
logging.level.com.megatim.iso20022=DEBUG
```

## Tests avec Postman

### Import de collection

1. Créer une nouvelle collection "LogConsoleItem API"
2. Importer les exemples depuis `POSTMAN_EXAMPLES.md`
3. Configurer une variable d'environnement `baseUrl = http://localhost:8080`

### Variables d'environnement suggérées

```json
{
  "baseUrl": "http://localhost:8080",
  "itemId": "1",
  "referenceMessage": "MSG20241022001",
  "dateSession": "2024-10-22"
}
```

## Bonnes pratiques

1. **Validation** : Ajouter `@Valid` sur les DTOs pour validation automatique
2. **Pagination** : Implémenter `Pageable` pour les listes volumineuses
3. **Sécurité** : Ajouter Spring Security pour l'authentification
4. **Logging** : Utiliser SLF4J pour tracer les opérations
5. **Exception handling** : Créer un `@ControllerAdvice` pour gérer les erreurs globalement

## Évolutions possibles

- [ ] Ajouter la pagination sur `GET /api/log-console-items`
- [ ] Implémenter des filtres combinés
- [ ] Ajouter des statistiques et agrégations
- [ ] Créer des endpoints pour les exports (CSV, Excel)
- [ ] Implémenter un système de cache (Redis)
- [ ] Ajouter des webhooks pour les événements
- [ ] Créer une interface d'administration web

## Support et documentation

- **Documentation complète** : Voir `POSTMAN_EXAMPLES.md`
- **Exemples JSON** : Dossier `test-data/`
- **Modèle de données** : Voir `README_JPA_ENTITIES.md`

## Auteur

Créé pour le projet ISO20022 - Système de console de logs
