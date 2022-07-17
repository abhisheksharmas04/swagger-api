package com.example.swaggerapi.service;

import java.util.List;

import com.example.swaggerapi.entity.Tourist;
import com.example.swaggerapi.exception.TouristNotFoundException;

public interface ITouristManagementService {
	public String registerTourist(Tourist tourist);
	public List<Tourist>fetchAllTourist();
	public Tourist fetchTouristById(Integer tid) throws TouristNotFoundException;
	public String updateTouristDetails(Tourist tourist)throws TouristNotFoundException;
	public String deleteTourist(Integer tid) throws TouristNotFoundException;
	public String updateTouristBudgetById(Integer id, Float hikePercentage) throws TouristNotFoundException;
}
