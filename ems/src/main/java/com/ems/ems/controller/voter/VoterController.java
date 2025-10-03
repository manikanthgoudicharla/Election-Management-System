
package com.ems.ems.controller.voter;

import com.ems.ems.dto.VoterInputDTO;
import com.ems.ems.dto.VoterResponseDTO;
import com.ems.ems.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voter")
public class VoterController {

    @Autowired
    private VoterService voterService;

    // CREATE Voter
    @PostMapping
    public ResponseEntity<?> createVoter(@RequestBody VoterInputDTO dto) {
        try {
            VoterResponseDTO response = voterService.createVoter(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to create voter: " + e.getMessage());
        }
    }

    // GET All Voters
    @GetMapping
    public ResponseEntity<?> getAllVoters() {
        try {
            List<VoterResponseDTO> voters = voterService.getAllVoters();
            return ResponseEntity.ok(voters);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to fetch voters: " + e.getMessage());
        }
    }

    // GET Voter by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getVoterById(@PathVariable String id) {
        try {
            VoterResponseDTO voter = voterService.getVoterById(id);
            return ResponseEntity.ok(voter);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Voter not found: " + e.getMessage());
        }
    }

    // UPDATE Voter
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVoter(@PathVariable String id, @RequestBody VoterInputDTO dto) {
        try {
            VoterResponseDTO updatedVoter = voterService.updateVoter(id, dto);
            return ResponseEntity.ok(updatedVoter);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Failed to update voter: " + e.getMessage());
        }
    }

    // DELETE Voter
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVoter(@PathVariable String id) {
        try {
            voterService.deleteVoter(id);
            return ResponseEntity.ok("Voter deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Failed to delete voter: " + e.getMessage());
        }
    }
}
