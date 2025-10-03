package com.ems.ems.controller.ballot;


import com.ems.ems.dto.BallotInputDTO;
import com.ems.ems.dto.BallotResponseDTO;
import com.ems.ems.dto.ResultResponseDTO;
import com.ems.ems.service.BallotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ballot")
public class BallotController {

    @Autowired
    private BallotService ballotService;

    // Create Ballot (Cast Vote)
    @PostMapping
    public ResponseEntity<?> createBallot(@RequestBody BallotInputDTO dto) {
        try {
            BallotResponseDTO response = ballotService.createBallot(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create ballot: " + e.getMessage());
        }
    }

    // Get All Ballots
    @GetMapping
    public ResponseEntity<?> getAllBallots() {
        try {
            List<BallotResponseDTO> ballots = ballotService.getAllBallots();
            return ResponseEntity.ok(ballots);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch ballots: " + e.getMessage());
        }
    }

    // Get Ballot by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getBallotById(@PathVariable String id) {
        try {
            BallotResponseDTO ballot = ballotService.getBallotById(id);
            return ResponseEntity.ok(ballot);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ballot not found: " + e.getMessage());
        }
    }

    // Update Ballot
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBallot(@PathVariable String id, @RequestBody BallotInputDTO dto) {
        try {
            BallotResponseDTO updated = ballotService.updateBallot(id, dto);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Failed to update ballot: " + e.getMessage());
        }
    }

    // Delete Ballot
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBallot(@PathVariable String id) {
        try {
            ballotService.deleteBallot(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Failed to delete ballot: " + e.getMessage());
        }
    }
    
    //results
    @GetMapping("/results/{electionId}")
    public ResponseEntity<?> getResults(@PathVariable String electionId) {
        try {
            List<ResultResponseDTO> results = ballotService.calculateResults(electionId);
            return ResponseEntity.status(HttpStatus.OK).body("winner"+results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error calculating results: " + e.getMessage());
        }
    }

}
