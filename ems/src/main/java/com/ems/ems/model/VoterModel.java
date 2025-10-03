package com.ems.ems.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "voters")
public class VoterModel {

    @Id
    private String voterId;
    private String voterName;
    private String email;
    private String voterIdentificationNumber;
}
