package com.ems.ems.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BallotResponseDTO {
    private String ballotId;
    private String electionId;
    private String voterId;
    private String candidateId;
}
