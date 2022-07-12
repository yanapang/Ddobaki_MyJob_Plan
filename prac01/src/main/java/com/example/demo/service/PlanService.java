package com.example.demo.service;

import java.util.Date;
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
	
	//--------getnextnum----------
	public int getNextPlanNum() {
		return plan_dao.getNextPlanNum();
	}
	
	public int getNextGroupNum() {
		return plan_dao.getNextGroupNum();
	}
	
	//여행 계획, 날짜에 맞는 새로운 flownum 받기 
	public int getNextFlowNum(int plan_group_num, Date plan_date) {
		return plan_dao.getNextFlowNum(plan_group_num, plan_date);
	}
	
	//-----------------------------------
	//전체 플랜 리턴
	public List<Plan> findAll(){
		return plan_dao.findAll();
	}
	//아이디로 플랜 1개 리턴.
	public Plan getPlan(int plan_num){
		return plan_dao.findById(plan_num).get();
	}
	
	//입력 및 수정  
	public void save(Plan p) {
		plan_dao.save(p);
	}
	
	//------- findBy ------
	public List<Plan> findByGroupNum(int plan_group_num){
		return plan_dao.findByGroupNum(plan_group_num);
	}
	public List<Plan> findByPlanDate(Date plan_date){
		return plan_dao.findByPlanDate(plan_date);
	}
	public List<Plan> findByUserNum(int user_num){
		return plan_dao.findByUserNum(user_num);
	}
	public List<Plan> findByUserNumAndGroupNumAndPlanDate(int user_num, int plan_group_num, Date plan_date){
		return plan_dao.findByUserNumAndGroupNumAndPlanDate(user_num, plan_group_num, plan_date);
	}
	
	//------plan group num ---
//	public List<Object[]> findPlanGroupNum(int user_num){
//		return plan_dao.findPlanGroupNum(user_num);
//	}
	
	//----------- delete ----------
	//계획 1개 단위 삭제 (기본제공)
	public void deleteById(int plan_num) {
		plan_dao.deleteById(plan_num);
	}
	
	//계획 그룹 전체 삭제 
	public void deleteByGroupNum(int plan_group_num) {
		plan_dao.deleteByPlanGroupNum(plan_group_num);
	}
	
	//날짜별 삭제
	public void deleteByPlanDate(int plan_group_num, Date plan_date) {
		plan_dao.deleteByPlanGroupNumAndPlanDate(plan_group_num, plan_date);
	}
	
}
