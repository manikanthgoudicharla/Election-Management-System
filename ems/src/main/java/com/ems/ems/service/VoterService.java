package com.ems.ems.service;

import com.ems.ems.dto.VoterInputDTO;
import com.ems.ems.dto.VoterResponseDTO;
import com.ems.ems.model.VoterModel;
import com.ems.ems.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoterService {

    @Autowired
    private VoterRepository voterRepository;

    // Convert DTO to Model
    public VoterModel convToModel(VoterInputDTO voterInputDTO) {
        VoterModel voterModel = new VoterModel();
        voterModel.setVoterName(voterInputDTO.getVoterName());
        voterModel.setEmail(voterInputDTO.getEmail());
        voterModel.setVoterIdentificationNumber(voterInputDTO.getVoterIdentificationNumber());
        return voterModel;
    }

    // Convert Model to Response DTO
    public VoterResponseDTO convToResponseDTO(VoterModel voterModel) {
        VoterResponseDTO dto = new VoterResponseDTO();
        dto.setVoterId(voterModel.getVoterId());
        dto.setVoterName(voterModel.getVoterName());
        dto.setEmail(voterModel.getEmail());
        dto.setVoterIdentificationNumber(voterModel.getVoterIdentificationNumber());
        return dto;
    }

    // CREATE Voter
    public VoterResponseDTO createVoter(VoterInputDTO dto) {
        VoterModel voter = convToModel(dto);
        VoterModel savedVoter = voterRepository.save(voter);
        return convToResponseDTO(savedVoter);
    }

    // READ All Voters
    public List<VoterResponseDTO> getAllVoters() {
        return voterRepository.findAll()
                .stream()
                .map(this::convToResponseDTO)
                .collect(Collectors.toList());
    }

    // READ Voter by ID
    public VoterResponseDTO getVoterById(String voterId) {
        VoterModel voter = voterRepository.findById(voterId)
                .orElseThrow(() -> new IllegalArgumentException("Voter not found"));
        return convToResponseDTO(voter);
    }

    // UPDATE Voter
    public VoterResponseDTO updateVoter(String voterId, VoterInputDTO dto) {
        VoterModel voter = voterRepository.findById(voterId)
                .orElseThrow(() -> new IllegalArgumentException("Voter not found"));

        voter.setVoterName(dto.getVoterName());
        voter.setEmail(dto.getEmail());
        voter.setVoterIdentificationNumber(dto.getVoterIdentificationNumber());

        VoterModel updatedVoter = voterRepository.save(voter);
        return convToResponseDTO(updatedVoter);
    }

    // DELETE Voter
    public void deleteVoter(String voterId) {
        VoterModel voter = voterRepository.findById(voterId)
                .orElseThrow(() -> new IllegalArgumentException("Voter not found"));
        voterRepository.delete(voter);
    }
}
