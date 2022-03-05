package com.example.springMongoDBCarsApi.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springMongoDBCarsApi.Models.Cars;
import com.example.springMongoDBCarsApi.Repository.CarsRepository;

@RestController
@RequestMapping("/api/cars")
public class CarsController {

	private final CarsRepository _carsRepository;
	
	@Autowired
	public CarsController(CarsRepository carsRepository) {
		_carsRepository = carsRepository;
	}
	
	@PostMapping
	public ResponseEntity<Cars> createCar(@RequestBody Cars cars) {
	    try {
	    	Cars _car= _carsRepository.save(cars);
	    	return new ResponseEntity<>(_car,HttpStatus.CREATED);
	    } catch (Exception e) {
	    	return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Cars>> getAllCars(){
		
		try {
			List<Cars> cars = new ArrayList<Cars>();
			_carsRepository.findAll().forEach(cars::add);
			
			if (cars.isEmpty()) {
			      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(cars, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cars> getCarById(@PathVariable("id") String id) {
		Optional<Cars> carData = _carsRepository.findByObjectId(new ObjectId(id));

	  if (carData.isPresent()) {
	    return new ResponseEntity<>(carData.get(), HttpStatus.OK);
	  } else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
	}
	
	@GetMapping("/ByMake/{make}")
	public ResponseEntity<List<Cars>> getCarByMake(@PathVariable("make") String make) {
		List<Cars> carsData = _carsRepository.findByMake(make);

		try {
		  if (carsData.isEmpty()) {
		    return new ResponseEntity<>(carsData, HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Boolean> updateTutorial(@PathVariable("id") String id, @RequestBody Cars car) {
	  Optional<Cars> carData = _carsRepository.findByObjectId(new ObjectId(id));
		try {
			if (carData.isPresent()) {
			    Cars _car = carData.get();
			    _car.setMake(car.getMake());
			    _car.setModel(car.getModel());
			    _car.setTopSpeed(car.getTopSpeed());
			    if(_carsRepository.save(_car)!=null) {
			    	return new ResponseEntity<>(true, HttpStatus.OK);
			    }else {
			    	return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
			    }
			    
			  } else {
			    return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
			  }
		}catch (Exception e) {
			return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	  
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
	  try {
	    _carsRepository.deleteById(new ObjectId(id));
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  } catch (Exception e) {
	    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
		
	
	
}
