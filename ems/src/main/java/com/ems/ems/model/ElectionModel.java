package com.ems.ems.model;

//lombok
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


//document
import org.springframework.data.mongodb.core.mapping.Document;


import org.springframework.data.annotation.Id;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="election")
public class ElectionModel {

    
    @Id
    private String electionId;

    private String electionName;

    private String electionType;

    private String electionStatus;

    private LocalDate electionDate;

    private LocalTime electionStartTime;

    private LocalTime electionEndTime;

    private String electiondDesc;

    private List<String> partyIds;

}
