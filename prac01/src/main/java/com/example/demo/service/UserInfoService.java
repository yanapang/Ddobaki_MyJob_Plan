package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserInfoDAO;
import com.example.demo.vo.UserInfo;

import lombok.Setter;

@Service
@Setter
public class UserInfoService {
	@Autowired
	private UserInfoDAO dao;
	
	public List<UserInfo> findAll(){
		return dao.findAll();
	}
}
