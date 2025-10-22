package com.megatim.iso20022.share.model.console;

import com.megatim.iso20022.systac.model.entites.InformationComplementaire;
import com.megatim.iso20022.systac.model.enumeration.VersionMessage;
import lombok.Getter;
import lombok.Setter; 

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "log_console_item_info")
@Getter
@Setter
public class LogConsoleItemInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "log_console_item_id", nullable = false)
    private LogConsoleItem logConsoleItem;
    private String cle;
    private String valeur;

    public LogConsoleItemInfo() {}

    public LogConsoleItemInfo(String cle, String valeur) {
        this.cle = cle;
        this.valeur = valeur;
    }
}
