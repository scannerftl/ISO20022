# Exemples de requêtes pour tester l'API LogConsoleItem

## Configuration de base
- **Base URL**: `http://localhost:8080/api/log-console-items`
- **Content-Type**: `application/json`

---

## 1. CREATE - Créer un nouveau LogConsoleItem

### Endpoint
```
POST http://localhost:8080/api/log-console-items
```

### Headers
```
Content-Type: application/json
```

### Body (JSON) - Exemple Simple
```json
{
  "etapeTraitement": "TRAITEMENT_INITIAL",
  "typeRejet": null,
  "typeJobQueue": "QUEUE_PAIEMENT",
  "typeSource": "SWIFT",
  "typeMessage": "PACS008",
  "versionMessage": "V1",
  "sensOperation": "DEBIT",
  "referenceMessage": "MSG20241022001",
  "referenceMessageOriginal": null,
  "versionMessageOriginale": null,
  "siAnnulationMessageEntier": false,
  "siConfirmationAnnulation": false,
  "statutBeac": "EN_COURS",
  "dateCreationMessage": "2024-10-22T08:30:00",
  "nombreTransactions": "1",
  "montantTotal": "150000.00",
  "devise": "XAF",
  "prioritePaiement": "HAUTE",
  "dateReglementInterbancaire": "2024-10-22",
  "methodeReglement": "RTGS",
  "typeSystemeCompensation": "SYSTAC",
  "categoriePaiement": "VIREMENT",
  "codeBicEmetteur": "BICFRCM01",
  "codeAgenceEmetteur": "AG001",
  "codeBicDestinataire": "BICCMCM02",
  "codeAgenceDestinataire": "AG002",
  "dateCloture": null,
  "dateRejet": null,
  "motifRejet": null,
  "motifPaiement": "Paiement fournisseur",
  "informationsSupplementairesPaiement": "Facture N° 2024-001",
  "nbreRejeu": "0",
  "dateSession": "2024-10-22",
  "logConsoleEntityType": "QUEU_IN",
  "logConsoleDetails": [],
  "informationsComplementaires": []
}
```

### Body (JSON) - Exemple Complet avec Relations
```json
{
  "etapeTraitement": "TRAITEMENT_INITIAL",
  "typeRejet": null,
  "typeJobQueue": "QUEUE_PAIEMENT",
  "typeSource": "SWIFT",
  "typeMessage": "PACS008",
  "versionMessage": "V1",
  "sensOperation": "DEBIT",
  "referenceMessage": "MSG20241022002",
  "referenceMessageOriginal": null,
  "versionMessageOriginale": null,
  "siAnnulationMessageEntier": false,
  "siConfirmationAnnulation": false,
  "statutBeac": "EN_COURS",
  "dateCreationMessage": "2024-10-22T09:15:00",
  "nombreTransactions": "2",
  "montantTotal": "500000.00",
  "devise": "XAF",
  "prioritePaiement": "NORMALE",
  "dateReglementInterbancaire": "2024-10-22",
  "methodeReglement": "RTGS",
  "typeSystemeCompensation": "SYSTAC",
  "categoriePaiement": "VIREMENT",
  "codeBicEmetteur": "BICFRCM01",
  "codeAgenceEmetteur": "AG001",
  "codeBicDestinataire": "BICCMCM02",
  "codeAgenceDestinataire": "AG002",
  "dateCloture": null,
  "dateRejet": null,
  "motifRejet": null,
  "motifPaiement": "Paiement salaires",
  "informationsSupplementairesPaiement": "Lot salaires octobre 2024",
  "nbreRejeu": "0",
  "dateSession": "2024-10-22",
  "logConsoleEntityType": "QUEU_IN",
  "logConsoleDetails": [
    {
      "referenceTransaction": "TRX001",
      "identifiantEndToEnd": "E2E-2024-001",
      "identifiantSystemeInformation": "SYS-INFO-001",
      "codeBicBanqueClientDebiteurUltime": "BICFRCM01",
      "nomClientDebiteurUltime": "ENTREPRISE ABC SARL",
      "idCompteClientDebiteurUltime": "CM21001234567890123456789",
      "codeBicBanqueClientBeneficiaireUltime": "BICCMCM02",
      "nomClientBeneficiaireUltime": "DUPONT Jean",
      "idCompteClientBeneficiaireUltime": "CM21009876543210987654321",
      "montantTransaction": "250000.00",
      "dateReglement": "2024-10-22",
      "statutBeac": "ACCEPTE",
      "informationIdentificationsEmetteur": [
        {
          "identification": "ID-EMETTEUR-001",
          "libelle": "Numéro employé"
        },
        {
          "identification": "ID-EMETTEUR-002",
          "libelle": "Code service"
        }
      ],
      "informationIdentificationsDestinataire": [
        {
          "identification": "ID-DEST-001",
          "libelle": "Numéro sécurité sociale"
        }
      ]
    },
    {
      "referenceTransaction": "TRX002",
      "identifiantEndToEnd": "E2E-2024-002",
      "identifiantSystemeInformation": "SYS-INFO-002",
      "codeBicBanqueClientDebiteurUltime": "BICFRCM01",
      "nomClientDebiteurUltime": "ENTREPRISE ABC SARL",
      "idCompteClientDebiteurUltime": "CM21001234567890123456789",
      "codeBicBanqueClientBeneficiaireUltime": "BICCMCM02",
      "nomClientBeneficiaireUltime": "MARTIN Marie",
      "idCompteClientBeneficiaireUltime": "CM21005555666677778888999",
      "montantTransaction": "250000.00",
      "dateReglement": "2024-10-22",
      "statutBeac": "ACCEPTE",
      "informationIdentificationsEmetteur": [
        {
          "identification": "ID-EMETTEUR-003",
          "libelle": "Numéro employé"
        }
      ],
      "informationIdentificationsDestinataire": [
        {
          "identification": "ID-DEST-002",
          "libelle": "Numéro sécurité sociale"
        }
      ]
    }
  ],
  "informationsComplementaires": [
    {
      "cle": "departement",
      "valeur": "Ressources Humaines"
    },
    {
      "cle": "periode",
      "valeur": "Octobre 2024"
    },
    {
      "cle": "responsable",
      "valeur": "Directeur RH"
    }
  ]
}
```

