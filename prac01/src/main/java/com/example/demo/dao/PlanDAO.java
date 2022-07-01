package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.Plan;

@Repository
public interface PlanDAO extends JpaRepository<Plan, Integer> {

}
