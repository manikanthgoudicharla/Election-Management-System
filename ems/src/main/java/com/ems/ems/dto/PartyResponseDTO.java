package com.ems.ems.dto;

// lombok
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

// time
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartyResponseDTO {

    private String partyId;  

    private String partyName;

    private String leaderName;

    private LocalDate foundedDate;

    private String partySymbol;

    private String partyDesc;  
}
