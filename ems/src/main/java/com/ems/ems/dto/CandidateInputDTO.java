package com.ems.ems.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateInputDTO {

    @NotBlank(message = "Candidate name cannot be blank")
    @Size(max = 100)
    private String candidateName;

    @NotBlank(message = "Position cannot be blank")
    private String position;

    @NotBlank(message = "Election ID is required")
    private String electionId; 

    @NotBlank(message = "Party ID is required")
    private String partyId;    
}
