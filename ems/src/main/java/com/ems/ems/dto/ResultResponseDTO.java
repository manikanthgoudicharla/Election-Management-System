package com.ems.ems.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponseDTO {
    private String candidateId;
    private String candidateName;
    private String partyId;
    private String partyName;
    private long votes; // number of ballots
}
