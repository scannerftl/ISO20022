package com.megatim.iso20022.share.model.console;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "log_console_details")
@Getter
@Setter
public class LogConsoleDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "log_console_item_id", nullable = false)
    private LogConsoleItem logConsoleItem;

    private String referenceTransaction;
    private String identifiantEndToEnd;
    private String identifiantSystemeInformation;
    private String codeBicBanqueClientDebiteurUltime;
    private String nomClientDebiteurUltime;
    private String idCompteClientDebiteurUltime;
    private String codeBicBanqueClientBeneficiaireUltime;
    private String nomClientBeneficiaireUltime;
    private String idCompteClientBeneficiaireUltime;
    private String montantTransaction;
    private String dateReglement;
    private String statutBeac;

    @OneToMany(mappedBy = "logConsoleDetailsEmetteur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LogConsoleDetailsIdentification> informationIdentificationsEmetteur = new ArrayList<>();
    @OneToMany(mappedBy = "logConsoleDetailsDestinataire", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LogConsoleDetailsIdentification> informationIdentificationsDestinataire = new ArrayList<>();

}
