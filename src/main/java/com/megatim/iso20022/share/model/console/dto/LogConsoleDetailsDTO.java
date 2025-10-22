package com.megatim.iso20022.share.model.console.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogConsoleDetailsDTO {
    private Long id;
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
    private List<LogConsoleDetailsIdentificationDTO> informationIdentificationsEmetteur = new ArrayList<>();
    private List<LogConsoleDetailsIdentificationDTO> informationIdentificationsDestinataire = new ArrayList<>();
}
