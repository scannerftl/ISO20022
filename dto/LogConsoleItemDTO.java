package com.megatim.iso20022.share.model.console.dto;

import com.megatim.iso20022.share.model.console.LogConsoleEntityType;
import com.megatim.iso20022.systac.model.enumeration.VersionMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogConsoleItemDTO {
    private Long id;
    private String etapeTraitement;
    private String typeRejet;
    private String typeJobQueue;
    private String typeSource;
    private String typeMessage;
    private VersionMessage versionMessage;
    private String sensOperation;
    private String referenceMessage;
    private String referenceMessageOriginal;
    private String versionMessageOriginale;
    private Boolean siAnnulationMessageEntier;
    private Boolean siConfirmationAnnulation;
    private String statutBeac;
    private String dateCreationMessage;
    private String nombreTransactions;
    private String montantTotal;
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
    private LogConsoleEntityType logConsoleEntityType;
    private List<LogConsoleDetailsDTO> logConsoleDetails = new ArrayList<>();
    private List<LogConsoleItemInfoDTO> informationsComplementaires = new ArrayList<>();
}
