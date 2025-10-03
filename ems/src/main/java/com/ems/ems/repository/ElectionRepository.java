package com.ems.ems.repository;

//Respository
import org.springframework.stereotype.Repository;

//import mogorepo
import org.springframework.data.mongodb.repository.MongoRepository;

//import model
import com.ems.ems.model.ElectionModel;

@Repository
public interface ElectionRepository extends MongoRepository<ElectionModel,String> {
    
}

