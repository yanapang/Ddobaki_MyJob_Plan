package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.Dibs;


@Repository
public interface DibsDAO extends JpaRepository<Dibs, Integer> {

	@Query("SELECT d FROM Dibs d where user_num=:user_num")
	public List<Dibs> findByUserNum(int user_num);
}
