package com.megatim.iso20022.share.model.console.controller;

import com.megatim.iso20022.share.model.console.LogConsoleEntityType;
import com.megatim.iso20022.share.model.console.dto.LogConsoleItemDTO;
import com.megatim.iso20022.share.model.console.service.LogConsoleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/log-console-items")
@CrossOrigin(origins = "*")
public class LogConsoleItemController {

    @Autowired
    private LogConsoleItemService service;

    /**
     * CREATE - Créer un nouveau LogConsoleItem
     * POST /api/log-console-items
     */
    @PostMapping
    public ResponseEntity<LogConsoleItemDTO> create(@RequestBody LogConsoleItemDTO dto) {
        try {
            LogConsoleItemDTO created = service.create(dto);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * READ - Récupérer tous les LogConsoleItem
     * GET /api/log-console-items
     */
    @GetMapping
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

    /**
     * READ - Récupérer un LogConsoleItem par ID
     * GET /api/log-console-items/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<LogConsoleItemDTO> findById(@PathVariable("id") Long id) {
        try {
            return service.findById(id)
                    .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * UPDATE - Mettre à jour un LogConsoleItem existant
     * PUT /api/log-console-items/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<LogConsoleItemDTO> update(@PathVariable("id") Long id, @RequestBody LogConsoleItemDTO dto) {
        try {
            LogConsoleItemDTO updated = service.update(id, dto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * DELETE - Supprimer un LogConsoleItem par ID
     * DELETE /api/log-console-items/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * SEARCH - Rechercher par type d'entité
     * GET /api/log-console-items/search/by-entity-type?type=QUEU_ERROR
     */
    @GetMapping("/search/by-entity-type")
    public ResponseEntity<List<LogConsoleItemDTO>> findByEntityType(@RequestParam("type") LogConsoleEntityType type) {
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

    /**
     * SEARCH - Rechercher par référence de message
     * GET /api/log-console-items/search/by-reference?reference=MSG123
     */
    @GetMapping("/search/by-reference")
    public ResponseEntity<List<LogConsoleItemDTO>> findByReferenceMessage(@RequestParam("reference") String reference) {
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

    /**
     * SEARCH - Rechercher par type de message
     * GET /api/log-console-items/search/by-type-message?type=PACS008
     */
    @GetMapping("/search/by-type-message")
    public ResponseEntity<List<LogConsoleItemDTO>> findByTypeMessage(@RequestParam("type") String type) {
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

    /**
     * SEARCH - Rechercher par date de session
     * GET /api/log-console-items/search/by-date-session?date=2024-01-15
     */
    @GetMapping("/search/by-date-session")
    public ResponseEntity<List<LogConsoleItemDTO>> findByDateSession(@RequestParam("date") String date) {
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

    /**
     * SEARCH - Rechercher par code BIC
     * GET /api/log-console-items/search/by-bic?code=BICFR123
     */
    @GetMapping("/search/by-bic")
    public ResponseEntity<List<LogConsoleItemDTO>> findByCodeBic(@RequestParam("code") String code) {
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
