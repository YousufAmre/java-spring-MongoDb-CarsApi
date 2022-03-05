package com.example.springMongoDBCarsApi.Repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.springMongoDBCarsApi.Models.Cars;

public interface CarsRepository extends MongoRepository<Cars, String>{
	
	@Query("{'_id': ?0}")
	Optional<Cars> findByObjectId(ObjectId id);
	
	@Query(value="{'id' : $0}", delete = true)
	Cars deleteById (ObjectId id);
	
	List<Cars> findByMake(String make);
}
