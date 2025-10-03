package com.ems.ems.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ballots")
public class BallotModel {
    @Id
    private String ballotId;
    private String electionId;
    private String voterId;
    private String candidateId;
    private String partyId;
}