### Commande cURL
```bash
curl -X POST http://localhost:8080/api/log-console-items \
  -H "Content-Type: application/json" \
  -d '{
    "etapeTraitement": "TRAITEMENT_INITIAL",
    "typeMessage": "PACS008",
    "versionMessage": "V1",
    "referenceMessage": "MSG20241022003",
    "montantTotal": "100000.00",
    "devise": "XAF",
    "codeBicEmetteur": "BICFRCM01",
    "codeBicDestinataire": "BICCMCM02",
    "dateSession": "2024-10-22",
    "logConsoleEntityType": "QUEU_IN"
  }'
```

---

## 2. READ - Récupérer tous les LogConsoleItem

### Endpoint
```
GET http://localhost:8080/api/log-console-items
```

### Commande cURL
```bash
curl -X GET http://localhost:8080/api/log-console-items
```

---

## 3. READ - Récupérer un LogConsoleItem par ID

### Endpoint
```
GET http://localhost:8080/api/log-console-items/{id}
```

### Exemple
```
GET http://localhost:8080/api/log-console-items/1
```

### Commande cURL
```bash
curl -X GET http://localhost:8080/api/log-console-items/1
```

---

## 4. UPDATE - Mettre à jour un LogConsoleItem

### Endpoint
```
PUT http://localhost:8080/api/log-console-items/{id}
```

### Headers
```
Content-Type: application/json
```

### Body (JSON)
```json
{
  "etapeTraitement": "TRAITEMENT_TERMINE",
  "typeRejet": null,
  "typeJobQueue": "QUEUE_PAIEMENT",
  "typeSource": "SWIFT",
  "typeMessage": "PACS008",
  "versionMessage": "V1",
  "sensOperation": "DEBIT",
  "referenceMessage": "MSG20241022001",
  "referenceMessageOriginal": null,
  "versionMessageOriginale": null,
  "siAnnulationMessageEntier": false,
  "siConfirmationAnnulation": false,
  "statutBeac": "COMPLETE",
  "dateCreationMessage": "2024-10-22T08:30:00",
  "nombreTransactions": "1",
  "montantTotal": "150000.00",
  "devise": "XAF",
  "prioritePaiement": "HAUTE",
  "dateReglementInterbancaire": "2024-10-22",
  "methodeReglement": "RTGS",
  "typeSystemeCompensation": "SYSTAC",
  "categoriePaiement": "VIREMENT",
  "codeBicEmetteur": "BICFRCM01",
  "codeAgenceEmetteur": "AG001",
  "codeBicDestinataire": "BICCMCM02",
  "codeAgenceDestinataire": "AG002",
  "dateCloture": "2024-10-22T10:00:00",
  "dateRejet": null,
  "motifRejet": null,
  "motifPaiement": "Paiement fournisseur",
  "informationsSupplementairesPaiement": "Facture N° 2024-001 - PAYEE",
  "nbreRejeu": "0",
  "dateSession": "2024-10-22",
  "logConsoleEntityType": "QUEU_IN_ARCHIVAGE",
  "logConsoleDetails": [],
  "informationsComplementaires": []
}
```

### Commande cURL
```bash
curl -X PUT http://localhost:8080/api/log-console-items/1 \
  -H "Content-Type: application/json" \
  -d '{
    "etapeTraitement": "TRAITEMENT_TERMINE",
    "statutBeac": "COMPLETE",
    "dateCloture": "2024-10-22T10:00:00",
    "logConsoleEntityType": "QUEU_IN_ARCHIVAGE"
  }'
```

---

