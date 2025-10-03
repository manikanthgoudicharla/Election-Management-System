package com.ems.ems.repository;

//repository
import org.springframework.stereotype.Repository;

//mogorepo
import org.springframework.data.mongodb.repository.MongoRepository;

//model
import com.ems.ems.model.VoterModel;

@Repository
public interface VoterRepository extends MongoRepository<VoterModel,String> {
    
}
