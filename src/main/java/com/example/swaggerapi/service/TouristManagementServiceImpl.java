package com.example.swaggerapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.swaggerapi.entity.Tourist;
import com.example.swaggerapi.exception.TouristNotFoundException;
import com.example.swaggerapi.repo.ITouristRepo;

@Service
public class TouristManagementServiceImpl implements ITouristManagementService {
	
	@Autowired
	private ITouristRepo touristRepo;

	@Override
	public String registerTourist(Tourist tourist) {
		return "Tourist is register having id:: " + touristRepo.save(tourist).getTId();
	}

	@Override
	public List<Tourist> fetchAllTourist() {
		List<Tourist> touristList = touristRepo.findAll();
		touristList.sort((t1,t2) -> t1.getTId().compareTo(t2.getTId()));
		return touristList;
	}

	@Override
	public Tourist fetchTouristById(Integer tid) throws TouristNotFoundException {
		return touristRepo.findById(tid).orElseThrow( () -> new TouristNotFoundException("Tourist Not Found with id:: " + tid));
	}

	@Override
	public String updateTouristDetails(Tourist tourist) throws TouristNotFoundException {
		Tourist t = touristRepo.findById(tourist.getTId()).orElseThrow(() -> new TouristNotFoundException("Tourist not found with id:: " + tourist.getTId()));
		touristRepo.save(tourist);
		return t.getTId() + " Object is updated";
	}

	@Override
	public String deleteTourist(Integer tid) throws TouristNotFoundException {
		Tourist t = touristRepo.findById(tid).orElseThrow(() -> new TouristNotFoundException("Tourist not found with id:: " + tid));
		touristRepo.deleteById(tid);
		return "Tourist objected delete with id:: " + tid;
	}

	@Override
	public String updateTouristBudgetById(Integer tid, Float hikePercentage) throws TouristNotFoundException {
		Tourist t = touristRepo.findById(tid).orElseThrow(() -> new TouristNotFoundException("Tourist not found with id:: " + tid));
		t.setBudget(t.getBudget() + (t.getBudget() * (hikePercentage/100)));
		touristRepo.save(t);
		return "Tourist Budget is update";
	}

}
