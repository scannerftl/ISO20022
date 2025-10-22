package com.megatim.iso20022.share.model.console.controller;

import com.megatim.iso20022.share.model.console.LogConsoleEntityType;
import com.megatim.iso20022.share.model.console.dto.LogConsoleItemDTO;
import com.megatim.iso20022.share.model.console.service.LogConsoleItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/log-console-items")
@CrossOrigin(origins = "*")
@Tag(name = "LogConsoleItem", description = "API de gestion des logs console ISO20022")
public class LogConsoleItemController {

    @Autowired
    private LogConsoleItemService service;

    @PostMapping
    @Operation(summary = "Créer un nouveau LogConsoleItem", description = "Crée un nouveau log console avec toutes ses relations")
    public ResponseEntity<LogConsoleItemDTO> create(@RequestBody LogConsoleItemDTO dto) {
        try {
            LogConsoleItemDTO created = service.create(dto);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @Operation(summary = "Récupérer tous les LogConsoleItem", description = "Retourne la liste complète des logs console")
    public ResponseEntity<List<LogConsoleItemDTO>> findAll() {
        try {
            List<LogConsoleItemDTO> items = service.findAll();
            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un LogConsoleItem par ID", description = "Retourne un log console spécifique avec toutes ses relations")
    public ResponseEntity<LogConsoleItemDTO> findById(
            @Parameter(description = "ID du LogConsoleItem") @PathVariable("id") Long id) {
        try {
            return service.findById(id)
                    .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un LogConsoleItem", description = "Met à jour un log console existant")
    public ResponseEntity<LogConsoleItemDTO> update(
            @Parameter(description = "ID du LogConsoleItem") @PathVariable("id") Long id,
            @RequestBody LogConsoleItemDTO dto) {
        try {
            LogConsoleItemDTO updated = service.update(id, dto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un LogConsoleItem", description = "Supprime un log console et toutes ses relations")
    public ResponseEntity<HttpStatus> delete(
            @Parameter(description = "ID du LogConsoleItem") @PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/by-entity-type")
    @Operation(summary = "Rechercher par type d'entité", description = "Recherche les logs par type d'entité console")
    public ResponseEntity<List<LogConsoleItemDTO>> findByEntityType(
            @Parameter(description = "Type d'entité") @RequestParam("type") LogConsoleEntityType type) {
        try {
            List<LogConsoleItemDTO> items = service.findByEntityType(type);
            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/by-reference")
    @Operation(summary = "Rechercher par référence de message", description = "Recherche les logs par référence de message")
    public ResponseEntity<List<LogConsoleItemDTO>> findByReferenceMessage(
            @Parameter(description = "Référence du message") @RequestParam("reference") String reference) {
        try {
            List<LogConsoleItemDTO> items = service.findByReferenceMessage(reference);
            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/by-type-message")
    @Operation(summary = "Rechercher par type de message", description = "Recherche les logs par type de message ISO20022")
    public ResponseEntity<List<LogConsoleItemDTO>> findByTypeMessage(
            @Parameter(description = "Type de message") @RequestParam("type") String type) {
        try {
            List<LogConsoleItemDTO> items = service.findByTypeMessage(type);
            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/by-date-session")
    @Operation(summary = "Rechercher par date de session", description = "Recherche les logs par date de session")
    public ResponseEntity<List<LogConsoleItemDTO>> findByDateSession(
            @Parameter(description = "Date de session") @RequestParam("date") String date) {
        try {
            List<LogConsoleItemDTO> items = service.findByDateSession(date);
            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/by-bic")
    @Operation(summary = "Rechercher par code BIC", description = "Recherche les logs par code BIC émetteur ou destinataire")
    public ResponseEntity<List<LogConsoleItemDTO>> findByCodeBic(
            @Parameter(description = "Code BIC") @RequestParam("code") String code) {
        try {
            List<LogConsoleItemDTO> items = service.findByCodeBic(code);
            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
