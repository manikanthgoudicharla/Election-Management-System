package com.ems.ems.controller.party;

import com.ems.ems.dto.PartyInputDTO;
import com.ems.ems.dto.PartyResponseDTO;
import com.ems.ems.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/party")
public class PartyController {

    @Autowired
    private PartyService partyService;

    // CREATE PARTY
    @PostMapping
    public ResponseEntity<?> createParty(@RequestBody PartyInputDTO dto) {
        try {
            PartyResponseDTO response = partyService.createParty(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to create party: " + e.getMessage());
        }
    }

    // GET ALL PARTIES
    @GetMapping
    public ResponseEntity<?> getAllParties() {
        try {
            List<PartyResponseDTO> parties = partyService.getAllParties();
            return ResponseEntity.ok(parties);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to fetch parties: " + e.getMessage());
        }
    }

    // GET PARTY BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPartyById(@PathVariable String id) {
        try {
            PartyResponseDTO party = partyService.getPartyById(id);
            return ResponseEntity.ok(party);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Party not found: " + e.getMessage());
        }
    }

    // UPDATE PARTY
    @PutMapping("/{id}")
    public ResponseEntity<?> updateParty(@PathVariable String id, @RequestBody PartyInputDTO dto) {
        try {
            PartyResponseDTO updatedParty = partyService.updateParty(id, dto);
            return ResponseEntity.ok(updatedParty);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Failed to update party: " + e.getMessage());
        }
    }

    // DELETE PARTY
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParty(@PathVariable String id) {
        try {
            partyService.deleteParty(id);
            return ResponseEntity.ok("Party deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Failed to delete party: " + e.getMessage());
        }
    }
}
