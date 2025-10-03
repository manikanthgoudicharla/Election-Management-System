package com.ems.ems.controller.election;

// controler annotatations
import org.springframework.web.bind.annotation.*;

// http
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

//dto
import com.ems.ems.dto.ElectionInputDTO;
import com.ems.ems.dto.ElectionResponseDTO;

//service
import com.ems.ems.service.ElectionService;

// bean
import org.springframework.beans.factory.annotation.Autowired;



@RestController
@RequestMapping("/election")
public class ElectionController {
    
    @Autowired
    private ElectionService electionService;

    @PostMapping
    public ResponseEntity<?> createElectionController(@RequestBody ElectionInputDTO electionInputDTO){
       try{

        ElectionResponseDTO response= electionService.createElection(electionInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
       }
       catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to create election"+e.getMessage());
       }
    }
    
}
