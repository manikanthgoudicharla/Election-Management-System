package com.ems.ems.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "candidates")
public class CandidateModel {

    @Id
    private String candidateId;
    private String candidateName;
    private String position;
    private String electionId;
    private String partyId;    
}
