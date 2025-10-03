package com.ems.ems.model;

// lombok
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

// spring data mongo
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// time
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "parties")
public class PartyModel {

    @Id
    private String partyId; 

    private String partyName;

    private String leaderName;

    private LocalDate foundedDate;

    private String partySymbol;

    private String partyDesc;
}
