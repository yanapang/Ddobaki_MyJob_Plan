package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.Reservation;

@Repository
public interface ReservationDAO extends JpaRepository<Reservation, Integer> {

	@Query("SELECT r FROM Reservation where user_num=:user_num")
	public List<Reservation> findByUserNum(int user_num);
}
