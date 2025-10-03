package com.ems.ems.service;

import com.ems.ems.dto.BallotInputDTO;
import com.ems.ems.dto.BallotResponseDTO;
import com.ems.ems.dto.ResultResponseDTO;
import com.ems.ems.model.BallotModel;
import com.ems.ems.model.CandidateModel;
import com.ems.ems.model.ElectionModel;
import com.ems.ems.model.PartyModel;
import com.ems.ems.repository.BallotRepository;
import com.ems.ems.repository.CandidateRepository;
import com.ems.ems.repository.ElectionRepository;
import com.ems.ems.repository.PartyRepository;
import com.ems.ems.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BallotService {

    @Autowired
    private BallotRepository ballotRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PartyRepository partyRepository;

    public BallotModel convToModel(BallotInputDTO ballotInputDTO) {
        BallotModel ballotModel = new BallotModel();
        ballotModel.setCandidateId(ballotInputDTO.getCandidateId());
        ballotModel.setElectionId(ballotInputDTO.getElectionId());
        ballotModel.setVoterId(ballotInputDTO.getVoterId());
        ballotModel.setPartyId(ballotInputDTO.getPartyId());
        return ballotModel;
    }

    public BallotResponseDTO convToResponseDTO(BallotModel ballotModel) {
        BallotResponseDTO dto = new BallotResponseDTO();
        dto.setBallotId(ballotModel.getBallotId());
        dto.setCandidateId(ballotModel.getCandidateId());
        dto.setElectionId(ballotModel.getElectionId());
        dto.setVoterId(ballotModel.getVoterId());
        dto.setPartyId(ballotModel.getPartyId());
        return dto;
    }

    public BallotResponseDTO createBallot(BallotInputDTO ballotInputDTO) {
        ElectionModel election = electionRepository.findById(ballotInputDTO.getElectionId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Election ID: " + ballotInputDTO.getElectionId()));

        if (!election.getPartyIds().contains(ballotInputDTO.getPartyId())) {
            throw new IllegalArgumentException("Party is not participating in Election " + election.getElectionName());
        }

        CandidateModel candidate = candidateRepository.findById(ballotInputDTO.getCandidateId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Candidate ID: " + ballotInputDTO.getCandidateId()));

        if (!candidate.getPartyId().equals(ballotInputDTO.getPartyId())) {
            throw new IllegalArgumentException("Candidate does not belong to the given Party.");
        }

        if (!voterRepository.existsById(ballotInputDTO.getVoterId())) {
            throw new IllegalArgumentException("Invalid Voter ID.");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime electionStart = LocalDateTime.of(election.getElectionDate(), election.getElectionStartTime());
        LocalDateTime electionEnd = LocalDateTime.of(election.getElectionDate(), election.getElectionEndTime());

        if (now.isBefore(electionStart)) {
            throw new IllegalArgumentException("Voting has not started yet. Starts at: " + electionStart);
        }
        if (now.isAfter(electionEnd)) {
            throw new IllegalArgumentException("Voting is over. Ended at: " + electionEnd);
        }

        boolean alreadyVoted = ballotRepository.existsByElectionIdAndVoterId(
                ballotInputDTO.getElectionId(), ballotInputDTO.getVoterId());

        if (alreadyVoted) {
            throw new IllegalArgumentException("Voter has already cast a vote in this election.");
        }

        BallotModel ballot = convToModel(ballotInputDTO);
        BallotModel saved = ballotRepository.save(ballot);
        return convToResponseDTO(saved);
    }

    public List<BallotResponseDTO> getAllBallots() {
        return ballotRepository.findAll().stream()
                .map(this::convToResponseDTO)
                .collect(Collectors.toList());
    }

    public BallotResponseDTO getBallotById(String id) {
        BallotModel ballot = ballotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ballot not found with ID: " + id));
        return convToResponseDTO(ballot);
    }

    public BallotResponseDTO updateBallot(String id, BallotInputDTO dto) {
        BallotModel existing = ballotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ballot not found with ID: " + id));

        existing.setCandidateId(dto.getCandidateId());
        existing.setElectionId(dto.getElectionId());
        existing.setVoterId(dto.getVoterId());
        existing.setPartyId(dto.getPartyId());

        BallotModel updated = ballotRepository.save(existing);
        return convToResponseDTO(updated);
    }

    public void deleteBallot(String id) {
        if (!ballotRepository.existsById(id)) {
            throw new RuntimeException("Ballot not found with ID: " + id);
        }
        ballotRepository.deleteById(id);
    }

    public List<ResultResponseDTO> calculateResults(String electionId) {
        ElectionModel election = electionRepository.findById(electionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Election ID: " + electionId));

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime electionEnd = LocalDateTime.of(election.getElectionDate(), election.getElectionEndTime());

        if (now.isBefore(electionEnd)) {
            throw new IllegalArgumentException("Election is still ongoing. Results available after: " + electionEnd);
        }

        List<BallotModel> ballots = ballotRepository.findByElectionId(electionId);
        if (ballots.isEmpty()) {
            throw new IllegalArgumentException("No votes cast for this election.");
        }

        Map<String, Long> candidateVoteCount = ballots.stream()
                .collect(Collectors.groupingBy(BallotModel::getCandidateId, Collectors.counting()));

        long totalVotes = ballots.size();

        List<ResultResponseDTO> results = candidateVoteCount.entrySet().stream()
                .map(entry -> {
                    String candidateId = entry.getKey();
                    long votes = entry.getValue();

                    CandidateModel candidate = candidateRepository.findById(candidateId)
                            .orElseThrow(() -> new IllegalArgumentException("Candidate not found: " + candidateId));
                    PartyModel party = partyRepository.findById(candidate.getPartyId())
                            .orElseThrow(() -> new IllegalArgumentException("Party not found: " + candidate.getPartyId()));

                    return new ResultResponseDTO(
                            candidate.getCandidateId(),
                            candidate.getCandidateName(),
                            party.getPartyId(),
                            party.getPartyName(),
                            votes
                    );
                })
                .sorted((a, b) -> Long.compare(b.getVotes(), a.getVotes()))
                .collect(Collectors.toList());

        // Check if any candidate has ≥ 51% votes
        if (results.get(0).getVotes() * 100.0 / totalVotes >= 51) {
            return List.of(results.get(0)); // Winner only
        }
        return List.of(new ResultResponseDTO(null, "No Winner", null, null, 0L));
    }
}
