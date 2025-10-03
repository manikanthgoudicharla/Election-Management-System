package com.ems.ems.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateResponseDTO {
    private String candidateId;
    private String candidateName;
    private String position;
    private String electionId;
    private String partyId;
}
