package com.ems.ems.dto;

//lombok
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;



//time
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectionResponseDTO {

    private String electionId;

    private String electionName;

    private String electionType;

    private String electionStatus;

    private LocalDate electionDate;

    private LocalTime electionStartTime;

    private LocalTime electionEndTime;

    private String electiondDesc;

    private List<String> partyId;

}
