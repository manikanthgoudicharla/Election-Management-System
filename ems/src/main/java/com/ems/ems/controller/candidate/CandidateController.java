package com.ems.ems.controller.candidate;

import com.ems.ems.dto.CandidateInputDTO;
import com.ems.ems.dto.CandidateResponseDTO;
import com.ems.ems.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    // CREATE Candidate
    @PostMapping
    public ResponseEntity<?> createCandidate(@RequestBody CandidateInputDTO dto) {
        try {
            CandidateResponseDTO response = candidateService.createCandidate(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to create candidate: " + e.getMessage());
        }
    }

    // GET ALL Candidates
    @GetMapping
    public ResponseEntity<?> getAllCandidates() {
        try {
            List<CandidateResponseDTO> candidates = candidateService.getAllCandidates();
            return ResponseEntity.ok(candidates);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to fetch candidates: " + e.getMessage());
        }
    }

    // GET Candidate by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCandidateById(@PathVariable String id) {
        try {
            CandidateResponseDTO candidate = candidateService.getCandidateById(id);
            return ResponseEntity.ok(candidate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Candidate not found: " + e.getMessage());
        }
    }

    // UPDATE Candidate
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCandidate(@PathVariable String id, @RequestBody CandidateInputDTO dto) {
        try {
            CandidateResponseDTO updatedCandidate = candidateService.updateCandidate(id, dto);
            return ResponseEntity.ok(updatedCandidate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Failed to update candidate: " + e.getMessage());
        }
    }

    // DELETE Candidate
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCandidate(@PathVariable String id) {
        try {
            candidateService.deleteCandidate(id);
            return ResponseEntity.ok("Candidate deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Failed to delete candidate: " + e.getMessage());
        }
    }
}
