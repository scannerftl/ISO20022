package com.megatim.iso20022.share.model.console.repository;

import com.megatim.iso20022.share.model.console.LogConsoleItem;
import com.megatim.iso20022.share.model.console.LogConsoleEntityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogConsoleItemRepository extends JpaRepository<LogConsoleItem, Long> {
    
    List<LogConsoleItem> findByLogConsoleEntityType(LogConsoleEntityType entityType);
    
    List<LogConsoleItem> findByReferenceMessage(String referenceMessage);
    
    List<LogConsoleItem> findByTypeMessage(String typeMessage);
    
    @Query("SELECT l FROM LogConsoleItem l WHERE l.dateSession = :dateSession")
    List<LogConsoleItem> findByDateSession(@Param("dateSession") String dateSession);
    
    @Query("SELECT l FROM LogConsoleItem l WHERE l.codeBicEmetteur = :codeBic OR l.codeBicDestinataire = :codeBic")
    List<LogConsoleItem> findByCodeBic(@Param("codeBic") String codeBic);
}
