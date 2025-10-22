package com.megatim.iso20022.share.model.console.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogConsoleDetailsIdentificationDTO {
    private Long id;
    private String identification;
    private String libelle;
}
