package com.ems.ems.service;

// election repository
import com.ems.ems.repository.ElectionRepository;
import com.ems.ems.repository.PartyRepository;

//service
import org.springframework.stereotype.Service;

import java.util.ArrayList;

//beans
import org.springframework.beans.factory.annotation.Autowired;

// dto
import com.ems.ems.dto.ElectionInputDTO;
import com.ems.ems.dto.ElectionResponseDTO;

//model
import com.ems.ems.model.ElectionModel;
// import com.ems.ems.model.PartyModel;

//utils
// import java.util.o;


@Service
public class ElectionService {

    @Autowired
    private ElectionRepository electionRepository;

    // @Autowired
    // private PartyRepository partyRepository;

     public ElectionModel convertToModel(ElectionInputDTO dto) {

        ElectionModel electionModel = new ElectionModel();
        electionModel.setElectionName(dto.getElectionName());
        electionModel.setElectionType(dto.getElectionType());
        electionModel.setElectionStatus(dto.getElectionStatus());
        electionModel.setElectionDate(dto.getElectionDate());
        electionModel.setElectionStartTime(dto.getElectionStartTime());
        electionModel.setElectionEndTime(dto.getElectionEndTime());
        electionModel.setElectiondDesc(dto.getElectiondDesc());
        electionModel.setPartyIds(new ArrayList<>());
        
        return electionModel;

    }
        public ElectionResponseDTO convToResponseDTO(ElectionModel electionModel){
            ElectionResponseDTO electionResponseDTO=new ElectionResponseDTO();
            
            electionResponseDTO.setElectionId(electionModel.getElectionId());
            electionResponseDTO.setElectionName(electionModel.getElectionName());
            electionResponseDTO.setElectionType(electionModel.getElectionType());
            electionResponseDTO.setElectionStatus(electionModel.getElectionStatus());
            electionResponseDTO.setElectionDate(electionModel.getElectionDate());
            electionResponseDTO.setElectionStartTime(electionModel.getElectionStartTime());
            electionResponseDTO.setElectionEndTime(electionModel.getElectionEndTime());
            electionResponseDTO.setElectiondDesc(electionModel.getElectiondDesc());
            electionResponseDTO.setPartyId(electionModel.getPartyIds());

            return electionResponseDTO;
        }

        //create election
    public ElectionResponseDTO createElection(ElectionInputDTO electioInputDTO){
        ElectionModel election=convertToModel(electioInputDTO);
        ElectionModel saveElection=electionRepository.save(election);
        return convToResponseDTO(saveElection);
    }
    
}
