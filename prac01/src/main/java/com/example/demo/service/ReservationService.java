package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ReservationDAO;
import com.example.demo.vo.Reservation;

import lombok.Setter;

@Service
@Setter
public class ReservationService {

	@Autowired
	private ReservationDAO reservation_dao;
	
	public List<Reservation> findAll() {
		return reservation_dao.findAll();
	}
	
	public List<Reservation> findByUserNum(int user_num){
		return reservation_dao.findByUserNum(user_num);
	}
}
