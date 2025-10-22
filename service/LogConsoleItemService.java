package com.megatim.iso20022.share.model.console.service;

import com.megatim.iso20022.share.model.console.LogConsoleItem;
import com.megatim.iso20022.share.model.console.LogConsoleEntityType;
import com.megatim.iso20022.share.model.console.dto.LogConsoleItemDTO;
import com.megatim.iso20022.share.model.console.mapper.LogConsoleItemMapper;
import com.megatim.iso20022.share.model.console.repository.LogConsoleItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LogConsoleItemService {

    @Autowired
    private LogConsoleItemRepository repository;

    @Autowired
    private LogConsoleItemMapper mapper;

    /**
     * Créer un nouveau LogConsoleItem
     */
    public LogConsoleItemDTO create(LogConsoleItemDTO dto) {
        LogConsoleItem entity = mapper.toEntity(dto);
        entity.setId(null); // S'assurer que c'est une nouvelle entité
        LogConsoleItem saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    /**
     * Récupérer tous les LogConsoleItem
     */
    @Transactional(readOnly = true)
    public List<LogConsoleItemDTO> findAll() {
        List<LogConsoleItem> entities = repository.findAll();
        return mapper.toDTOList(entities);
    }

    /**
     * Récupérer un LogConsoleItem par ID
     */
    @Transactional(readOnly = true)
    public Optional<LogConsoleItemDTO> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO);
    }

    /**
     * Mettre à jour un LogConsoleItem existant
     */
    public LogConsoleItemDTO update(Long id, LogConsoleItemDTO dto) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("LogConsoleItem avec l'ID " + id + " n'existe pas");
        }
        
        LogConsoleItem entity = mapper.toEntity(dto);
        entity.setId(id);
        
        // Gérer les relations enfants
        if (entity.getLogConsoleDetails() != null) {
            entity.getLogConsoleDetails().forEach(detail -> detail.setLogConsoleItem(entity));
        }
        
        if (entity.getInformationsComplementaires() != null) {
            entity.getInformationsComplementaires().forEach(info -> info.setLogConsoleItem(entity));
        }
        
        LogConsoleItem updated = repository.save(entity);
        return mapper.toDTO(updated);
    }

    /**
     * Supprimer un LogConsoleItem par ID
     */
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("LogConsoleItem avec l'ID " + id + " n'existe pas");
        }
        repository.deleteById(id);
    }

    /**
     * Rechercher par type d'entité
     */
    @Transactional(readOnly = true)
    public List<LogConsoleItemDTO> findByEntityType(LogConsoleEntityType entityType) {
        List<LogConsoleItem> entities = repository.findByLogConsoleEntityType(entityType);
        return mapper.toDTOList(entities);
    }

    /**
     * Rechercher par référence de message
     */
    @Transactional(readOnly = true)
    public List<LogConsoleItemDTO> findByReferenceMessage(String referenceMessage) {
        List<LogConsoleItem> entities = repository.findByReferenceMessage(referenceMessage);
        return mapper.toDTOList(entities);
    }

    /**
     * Rechercher par type de message
     */
    @Transactional(readOnly = true)
    public List<LogConsoleItemDTO> findByTypeMessage(String typeMessage) {
        List<LogConsoleItem> entities = repository.findByTypeMessage(typeMessage);
        return mapper.toDTOList(entities);
    }

    /**
     * Rechercher par date de session
     */
    @Transactional(readOnly = true)
    public List<LogConsoleItemDTO> findByDateSession(String dateSession) {
        List<LogConsoleItem> entities = repository.findByDateSession(dateSession);
        return mapper.toDTOList(entities);
    }

    /**
     * Rechercher par code BIC (émetteur ou destinataire)
     */
    @Transactional(readOnly = true)
    public List<LogConsoleItemDTO> findByCodeBic(String codeBic) {
        List<LogConsoleItem> entities = repository.findByCodeBic(codeBic);
        return mapper.toDTOList(entities);
    }
}
