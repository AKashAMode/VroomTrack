package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Car;

public interface CarRepository extends JpaRepository<Car, Integer>, CarRepositoryCustom{
	
	public List<Car> findByName(String name);
	
	@Query("select car from Car car where car.model = :model")
	public List<Car> findByModel(@Param("model") String model);
	
	@Query("select car from Car car where car.year = :caryear")
	public List<Car> findByYear(@Param("caryear") int year);
	

}
