package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Car;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class CarRepositoryCustomImpl implements CarRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    
    @Override
    public List<Car> globalSearch(String keyword) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = cb.createQuery(Car.class);
        Root<Car> root = query.from(Car.class);

        List<Predicate> predicates = new ArrayList<>();
        
        if (keyword != null && !keyword.isEmpty()) {
            
            predicates.add(cb.like(root.get("name"), "%" + keyword + "%"));
            predicates.add(cb.like(root.get("model"), "%" + keyword + "%"));
            predicates.add(cb.like(root.get("color"), "%" + keyword + "%"));
            predicates.add(cb.like(root.get("fuelType"), "%" + keyword + "%"));
            
           
            try {
                Integer year = Integer.parseInt(keyword); 
                predicates.add(cb.equal(root.get("year"), year));  
            } catch (NumberFormatException e) {
                
            }
        }

        query.where(cb.or(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();
    }


    
    @Override
    public List<Car> searchCars(String keyword, int page, int size, String sort) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = cb.createQuery(Car.class);
        Root<Car> root = query.from(Car.class);

        List<Predicate> predicates = new ArrayList<>();

        if (keyword != null && !keyword.isEmpty()) {
            predicates.add(cb.like(root.get("name"), "%" + keyword + "%"));
            predicates.add(cb.like(root.get("model"), "%" + keyword + "%"));
            predicates.add(cb.like(root.get("color"), "%" + keyword + "%"));
            predicates.add(cb.like(root.get("fuelType"), "%" + keyword + "%"));

            
            try {
                int yearValue = Integer.parseInt(keyword);
                predicates.add(cb.equal(root.get("year"), yearValue));
            } catch (NumberFormatException e) {
                
            }
        }

        query.where(cb.or(predicates.toArray(new Predicate[0])));

       
        if (sort != null && !sort.isEmpty()) {
            String[] sortParams = sort.split(",");
            for (String sortParam : sortParams) {
                String[] sortDetails = sortParam.split(":");
                String sortField = sortDetails[0];
                String sortOrder = sortDetails[1].toUpperCase();

                if ("ASC".equals(sortOrder)) {
                    query.orderBy(cb.asc(root.get(sortField)));
                } else if ("DESC".equals(sortOrder)) {
                    query.orderBy(cb.desc(root.get(sortField)));
                }
            }
        }

       
        return entityManager.createQuery(query)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    
    
}

