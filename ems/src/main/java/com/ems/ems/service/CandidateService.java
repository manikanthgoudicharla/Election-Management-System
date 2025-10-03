package com.ems.ems.service;

import com.ems.ems.dto.CandidateInputDTO;
import com.ems.ems.dto.CandidateResponseDTO;
import com.ems.ems.model.CandidateModel;
import com.ems.ems.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//repos
import com.ems.ems.repository.PartyRepository;
import com.ems.ems.repository.ElectionRepository;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private ElectionRepository electionRepository;

    // Convert DTO to Model
    public CandidateModel convToModel(CandidateInputDTO candidateInputDTO) {
        CandidateModel candidateModel = new CandidateModel();

        candidateModel.setCandidateName(candidateInputDTO.getCandidateName());
        candidateModel.setElectionId(candidateInputDTO.getElectionId());
        candidateModel.setPartyId(candidateInputDTO.getPartyId());
        candidateModel.setPosition(candidateInputDTO.getPosition());

        return candidateModel;
    }

    // Convert Model to Response DTO
    public CandidateResponseDTO convToResponseDTO(CandidateModel candidateModel) {
        CandidateResponseDTO dto = new CandidateResponseDTO();
        dto.setCandidateId(candidateModel.getCandidateId());
        dto.setCandidateName(candidateModel.getCandidateName());
        dto.setElectionId(candidateModel.getElectionId());
        dto.setPartyId(candidateModel.getPartyId());
        dto.setPosition(candidateModel.getPosition());
        return dto;
    }

    // CREATE Candidate
    public CandidateResponseDTO createCandidate(CandidateInputDTO dto) {
        String electionid=dto.getElectionId();
        String partyid=dto.getPartyId();

        if(electionRepository.existsById(electionid) && partyRepository.existsById(partyid)){
          CandidateModel candidate = convToModel(dto);
          CandidateModel savedCandidate = candidateRepository.save(candidate);
          return convToResponseDTO(savedCandidate);
        }
        else{
          throw new IllegalArgumentException("Either Election ID or Party ID does not exist");
        }
       
    }

    // READ ALL Candidates
    public List<CandidateResponseDTO> getAllCandidates() {
        return candidateRepository.findAll()
                .stream()
                .map(this::convToResponseDTO)
                .collect(Collectors.toList());
    }

    // READ Candidate by ID
    public CandidateResponseDTO getCandidateById(String candidateId) {
        CandidateModel candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found"));
        return convToResponseDTO(candidate);
    }

    // UPDATE Candidate
    public CandidateResponseDTO updateCandidate(String candidateId, CandidateInputDTO dto) {
        CandidateModel candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found"));

        candidate.setCandidateName(dto.getCandidateName());
        candidate.setElectionId(dto.getElectionId());
        candidate.setPartyId(dto.getPartyId());
        candidate.setPosition(dto.getPosition());

        CandidateModel updatedCandidate = candidateRepository.save(candidate);
        return convToResponseDTO(updatedCandidate);
    }

    // DELETE Candidate
    public void deleteCandidate(String candidateId) {
        CandidateModel candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found"));
        candidateRepository.delete(candidate);
    }
}
