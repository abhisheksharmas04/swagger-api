package com.example.swaggerapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swaggerapi.entity.Tourist;
import com.example.swaggerapi.exception.TouristNotFoundException;
import com.example.swaggerapi.service.ITouristManagementService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tourist")
@Api("All About Tourist")
public class TouristOperationsController {
	
	@Autowired
	private ITouristManagementService service;
	
	@PostMapping("/register")
	@ApiOperation("For Tourist Registration")
	public ResponseEntity<String> enrollTourist(@RequestBody Tourist tourist){
		// use service
		try {
			String resultMsg = service.registerTourist(tourist);
			return new ResponseEntity<String>(resultMsg, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Problem in tourist endrollment", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> getAllTourist(){
		try {
			List<Tourist> touristList = service.fetchAllTourist();
			return new ResponseEntity<List<Tourist>>(touristList,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Problem in fetching tourist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?>displayTouristById(@PathVariable("id") Integer id){
		try {
			 return new ResponseEntity<>(service.fetchTouristById(id),HttpStatus.OK);
		} catch (TouristNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/modify")
	public ResponseEntity<String>updateTourist(@RequestBody Tourist tourist){
		try {
			return new ResponseEntity<String>(service.updateTouristDetails(tourist),HttpStatus.OK);
		} catch (TouristNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<String>deleteTouristById(@PathVariable("id") Integer id){
		try {
			return new ResponseEntity<String>(service.deleteTourist(id), HttpStatus.OK);
		} catch (TouristNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping("/budgetModify/{id}/{hike}")
	public ResponseEntity<String>updateBudget(@PathVariable("id")Integer id, @PathVariable("hike") Float hikePer){
		try {
			return new ResponseEntity<String>(service.updateTouristBudgetById(id,hikePer), HttpStatus.OK);
		} catch (TouristNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
