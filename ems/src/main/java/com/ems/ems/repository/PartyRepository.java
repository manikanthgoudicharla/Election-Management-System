package com.ems.ems.repository;

//repository
import org.springframework.stereotype.Repository;

// mongo repo
import org.springframework.data.mongodb.repository.MongoRepository;

//import model
import com.ems.ems.model.PartyModel;

@Repository
public interface PartyRepository extends MongoRepository<PartyModel,String> {
    
}
