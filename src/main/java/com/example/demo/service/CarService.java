package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Car;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CarRepository;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	
	public Car saveCar(Car car){
	return carRepository.save(car);
	
	}
	

	public List<Car> getAllCars(){
	return carRepository.findAll();
	}
	
	
	public Car getById(int id) {
	    return carRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Car with ID " + id + " not found"));
	}
	

	
	public List<Car> findByName(String name){
		
		List<Car> cars = carRepository.findByName(name);
		
		if(cars.isEmpty()) {
			throw new ResourceNotFoundException(" No cars found with this name : " + name);
		}else {
			return cars; 
		}
	}
	
	
	public List<Car> findByModel(String model){
		List<Car> models = carRepository.findByModel(model);
		
		if(models.isEmpty()) {
			throw new ResourceNotFoundException("Model not Found : " + model);
		}else {
			return models;
		}
	}
	
	
	public List<Car> findByYear(int year){
	
		List<Car> years = carRepository.findByYear(year);
		
		if(years.isEmpty()) {
			throw new ResourceNotFoundException("year is not found :" + year);
		}else {
			return years;
		}
	}
	
	
	public Car updateCar(int id, Car updatedCar) {
	    getById(id); 
	    updatedCar.setId(id); 
	    return carRepository.save(updatedCar); 
	}
	
	
	public String deleteCar(int id) {
		getById(id);
		carRepository.deleteById(id);
		return "Car Deleted successfully with Id :" + id;
	}
	

	public List<Car> globalSearch(String keyword) {
        return carRepository.globalSearch(keyword); 
    }

   
    public List<Car> searchCars(String keyword, int page, int size, String sort) {
        return carRepository.searchCars(keyword, page, size, sort);
    }
	
	
}
