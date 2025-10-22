package com.megatim.iso20022.share.model.console;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "log_console_details_identification")
@Getter
@Setter
public class LogConsoleDetailsIdentification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "log_console_details_emetteur_id")
    private LogConsoleDetails logConsoleDetailsEmetteur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "log_console_details_destinataire_id")
    private LogConsoleDetails logConsoleDetailsDestinataire;

    private String identification;
    private String libelle;
}
