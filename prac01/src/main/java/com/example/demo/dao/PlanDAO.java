package com.example.demo.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.Plan;

@Repository
public interface PlanDAO extends JpaRepository<Plan, Integer> {

	//----------------------- getNextNum --------------------------
	//pk값 반환; 한 장소 단위.
	@Query("SELECT NVL(MAX(plan_num),0)+1 FROM Plan")
	public int getNextPlanNum();
	
	//새로운 여행계획 그룹 번호 반환.
	//테이블에서 새로운 plan_group_num 받아오기 
	@Query("SELECT NVL(MAX(plan_group_num),0)+1 FROM Plan")
	public int getNextGroupNum();
	
	//룹번호, 날짜별 다음 flow_num 받아오기 
	@Query("SELECT NVL(MAX(plan_flow_num),0)+1 FROM Plan WHERE plan_group_num=:plan_group_num AND plan_date=:plan_date")
	public int getNextFlowNum(@Param("plan_group_num") int plan_group_num, @Param("plan_date") Date plan_date);

	
	
	// ----------------------- findBy -----------------------------
	@Query("SELECT p FROM Plan p WHERE plan_group_num=:plan_group_num")
	public List<Plan> findByGroupNum(@Param("plan_group_num") int plan_group_num);
	
	@Query("SELECT p FROM Plan p WHERE user_num=:user_num")
	public List<Plan> findByUserNum(@Param("user_num") int user_num);
	
	@Query("SELECT p FROM Plan p WHERE plan_date=:plan_date")
	public List<Plan> findByPlanDate(@Param("plan_date") Date plan_date);
	
	@Query("SELECT p FROM Plan p WHERE user_num=:user_num AND plan_group_num=:plan_group_num AND plan_date=:plan_date")
	public List<Plan> findByUserNumAndGroupNumAndPlanDate(@Param("user_num") int user_num, @Param("plan_group_num") int plan_group_num, @Param("plan_date") Date plan_date);
	
	//------------------------delete-----------------------------------------
	//delete(플랜내역하나삭제(기본제공), 날짜별삭제, 플랜 그룹삭제)
	@Modifying
	@Transactional
	@Query(value ="DELETE FROM Plan p WHERE p.plan_num=:#{#plan_num}", nativeQuery = true)
	public void deleteById(@Param("plan_num") int plan_num); //플랜그룹 전체삭제
	
	@Transactional
	@Modifying
	@Query(value ="DELETE FROM Plan p WHERE p.plan_group_num=:#{#plan_group_num}", nativeQuery = true)
	public void deleteByPlanGroupNum(@Param("plan_group_num") int plan_group_num); //플랜그룹 전체삭제
	
	@Transactional
	@Modifying
	@Query(value ="DELETE FROM Plan p WHERE p.plan_group_num=:#{#plan_group_num} AND p.plan_date=:#{#plan_date}", nativeQuery = true)
	public void deleteByPlanGroupNumAndPlanDate(@Param("plan_group_num") int plan_group_num, @Param("plan_date") Date plan_date); //플랜내 날짜별 삭제 
	
	
}

