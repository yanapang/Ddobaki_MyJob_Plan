package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PlaceDAO;
import com.example.demo.vo.Place;

import lombok.Setter;

@Service
@Setter
public class PlaceService {

	@Autowired
	private PlaceDAO place_dao;
	
	public List<Place> findAll(){
		return place_dao.findAll();
	}
	
	public Place getPlace(int place_num) {
		return place_dao.findById(place_num).get();
	}
	
}

