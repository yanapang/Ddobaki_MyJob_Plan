package com.example.demo.service;

import java.util.List;
import java.util.Optional;

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
	
	//전체 플랜 리턴
	public List<Plan> findAll(){
		return plan_dao.findAll();
	}
	
	//아이디로 플랜 1개 리턴.
	public Optional<Plan> getPlan(int plan_num){
		return plan_dao.findById(plan_num);
	}
	
	public void save(Plan p) {
		plan_dao.save(p);
	}
}
