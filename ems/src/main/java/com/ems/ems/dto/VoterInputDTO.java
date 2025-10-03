package com.ems.ems.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoterInputDTO {

    @NotBlank(message = "Voter name cannot be blank")
    private String voterName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Voter ID number is required")
    private String voterIdentificationNumber;
}
