package com.ems.ems.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BallotInputDTO {

    @NotBlank(message = "Election ID is required")
    private String electionId;

    @NotBlank(message = "Voter ID is required")
    private String voterId;

    @NotBlank(message = "Candidate ID is required")
    private String candidateId;
}
