package com.ems.ems.dto;

// lombok
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

//validation
import jakarta.validation.constraints.*;

//json time
import com.fasterxml.jackson.annotation.JsonFormat;

// Local time and date
import java.time.LocalDate;
// import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartyInputDTO {

    @NotBlank( message = "The partyName cannot be Blank !")
    @Size(max=100)
    private String partyName;

    @NotBlank( message = "The leaderName cannot be Blank !")
    @Size(max=100)
    private String leaderName;
    
    @NotNull(message="founded Date cannot be Null")
    @JsonFormat( pattern ="yyyy-MM-dd")
    private LocalDate foundedDate;

    @NotBlank( message = "The partySymbol cannot be Blank !")
    @Size(max=100)
    private String partySymbol;
    
    @NotBlank( message = "The partydescription cannot be Blank !")
    @Size(max=100)
    private String PartyDesc;   
    
}
