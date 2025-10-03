package com.ems.ems.repository;

// Repository
import org.springframework.stereotype.Repository;

//mongo repo
import org.springframework.data.mongodb.repository.MongoRepository;

//model
import com.ems.ems.model.CandidateModel;

@Repository
public interface CandidateRepository extends MongoRepository<CandidateModel,String> {
    
}
