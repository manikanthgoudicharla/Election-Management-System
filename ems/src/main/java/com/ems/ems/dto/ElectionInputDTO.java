package com.ems.ems.dto;

//lombok
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;



import com.fasterxml.jackson.annotation.JsonFormat;

//validation
import jakarta.validation.constraints.*;

//time
import java.time.LocalTime;
import java.time.LocalDate;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectionInputDTO {


    @NotBlank( message = "The ElectionName cannot be Blank !")
    @Size(max=100)
    private String electionName;
   
    @NotBlank( message = "The ElectionType cannot be Blank !")
    private String electionType;

    @NotBlank( message = "The ElectionStatus cannot be Blank !")
    private String electionStatus;

    @NotNull(message="Election Date cannot be Null")
    @JsonFormat( pattern ="yyyy-MM-dd")
    private LocalDate electionDate;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime electionStartTime;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime electionEndTime;

    @NotBlank(message="Election description cannot be blank")
    @Size(max=1000)
    private String electiondDesc;

}
