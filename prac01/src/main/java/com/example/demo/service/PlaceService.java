package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PlaceDAO;
import com.example.demo.vo.Place;

import lombok.Setter;

@Service
@Setter
public class PlaceService {

	@Autowired
	private PlaceDAO dao;
	
	public List<Place> findAll(){
		return dao.findAll();
	}
}
