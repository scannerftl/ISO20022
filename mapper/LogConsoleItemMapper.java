package com.megatim.iso20022.share.model.console.mapper;

import com.megatim.iso20022.share.model.console.*;
import com.megatim.iso20022.share.model.console.dto.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LogConsoleItemMapper {

    public LogConsoleItemDTO toDTO(LogConsoleItem entity) {
        if (entity == null) {
            return null;
        }

        LogConsoleItemDTO dto = new LogConsoleItemDTO();
        dto.setId(entity.getId());
        dto.setEtapeTraitement(entity.getEtapeTraitement());
        dto.setTypeRejet(entity.getTypeRejet());
        dto.setTypeJobQueue(entity.getTypeJobQueue());
        dto.setTypeSource(entity.getTypeSource());
        dto.setTypeMessage(entity.getTypeMessage());
        dto.setVersionMessage(entity.getVersionMessage());
        dto.setSensOperation(entity.getSensOperation());
        dto.setReferenceMessage(entity.getReferenceMessage());
        dto.setReferenceMessageOriginal(entity.getReferenceMessageOriginal());
        dto.setVersionMessageOriginale(entity.getVersionMessageOriginale());
        dto.setSiAnnulationMessageEntier(entity.getSiAnnulationMessageEntier());
        dto.setSiConfirmationAnnulation(entity.getSiConfirmationAnnulation());
        dto.setStatutBeac(entity.getStatutBeac());
        dto.setDateCreationMessage(entity.getDateCreationMessage());
        dto.setNombreTransactions(entity.getNombreTransactions());
        dto.setMontantTotal(entity.getMontantTotal());
        dto.setDevise(entity.getDevise());
        dto.setPrioritePaiement(entity.getPrioritePaiement());
        dto.setDateReglementInterbancaire(entity.getDateReglementInterbancaire());
        dto.setMethodeReglement(entity.getMethodeReglement());
        dto.setTypeSystemeCompensation(entity.getTypeSystemeCompensation());
        dto.setCategoriePaiement(entity.getCategoriePaiement());
        dto.setCodeBicEmetteur(entity.getCodeBicEmetteur());
        dto.setCodeAgenceEmetteur(entity.getCodeAgenceEmetteur());
        dto.setCodeBicDestinataire(entity.getCodeBicDestinataire());
        dto.setCodeAgenceDestinataire(entity.getCodeAgenceDestinataire());
        dto.setDateCloture(entity.getDateCloture());
        dto.setDateRejet(entity.getDateRejet());
        dto.setMotifRejet(entity.getMotifRejet());
        dto.setMotifPaiement(entity.getMotifPaiement());
        dto.setInformationsSupplementairesPaiement(entity.getInformationsSupplementairesPaiement());
        dto.setNbreRejeu(entity.getNbreRejeu());
        dto.setDateSession(entity.getDateSession());
        dto.setLogConsoleEntityType(entity.getLogConsoleEntityType());

        if (entity.getLogConsoleDetails() != null) {
            dto.setLogConsoleDetails(entity.getLogConsoleDetails().stream()
                    .map(this::toDetailsDTO)
                    .collect(Collectors.toList()));
        }

        if (entity.getInformationsComplementaires() != null) {
            dto.setInformationsComplementaires(entity.getInformationsComplementaires().stream()
                    .map(this::toInfoDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public LogConsoleItem toEntity(LogConsoleItemDTO dto) {
        if (dto == null) {
            return null;
        }

        LogConsoleItem entity = new LogConsoleItem();
        entity.setId(dto.getId());
        entity.setEtapeTraitement(dto.getEtapeTraitement());
        entity.setTypeRejet(dto.getTypeRejet());
        entity.setTypeJobQueue(dto.getTypeJobQueue());
        entity.setTypeSource(dto.getTypeSource());
        entity.setTypeMessage(dto.getTypeMessage());
        entity.setVersionMessage(dto.getVersionMessage());
        entity.setSensOperation(dto.getSensOperation());
        entity.setReferenceMessage(dto.getReferenceMessage());
        entity.setReferenceMessageOriginal(dto.getReferenceMessageOriginal());
        entity.setVersionMessageOriginale(dto.getVersionMessageOriginale());
        entity.setSiAnnulationMessageEntier(dto.getSiAnnulationMessageEntier());
        entity.setSiConfirmationAnnulation(dto.getSiConfirmationAnnulation());
        entity.setStatutBeac(dto.getStatutBeac());
        entity.setDateCreationMessage(dto.getDateCreationMessage());
        entity.setNombreTransactions(dto.getNombreTransactions());
        entity.setMontantTotal(dto.getMontantTotal());
        entity.setDevise(dto.getDevise());
        entity.setPrioritePaiement(dto.getPrioritePaiement());
        entity.setDateReglementInterbancaire(dto.getDateReglementInterbancaire());
        entity.setMethodeReglement(dto.getMethodeReglement());
        entity.setTypeSystemeCompensation(dto.getTypeSystemeCompensation());
        entity.setCategoriePaiement(dto.getCategoriePaiement());
        entity.setCodeBicEmetteur(dto.getCodeBicEmetteur());
        entity.setCodeAgenceEmetteur(dto.getCodeAgenceEmetteur());
        entity.setCodeBicDestinataire(dto.getCodeBicDestinataire());
        entity.setCodeAgenceDestinataire(dto.getCodeAgenceDestinataire());
        entity.setDateCloture(dto.getDateCloture());
        entity.setDateRejet(dto.getDateRejet());
        entity.setMotifRejet(dto.getMotifRejet());
        entity.setMotifPaiement(dto.getMotifPaiement());
        entity.setInformationsSupplementairesPaiement(dto.getInformationsSupplementairesPaiement());
        entity.setNbreRejeu(dto.getNbreRejeu());
        entity.setDateSession(dto.getDateSession());
        entity.setLogConsoleEntityType(dto.getLogConsoleEntityType());

        if (dto.getLogConsoleDetails() != null) {
            List<LogConsoleDetails> details = dto.getLogConsoleDetails().stream()
                    .map(detailDTO -> toDetailsEntity(detailDTO, entity))
                    .collect(Collectors.toList());
            entity.setLogConsoleDetails(details);
        }

        if (dto.getInformationsComplementaires() != null) {
            List<LogConsoleItemInfo> infos = dto.getInformationsComplementaires().stream()
                    .map(infoDTO -> toInfoEntity(infoDTO, entity))
                    .collect(Collectors.toList());
            entity.setInformationsComplementaires(infos);
        }

        return entity;
    }

    private LogConsoleDetailsDTO toDetailsDTO(LogConsoleDetails entity) {
        LogConsoleDetailsDTO dto = new LogConsoleDetailsDTO();
        dto.setId(entity.getId());
        dto.setReferenceTransaction(entity.getReferenceTransaction());
        dto.setIdentifiantEndToEnd(entity.getIdentifiantEndToEnd());
        dto.setIdentifiantSystemeInformation(entity.getIdentifiantSystemeInformation());
        dto.setCodeBicBanqueClientDebiteurUltime(entity.getCodeBicBanqueClientDebiteurUltime());
        dto.setNomClientDebiteurUltime(entity.getNomClientDebiteurUltime());
        dto.setIdCompteClientDebiteurUltime(entity.getIdCompteClientDebiteurUltime());
        dto.setCodeBicBanqueClientBeneficiaireUltime(entity.getCodeBicBanqueClientBeneficiaireUltime());
        dto.setNomClientBeneficiaireUltime(entity.getNomClientBeneficiaireUltime());
        dto.setIdCompteClientBeneficiaireUltime(entity.getIdCompteClientBeneficiaireUltime());
        dto.setMontantTransaction(entity.getMontantTransaction());
        dto.setDateReglement(entity.getDateReglement());
        dto.setStatutBeac(entity.getStatutBeac());

        if (entity.getInformationIdentificationsEmetteur() != null) {
            dto.setInformationIdentificationsEmetteur(entity.getInformationIdentificationsEmetteur().stream()
                    .map(this::toIdentificationDTO)
                    .collect(Collectors.toList()));
        }

        if (entity.getInformationIdentificationsDestinataire() != null) {
            dto.setInformationIdentificationsDestinataire(entity.getInformationIdentificationsDestinataire().stream()
                    .map(this::toIdentificationDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    private LogConsoleDetails toDetailsEntity(LogConsoleDetailsDTO dto, LogConsoleItem parent) {
        LogConsoleDetails entity = new LogConsoleDetails();
        entity.setId(dto.getId());
        entity.setLogConsoleItem(parent);
        entity.setReferenceTransaction(dto.getReferenceTransaction());
        entity.setIdentifiantEndToEnd(dto.getIdentifiantEndToEnd());
        entity.setIdentifiantSystemeInformation(dto.getIdentifiantSystemeInformation());
        entity.setCodeBicBanqueClientDebiteurUltime(dto.getCodeBicBanqueClientDebiteurUltime());
        entity.setNomClientDebiteurUltime(dto.getNomClientDebiteurUltime());
        entity.setIdCompteClientDebiteurUltime(dto.getIdCompteClientDebiteurUltime());
        entity.setCodeBicBanqueClientBeneficiaireUltime(dto.getCodeBicBanqueClientBeneficiaireUltime());
        entity.setNomClientBeneficiaireUltime(dto.getNomClientBeneficiaireUltime());
        entity.setIdCompteClientBeneficiaireUltime(dto.getIdCompteClientBeneficiaireUltime());
        entity.setMontantTransaction(dto.getMontantTransaction());
        entity.setDateReglement(dto.getDateReglement());
        entity.setStatutBeac(dto.getStatutBeac());

        if (dto.getInformationIdentificationsEmetteur() != null) {
            List<LogConsoleDetailsIdentification> identifications = dto.getInformationIdentificationsEmetteur().stream()
                    .map(idDTO -> toIdentificationEntityEmetteur(idDTO, entity))
                    .collect(Collectors.toList());
            entity.setInformationIdentificationsEmetteur(identifications);
        }

        if (dto.getInformationIdentificationsDestinataire() != null) {
            List<LogConsoleDetailsIdentification> identifications = dto.getInformationIdentificationsDestinataire().stream()
                    .map(idDTO -> toIdentificationEntityDestinataire(idDTO, entity))
                    .collect(Collectors.toList());
            entity.setInformationIdentificationsDestinataire(identifications);
        }

        return entity;
    }

    private LogConsoleDetailsIdentificationDTO toIdentificationDTO(LogConsoleDetailsIdentification entity) {
        LogConsoleDetailsIdentificationDTO dto = new LogConsoleDetailsIdentificationDTO();
        dto.setId(entity.getId());
        dto.setIdentification(entity.getIdentification());
        dto.setLibelle(entity.getLibelle());
        return dto;
    }

    private LogConsoleDetailsIdentification toIdentificationEntityEmetteur(LogConsoleDetailsIdentificationDTO dto, LogConsoleDetails parent) {
        LogConsoleDetailsIdentification entity = new LogConsoleDetailsIdentification();
        entity.setId(dto.getId());
        entity.setLogConsoleDetailsEmetteur(parent);
        entity.setIdentification(dto.getIdentification());
        entity.setLibelle(dto.getLibelle());
        return entity;
    }

    private LogConsoleDetailsIdentification toIdentificationEntityDestinataire(LogConsoleDetailsIdentificationDTO dto, LogConsoleDetails parent) {
        LogConsoleDetailsIdentification entity = new LogConsoleDetailsIdentification();
        entity.setId(dto.getId());
        entity.setLogConsoleDetailsDestinataire(parent);
        entity.setIdentification(dto.getIdentification());
        entity.setLibelle(dto.getLibelle());
        return entity;
    }

    private LogConsoleItemInfoDTO toInfoDTO(LogConsoleItemInfo entity) {
        LogConsoleItemInfoDTO dto = new LogConsoleItemInfoDTO();
        dto.setId(entity.getId());
        dto.setCle(entity.getCle());
        dto.setValeur(entity.getValeur());
        return dto;
    }

    private LogConsoleItemInfo toInfoEntity(LogConsoleItemInfoDTO dto, LogConsoleItem parent) {
        LogConsoleItemInfo entity = new LogConsoleItemInfo();
        entity.setId(dto.getId());
        entity.setLogConsoleItem(parent);
        entity.setCle(dto.getCle());
        entity.setValeur(dto.getValeur());
        return entity;
    }

    public List<LogConsoleItemDTO> toDTOList(List<LogConsoleItem> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
