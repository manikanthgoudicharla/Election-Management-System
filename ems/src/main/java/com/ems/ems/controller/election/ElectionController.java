package com.ems.ems.controller.election;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.ems.ems.dto.ElectionInputDTO;
import com.ems.ems.dto.ElectionResponseDTO;
import com.ems.ems.service.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/election")
public class ElectionController {

    @Autowired
    private ElectionService electionService;

    // CREATE
    @PostMapping
    public ResponseEntity<?> createElection(@RequestBody ElectionInputDTO dto) {
        try {
            ElectionResponseDTO response = electionService.createElection(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to create election: " + e.getMessage());
        }
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllElections() {
        try {
            List<ElectionResponseDTO> elections = electionService.getAllElections();
            return ResponseEntity.ok(elections);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to fetch elections: " + e.getMessage());
        }
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<?> getElectionById(@PathVariable String id) {
        try {
            ElectionResponseDTO election = electionService.getElectionById(id);
            return ResponseEntity.ok(election);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Election not found: " + e.getMessage());
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateElection(@PathVariable String id, @RequestBody ElectionInputDTO dto) {
        try {
            ElectionResponseDTO updatedElection = electionService.updateElection(id, dto);
            return ResponseEntity.ok(updatedElection);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Failed to update election: " + e.getMessage());
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteElection(@PathVariable String id) {
        try {
            electionService.deleteElection(id);
            return ResponseEntity.ok("Election deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Failed to delete election: " + e.getMessage());
        }
    }

    // NOMINATE PARTY
    @PostMapping("/{electionId}/nominate/{partyId}")
    public ResponseEntity<?> nominateParty(@PathVariable String electionId, @PathVariable String partyId) {
        try {
            ElectionResponseDTO election = electionService.nominateParty(electionId, partyId);
            return ResponseEntity.ok(election);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Failed to nominate party: " + e.getMessage());
        }
    }
}
