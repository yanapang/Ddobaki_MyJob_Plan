package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.Plan;

@Repository
public interface PlanDAO extends JpaRepository<Plan, Integer> {

	//pk값 반환 
	@Query("select nvl(max(plan_num),0)+1 from Plan")
	public int getNextPlanNum();
	
	
}

