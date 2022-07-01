package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PlanDAO;
import com.example.demo.vo.Plan;

import lombok.Setter;

@Service
@Setter
public class PlanService {

	@Autowired
	private PlanDAO dao;
	
	public List<Plan> findAll(){
		return dao.findAll();
	}
	
	public void save(Plan p) {
		dao.save(p);
	}
}
