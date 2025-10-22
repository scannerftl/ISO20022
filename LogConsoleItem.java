package com.megatim.iso20022.share.model.console;

import com.megatim.iso20022.systac.model.enumeration.VersionMessage;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "log_console_item")
@Getter
@Setter
public class LogConsoleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String etapeTraitement;
    private String typeRejet;
    private String typeJobQueue;
    private String typeSource;
    private String typeMessage;
    @Enumerated(EnumType.STRING)
    @Column(name = "version_message")
    private VersionMessage versionMessage;
    private String sensOperation;
    private String referenceMessage;
    private String referenceMessageOriginal;
    private String versionMessageOriginale;
    @Column(name = "si_annulation_message_entier")
    private Boolean siAnnulationMessageEntier;
    @Column(name = "si_confirmation_annulation")
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

    @Enumerated(EnumType.STRING)
    @Column(name = "log_console_entity_type")
    private LogConsoleEntityType logConsoleEntityType;


    @OneToMany(mappedBy = "logConsoleItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LogConsoleDetails> logConsoleDetails = new ArrayList<>();
    @OneToMany(mappedBy = "logConsoleItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LogConsoleItemInfo> informationsComplementaires = new ArrayList<>();

}
