# Guide de démarrage rapide - API LogConsoleItem

## 🚀 Démarrage en 5 minutes

### 1. Vérifier que votre application Spring Boot est démarrée

```bash
# L'application doit être accessible sur http://localhost:8080
```

### 2. Tester rapidement avec curl

#### Créer un LogConsoleItem
```bash
curl -X POST http://localhost:8080/api/log-console-items \
  -H "Content-Type: application/json" \
  -d "{\"etapeTraitement\":\"TRAITEMENT_INITIAL\",\"typeMessage\":\"PACS008\",\"versionMessage\":\"V1\",\"referenceMessage\":\"MSG001\",\"montantTotal\":\"100000.00\",\"devise\":\"XAF\",\"codeBicEmetteur\":\"BICFRCM01\",\"codeBicDestinataire\":\"BICCMCM02\",\"dateSession\":\"2024-10-22\",\"logConsoleEntityType\":\"QUEU_IN\"}"
```

#### Récupérer tous les items
```bash
curl -X GET http://localhost:8080/api/log-console-items
```

#### Récupérer un item par ID
```bash
curl -X GET http://localhost:8080/api/log-console-items/1
```

### 3. Tester avec Postman

1. **Importer la collection**
   - Ouvrir Postman
   - File → Import
   - Sélectionner `test-data/LogConsoleItem_API.postman_collection.json`

2. **Configurer les variables**
   - Créer un environnement "Local"
   - Ajouter la variable `baseUrl = http://localhost:8080/api/log-console-items`

3. **Exécuter les tests**
   - Dossier "CRUD Operations" → Exécuter toutes les requêtes
   - Dossier "Search Operations" → Tester les recherches

### 4. Utiliser les scripts de test automatisés

#### Sur Windows
```bash
cd test-data
test_api.bat
```

#### Sur Linux/Mac
```bash
cd test-data
chmod +x test_api.sh
./test_api.sh
```

## 📁 Fichiers JSON de test disponibles

| Fichier | Description |
|---------|-------------|
| `create_simple.json` | Création basique sans relations |
| `create_complete.json` | Création avec toutes les relations |
| `create_error_example.json` | Exemple de message en erreur |
| `update_example.json` | Mise à jour d'un item existant |

### Utilisation des fichiers JSON

```bash
# Créer avec un fichier JSON
curl -X POST http://localhost:8080/api/log-console-items \
  -H "Content-Type: application/json" \
  -d @test-data/create_complete.json

# Mettre à jour avec un fichier JSON
curl -X PUT http://localhost:8080/api/log-console-items/1 \
  -H "Content-Type: application/json" \
  -d @test-data/update_example.json
```

## 🔍 Endpoints disponibles

### CRUD de base
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

## 📊 Exemples de réponses

### Succès (201 Created)
```json
{
  "id": 1,
  "referenceMessage": "MSG001",
  "typeMessage": "PACS008",
  "montantTotal": "100000.00",
  "statutBeac": "EN_COURS",
  "logConsoleEntityType": "QUEU_IN"
}
```

### Erreur (404 Not Found)
```json
{
  "timestamp": "2024-10-22T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "LogConsoleItem avec l'ID 999 n'existe pas"
}
```

## 🎯 Scénarios d'utilisation courants

### Scénario 1: Créer un paiement simple
```bash
curl -X POST http://localhost:8080/api/log-console-items \
  -H "Content-Type: application/json" \
  -d @test-data/create_simple.json
```

### Scénario 2: Créer un lot de paiements avec détails
```bash
curl -X POST http://localhost:8080/api/log-console-items \
  -H "Content-Type: application/json" \
  -d @test-data/create_complete.json
```

### Scénario 3: Rechercher les messages en erreur
```bash
curl -X GET "http://localhost:8080/api/log-console-items/search/by-entity-type?type=QUEU_ERROR"
```

### Scénario 4: Archiver un message traité
```bash
# 1. Récupérer le message
curl -X GET http://localhost:8080/api/log-console-items/1

# 2. Mettre à jour avec statut archivé
curl -X PUT http://localhost:8080/api/log-console-items/1 \
  -H "Content-Type: application/json" \
  -d @test-data/update_example.json
```

## 🔧 Dépannage

### Erreur de connexion
```
curl: (7) Failed to connect to localhost port 8080
```
**Solution**: Vérifier que l'application Spring Boot est démarrée

### Erreur 500
```json
{"status": 500, "error": "Internal Server Error"}
```
**Solution**: Vérifier les logs de l'application et la configuration de la base de données

### Erreur 404 sur création
```json
{"status": 404, "error": "Not Found"}
```
**Solution**: Vérifier que l'URL est correcte et que le controller est bien scanné par Spring

## 📚 Documentation complète

- **Guide complet**: `POSTMAN_EXAMPLES.md`
- **Architecture**: `README_CRUD_API.md`
- **Modèle de données**: `README_JPA_ENTITIES.md`

## 💡 Conseils

1. **Toujours vérifier les IDs** retournés lors de la création
2. **Utiliser les fichiers JSON** fournis comme modèles
3. **Tester d'abord les opérations simples** avant les complexes
4. **Consulter les logs** en cas d'erreur
5. **Utiliser Postman** pour une meilleure visualisation des réponses

## ✅ Checklist de vérification

- [ ] Application Spring Boot démarrée
- [ ] Base de données accessible
- [ ] Endpoint `/api/log-console-items` accessible
- [ ] Création d'un item simple réussie
- [ ] Récupération de l'item créé réussie
- [ ] Mise à jour de l'item réussie
- [ ] Suppression de l'item réussie
- [ ] Recherches fonctionnelles

## 🎉 Vous êtes prêt !

L'API est maintenant opérationnelle. Consultez `POSTMAN_EXAMPLES.md` pour des exemples détaillés.
