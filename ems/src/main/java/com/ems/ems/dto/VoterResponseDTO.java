package com.ems.ems.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoterResponseDTO {
    private String voterId;
    private String voterName;
    private String email;
    private String voterIdentificationNumber;
}
