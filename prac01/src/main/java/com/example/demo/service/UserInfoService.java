package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserInfoDAO;
import com.example.demo.vo.UserInfo;

import lombok.Setter;

@Service
@Setter
public class UserInfoService {
	@Autowired
	private UserInfoDAO user_dao;
	
	public List<UserInfo> findAll(){
		return user_dao.findAll();
	}
	
	public UserInfo getUser(int user_num) {
		return user_dao.findById(user_num).get();
	}
	
}
