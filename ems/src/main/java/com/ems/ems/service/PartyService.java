package com.ems.ems.service;

import com.ems.ems.dto.PartyInputDTO;
import com.ems.ems.dto.PartyResponseDTO;
import com.ems.ems.model.PartyModel;
import com.ems.ems.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartyService {

    @Autowired
    private PartyRepository partyRepository;

    // Convert DTO to Model
    public PartyModel convToModel(PartyInputDTO partyInputDTO) {
        PartyModel partyModel = new PartyModel();
        partyModel.setPartyName(partyInputDTO.getPartyName());
        partyModel.setLeaderName(partyInputDTO.getLeaderName());
        partyModel.setFoundedDate(partyInputDTO.getFoundedDate());
        partyModel.setPartySymbol(partyInputDTO.getPartySymbol());
        partyModel.setPartyDesc(partyInputDTO.getPartyDesc());
        return partyModel;
    }

    // Convert Model to Response DTO
    public PartyResponseDTO convResponseDTO(PartyModel partyModel) {
        PartyResponseDTO dto = new PartyResponseDTO();
        dto.setPartyId(partyModel.getPartyId());
        dto.setPartyName(partyModel.getPartyName());
        dto.setLeaderName(partyModel.getLeaderName());
        dto.setFoundedDate(partyModel.getFoundedDate());
        dto.setPartySymbol(partyModel.getPartySymbol());
        dto.setPartyDesc(partyModel.getPartyDesc());
        return dto;
    }

    // CREATE
    public PartyResponseDTO createParty(PartyInputDTO dto) {
        PartyModel party = convToModel(dto);
        PartyModel savedParty = partyRepository.save(party);
        return convResponseDTO(savedParty);
    }

    // READ ALL
    public List<PartyResponseDTO> getAllParties() {
        return partyRepository.findAll()
                .stream()
                .map(this::convResponseDTO)
                .collect(Collectors.toList());
    }

    // READ ONE
    public PartyResponseDTO getPartyById(String partyId) {
        PartyModel party = partyRepository.findById(partyId)
                .orElseThrow(() -> new IllegalArgumentException("Party not found"));
        return convResponseDTO(party);
    }

    // UPDATE
    public PartyResponseDTO updateParty(String partyId, PartyInputDTO dto) {
        PartyModel party = partyRepository.findById(partyId)
                .orElseThrow(() -> new IllegalArgumentException("Party not found"));

        party.setPartyName(dto.getPartyName());
        party.setLeaderName(dto.getLeaderName());
        party.setFoundedDate(dto.getFoundedDate());
        party.setPartySymbol(dto.getPartySymbol());
        party.setPartyDesc(dto.getPartyDesc());

        PartyModel updatedParty = partyRepository.save(party);
        return convResponseDTO(updatedParty);
    }

    // DELETE
    public void deleteParty(String partyId) {
        PartyModel party = partyRepository.findById(partyId)
                .orElseThrow(() -> new IllegalArgumentException("Party not found"));
        partyRepository.delete(party);
    }
}
