package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DibsDAO;
import com.example.demo.vo.Dibs;

import lombok.Setter;

@Service
@Setter
public class DibsService {

	@Autowired
	private DibsDAO dibs_dao;
	
	public List<Dibs> findByUserNum(int user_num) {
		return dibs_dao.findByUserNum(user_num);
	}
}
