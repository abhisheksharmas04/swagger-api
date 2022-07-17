package com.example.swaggerapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.swaggerapi.entity.Tourist;

public interface ITouristRepo extends JpaRepository<Tourist, Integer> {

}