## 5. DELETE - Supprimer un LogConsoleItem

### Endpoint
```
DELETE http://localhost:8080/api/log-console-items/{id}
```

### Exemple
```
DELETE http://localhost:8080/api/log-console-items/1
```

### Commande cURL
```bash
curl -X DELETE http://localhost:8080/api/log-console-items/1
```

---

## 6. SEARCH - Rechercher par type d'entité

### Endpoint
```
GET http://localhost:8080/api/log-console-items/search/by-entity-type?type={type}
```

### Exemples
```
GET http://localhost:8080/api/log-console-items/search/by-entity-type?type=QUEU_IN
GET http://localhost:8080/api/log-console-items/search/by-entity-type?type=QUEU_ERROR
GET http://localhost:8080/api/log-console-items/search/by-entity-type?type=JOB_QUEUE
```

### Valeurs possibles pour type
- `QUEU_ERROR`
- `QUEU_IN`
- `QUEU_IN_ARCHIVAGE`
- `JOB_QUEUE`
- `JOB_QUEUE_ARCHIVAGE`

### Commande cURL
```bash
curl -X GET "http://localhost:8080/api/log-console-items/search/by-entity-type?type=QUEU_IN"
```

---

## 7. SEARCH - Rechercher par référence de message

### Endpoint
```
GET http://localhost:8080/api/log-console-items/search/by-reference?reference={reference}
```

### Exemple
```
GET http://localhost:8080/api/log-console-items/search/by-reference?reference=MSG20241022001
```

### Commande cURL
```bash
curl -X GET "http://localhost:8080/api/log-console-items/search/by-reference?reference=MSG20241022001"
```

---

## 8. SEARCH - Rechercher par type de message

### Endpoint
```
GET http://localhost:8080/api/log-console-items/search/by-type-message?type={type}
```

### Exemple
```
GET http://localhost:8080/api/log-console-items/search/by-type-message?type=PACS008
```

### Commande cURL
```bash
curl -X GET "http://localhost:8080/api/log-console-items/search/by-type-message?type=PACS008"
```

---

## 9. SEARCH - Rechercher par date de session

### Endpoint
```
GET http://localhost:8080/api/log-console-items/search/by-date-session?date={date}
```

### Exemple
```
GET http://localhost:8080/api/log-console-items/search/by-date-session?date=2024-10-22
```

### Commande cURL
```bash
curl -X GET "http://localhost:8080/api/log-console-items/search/by-date-session?date=2024-10-22"
```

---

## 10. SEARCH - Rechercher par code BIC

### Endpoint
```
GET http://localhost:8080/api/log-console-items/search/by-bic?code={code}
```

### Exemple
```
GET http://localhost:8080/api/log-console-items/search/by-bic?code=BICFRCM01
```

### Commande cURL
```bash
curl -X GET "http://localhost:8080/api/log-console-items/search/by-bic?code=BICFRCM01"
```

---

## Exemple de scénario de test complet

### 1. Créer un LogConsoleItem
```bash
curl -X POST http://localhost:8080/api/log-console-items \
  -H "Content-Type: application/json" \
  -d @create_log_console_item.json
```

### 2. Récupérer tous les items
```bash
curl -X GET http://localhost:8080/api/log-console-items
```

### 3. Récupérer l'item créé (supposons ID = 1)
```bash
curl -X GET http://localhost:8080/api/log-console-items/1
```

### 4. Mettre à jour l'item
```bash
curl -X PUT http://localhost:8080/api/log-console-items/1 \
  -H "Content-Type: application/json" \
  -d @update_log_console_item.json
```

### 5. Rechercher par type d'entité
```bash
curl -X GET "http://localhost:8080/api/log-console-items/search/by-entity-type?type=QUEU_IN"
```

### 6. Supprimer l'item
```bash
curl -X DELETE http://localhost:8080/api/log-console-items/1
```

---

## Notes importantes

1. **Cascade ALL**: Les relations `logConsoleDetails` et `informationsComplementaires` sont gérées automatiquement (création, mise à jour, suppression en cascade).

2. **orphanRemoval = true**: Les éléments enfants supprimés de la liste seront automatiquement supprimés de la base de données.

3. **VersionMessage**: Les valeurs possibles dépendent de votre enum `VersionMessage` (ex: V1, V2, etc.).

4. **Format des dates**: Utilisez le format ISO 8601 pour les dates (ex: "2024-10-22" ou "2024-10-22T08:30:00").

5. **Gestion des erreurs**: L'API retourne les codes HTTP appropriés:
   - 200 OK: Succès
   - 201 CREATED: Création réussie
   - 204 NO_CONTENT: Suppression réussie ou liste vide
   - 404 NOT_FOUND: Ressource non trouvée
   - 500 INTERNAL_SERVER_ERROR: Erreur serveur

6. **CORS**: Le controller est configuré avec `@CrossOrigin(origins = "*")` pour permettre les requêtes depuis n'importe quelle origine.
