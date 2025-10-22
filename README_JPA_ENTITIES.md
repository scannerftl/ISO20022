# Entités JPA - Structure et Utilisation

## Vue d'ensemble

Les classes ont été transformées en entités JPA avec **LogConsoleItem** comme agrégat principal. Toutes les opérations CRUD sur `LogConsoleItem` se propagent automatiquement aux entités liées grâce aux cascades.

## Structure des Relations

```
LogConsoleItem (Agrégat Principal)
├── @OneToMany → LogConsoleDetails
│   └── @OneToMany → LogConsoleDetailsIdentification (2 listes: émetteur et destinataire)
└── @OneToMany → LogConsoleItemInfo
```

## Détails des Entités

### 1. LogConsoleItem (Entité Principale)
- **Table**: `log_console_item`
- **Clé primaire**: `id` (auto-générée)
- **Relations**:
  - `logConsoleDetails`: Liste de détails (cascade ALL, orphanRemoval)
  - `informationsComplementaires`: Liste d'informations complémentaires (cascade ALL, orphanRemoval)

### 2. LogConsoleDetails
- **Table**: `log_console_details`
- **Clé primaire**: `id` (auto-générée)
- **Relations**:
  - `logConsoleItem`: Référence vers l'agrégat principal (ManyToOne, LAZY)
  - `informationIdentificationsEmetteur`: Liste d'identifications émetteur (cascade ALL, orphanRemoval)
  - `informationIdentificationsDestinataire`: Liste d'identifications destinataire (cascade ALL, orphanRemoval)

### 3. LogConsoleItemInfo
- **Table**: `log_console_item_info`
- **Clé primaire**: `id` (auto-générée)
- **Relations**:
  - `logConsoleItem`: Référence vers l'agrégat principal (ManyToOne, LAZY)

### 4. LogConsoleDetailsIdentification
- **Table**: `log_console_details_identification`
- **Clé primaire**: `id` (auto-générée)
- **Relations**:
  - `logConsoleDetailsEmetteur`: Référence optionnelle vers LogConsoleDetails (ManyToOne, LAZY)
  - `logConsoleDetailsDestinataire`: Référence optionnelle vers LogConsoleDetails (ManyToOne, LAZY)

### 5. LogConsoleEntityType
- **Type**: Enum
- **Valeurs**: QUEU_ERROR, QUEU_IN, QUEU_IN_ARCHIVAGE, JOB_QUEUE, JOB_QUEUE_ARCHIVAGE

## Utilisation CRUD

### Créer un LogConsoleItem avec toutes ses relations

```java
LogConsoleItem item = new LogConsoleItem();
item.setEtapeTraitement("Traitement 1");
item.setTypeMessage("PACS.008");

// Ajouter des détails
LogConsoleDetails details = new LogConsoleDetails();
details.setReferenceTransaction("REF123");
details.setLogConsoleItem(item); // Important: définir la relation inverse
item.getLogConsoleDetails().add(details);

// Ajouter des identifications émetteur
LogConsoleDetailsIdentification idEmetteur = new LogConsoleDetailsIdentification();
idEmetteur.setIdentification("ID001");
idEmetteur.setLibelle("Émetteur principal");
idEmetteur.setLogConsoleDetailsEmetteur(details); // Important: définir la relation inverse
details.getInformationIdentificationsEmetteur().add(idEmetteur);

// Ajouter des informations complémentaires
LogConsoleItemInfo info = new LogConsoleItemInfo("cleTest", "valeurTest");
info.setLogConsoleItem(item); // Important: définir la relation inverse
item.getInformationsComplementaires().add(info);

// Sauvegarder uniquement l'agrégat principal
logConsoleItemRepository.save(item); // Cascade ALL → sauvegarde automatique de toutes les entités liées
```

### Mettre à jour

```java
LogConsoleItem item = logConsoleItemRepository.findById(id).orElseThrow();
item.setEtapeTraitement("Nouveau traitement");

// Ajouter un nouveau détail
LogConsoleDetails newDetails = new LogConsoleDetails();
newDetails.setReferenceTransaction("REF456");
newDetails.setLogConsoleItem(item);
item.getLogConsoleDetails().add(newDetails);

logConsoleItemRepository.save(item); // Cascade ALL → mise à jour automatique
```

### Supprimer

```java
// Supprimer l'agrégat principal supprime automatiquement toutes les entités liées
logConsoleItemRepository.deleteById(id); // orphanRemoval = true → suppression en cascade
```

### Supprimer un élément d'une collection

```java
LogConsoleItem item = logConsoleItemRepository.findById(id).orElseThrow();

// Supprimer un détail spécifique
item.getLogConsoleDetails().removeIf(d -> d.getId().equals(detailId));

logConsoleItemRepository.save(item); // orphanRemoval = true → suppression automatique en base
```

## Points Importants

1. **Cascade ALL**: Toutes les opérations (PERSIST, MERGE, REMOVE, REFRESH, DETACH) se propagent automatiquement
2. **orphanRemoval = true**: Les entités retirées des collections sont automatiquement supprimées de la base
3. **FetchType.LAZY**: Les relations sont chargées à la demande pour optimiser les performances
4. **Relations bidirectionnelles**: Toujours définir les deux côtés de la relation pour maintenir la cohérence

## Repository Nécessaire

```java
@Repository
public interface LogConsoleItemRepository extends JpaRepository<LogConsoleItem, Long> {
    // Seul repository nécessaire pour gérer toutes les entités grâce aux cascades
}
```

## Configuration JPA (application.properties)

```properties
# Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Pour éviter les problèmes de lazy loading
spring.jpa.open-in-view=true
```

## Méthodes Utilitaires Recommandées

Ajouter ces méthodes dans `LogConsoleItem` pour faciliter la gestion des relations bidirectionnelles :

```java
public void addLogConsoleDetails(LogConsoleDetails details) {
    logConsoleDetails.add(details);
    details.setLogConsoleItem(this);
}

public void removeLogConsoleDetails(LogConsoleDetails details) {
    logConsoleDetails.remove(details);
    details.setLogConsoleItem(null);
}

public void addInformationComplementaire(LogConsoleItemInfo info) {
    informationsComplementaires.add(info);
    info.setLogConsoleItem(this);
}

public void removeInformationComplementaire(LogConsoleItemInfo info) {
    informationsComplementaires.remove(info);
    info.setLogConsoleItem(null);
}
```
