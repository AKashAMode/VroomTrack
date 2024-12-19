package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Car;
import com.example.demo.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {
	
	@Autowired
	private CarService carService;
	
	
	@PostMapping
	public Car addCar(@RequestBody Car car) {
		return carService.saveCar(car);
	}
	
	
	@GetMapping
	public List<Car> getAllCars(){
		return carService.getAllCars();
	}
	
	@GetMapping("/getById/{id}")
	public Car getById(@PathVariable int id) {
		
		return carService.getById(id);
	}
	
	
	@GetMapping("/byName/{name}")
	public List<Car> findByName(@PathVariable String name){
		return carService.findByName(name);
	}
	
	
	@GetMapping("/byModel/{model}")
	public List<Car> findByModel(@PathVariable String model){
		return carService.findByModel(model);
	}
	
	@GetMapping("/byYear/{year}")
	public List<Car> findByYear(@PathVariable int year){
		return carService.findByYear(year);
	}
	
	
	@PutMapping("/{id}")
	public Car updateCar(@PathVariable int id, @RequestBody Car updateCar) {
		return carService.updateCar(id, updateCar);
	}
	
	
	@DeleteMapping("/{id}")
	public String deleteCar(@PathVariable int id) {
		return carService.deleteCar(id);
	}
	
	
	  @GetMapping("/search/{keyword}")
	    public List<Car> globalSearch(@PathVariable String keyword) {
	        return carService.globalSearch(keyword);
	    }

	  
	    @GetMapping("/searchCars")
	    public List<Car> searchCars(
	            @RequestParam(required = false) String keyword,
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int size,
	            @RequestParam(defaultValue = "name:ASC") String sort) {
	        return carService.searchCars(keyword, page, size, sort);
	    }

	    
}
