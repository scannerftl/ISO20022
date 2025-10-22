package com.megatim.iso20022.share.model.console.dto;

import com.megatim.iso20022.share.model.console.LogConsoleEntityType;
import com.megatim.iso20022.systac.model.enumeration.VersionMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO pour LogConsoleItem")
public class LogConsoleItemDTO {
    @Schema(description = "Identifiant unique", example = "1")
    private Long id;
    
    @Schema(description = "Étape de traitement", example = "TRAITEMENT_INITIAL")
    private String etapeTraitement;
    
    private String typeRejet;
    private String typeJobQueue;
    private String typeSource;
    
    @Schema(description = "Type de message ISO20022", example = "PACS008")
    private String typeMessage;
    
    private VersionMessage versionMessage;
    private String sensOperation;
    
    @Schema(description = "Référence unique du message", example = "MSG20241022001")
    private String referenceMessage;
    
    private String referenceMessageOriginal;
    private String versionMessageOriginale;
    private Boolean siAnnulationMessageEntier;
    private Boolean siConfirmationAnnulation;
    private String statutBeac;
    private String dateCreationMessage;
    private String nombreTransactions;
    
    @Schema(description = "Montant total", example = "150000.00")
    private String montantTotal;
    
    @Schema(description = "Devise", example = "XAF")
    private String devise;
    
    private String prioritePaiement;
    private String dateReglementInterbancaire;
    private String methodeReglement;
    private String typeSystemeCompensation;
    private String categoriePaiement;
    private String codeBicEmetteur;
    private String codeAgenceEmetteur;
    private String codeBicDestinataire;
    private String codeAgenceDestinataire;
    private String dateCloture;
    private String dateRejet;
    private String motifRejet;
    private String motifPaiement;
    private String informationsSupplementairesPaiement;
    private String nbreRejeu;
    private String dateSession;
    
    @Schema(description = "Type d'entité console", example = "QUEU_IN")
    private LogConsoleEntityType logConsoleEntityType;
    
    @Schema(description = "Liste des détails de transaction")
    private List<LogConsoleDetailsDTO> logConsoleDetails = new ArrayList<>();
    
    @Schema(description = "Liste des informations complémentaires")
    private List<LogConsoleItemInfoDTO> informationsComplementaires = new ArrayList<>();
}
