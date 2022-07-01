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
	private PlanDAO plan_dao;
	
	public List<Plan> findAll(){
		return plan_dao.findAll();
	}
	
	public void save(Plan p) {
		plan_dao.save(p);
	}
}
