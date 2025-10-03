package com.ems.ems.service;

import com.ems.ems.repository.ElectionRepository;
import com.ems.ems.repository.PartyRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ems.ems.dto.ElectionInputDTO;
import com.ems.ems.dto.ElectionResponseDTO;
import com.ems.ems.model.ElectionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElectionService {

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private PartyRepository partyRepository;

    // Convert DTO to Model
    public ElectionModel convertToModel(ElectionInputDTO dto) {
        ElectionModel electionModel = new ElectionModel();
        electionModel.setElectionName(dto.getElectionName());
        electionModel.setElectionType(dto.getElectionType());
        electionModel.setElectionStatus(dto.getElectionStatus());
        electionModel.setElectionDate(dto.getElectionDate());
        electionModel.setElectionStartTime(dto.getElectionStartTime());
        electionModel.setElectionEndTime(dto.getElectionEndTime());
        electionModel.setElectiondDesc(dto.getElectiondDesc());
        electionModel.setPartyIds(new ArrayList<>()); // Empty initially
        return electionModel;
    }

    // Convert Model to DTO
    public ElectionResponseDTO convToResponseDTO(ElectionModel electionModel) {
        ElectionResponseDTO dto = new ElectionResponseDTO();
        dto.setElectionId(electionModel.getElectionId());
        dto.setElectionName(electionModel.getElectionName());
        dto.setElectionType(electionModel.getElectionType());
        dto.setElectionStatus(electionModel.getElectionStatus());
        dto.setElectionDate(electionModel.getElectionDate());
        dto.setElectionStartTime(electionModel.getElectionStartTime());
        dto.setElectionEndTime(electionModel.getElectionEndTime());
        dto.setElectiondDesc(electionModel.getElectiondDesc());
        dto.setPartyId(electionModel.getPartyIds());
        return dto;
    }

    // Create Election
    public ElectionResponseDTO createElection(ElectionInputDTO dto) {
        ElectionModel election = convertToModel(dto);
        ElectionModel savedElection = electionRepository.save(election);
        return convToResponseDTO(savedElection);
    }

    // Get All Elections
    public List<ElectionResponseDTO> getAllElections() {
        return electionRepository.findAll()
                .stream()
                .map(this::convToResponseDTO)
                .collect(Collectors.toList());
    }

    // Get Election by ID
    public ElectionResponseDTO getElectionById(String electionId) {
        ElectionModel election = electionRepository.findById(electionId)
                .orElseThrow(() -> new IllegalArgumentException("Election not found"));
        return convToResponseDTO(election);
    }

    // Update Election
    public ElectionResponseDTO updateElection(String electionId, ElectionInputDTO dto) {
        ElectionModel election = electionRepository.findById(electionId)
                .orElseThrow(() -> new IllegalArgumentException("Election not found"));

        election.setElectionName(dto.getElectionName());
        election.setElectionType(dto.getElectionType());
        election.setElectionStatus(dto.getElectionStatus());
        election.setElectionDate(dto.getElectionDate());
        election.setElectionStartTime(dto.getElectionStartTime());
        election.setElectionEndTime(dto.getElectionEndTime());
        election.setElectiondDesc(dto.getElectiondDesc());

        ElectionModel updatedElection = electionRepository.save(election);
        return convToResponseDTO(updatedElection);
    }

    // Delete Election
    public void deleteElection(String electionId) {
        ElectionModel election = electionRepository.findById(electionId)
                .orElseThrow(() -> new IllegalArgumentException("Election not found"));
        electionRepository.delete(election);
    }

    // Nominate a Party to an Election
    public ElectionResponseDTO nominateParty(String electionId, String partyId) {
        ElectionModel election = electionRepository.findById(electionId)
                .orElseThrow(() -> new IllegalArgumentException("Election not found"));

        if (!partyRepository.existsById(partyId)) {
            throw new IllegalArgumentException("Invalid Party ID");
        }

        if (!election.getPartyIds().contains(partyId)) {
            election.getPartyIds().add(partyId);
            electionRepository.save(election);
        }

        return convToResponseDTO(election);
    }
}
