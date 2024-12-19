package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Car;

public interface CarRepositoryCustom {
	
	   List<Car> globalSearch(String keyword);

	    
	    List<Car> searchCars(String keyword, int page, int size, String sort);
	
	

}
