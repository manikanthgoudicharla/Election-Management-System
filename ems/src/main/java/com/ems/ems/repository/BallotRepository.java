package com.ems.ems.repository;

//repository
import org.springframework.stereotype.Repository;

import java.util.List;

//mongo repo
import org.springframework.data.mongodb.repository.MongoRepository;

//model
import com.ems.ems.model.BallotModel;

@Repository
public interface BallotRepository extends MongoRepository<BallotModel,String>{
    boolean existsByElectionIdAndVoterId(String electionId, String voterId);
    List<BallotModel> findByElectionId(String electionId);

}
