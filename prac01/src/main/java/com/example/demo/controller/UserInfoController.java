package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.UserInfoService;
import com.example.demo.vo.UserInfo;

import lombok.Setter;

@Controller
@Setter
public class UserInfoController {
	@Autowired
	private UserInfoService user;
	
	@GetMapping("/listUser")
	@ResponseBody
	public List<UserInfo> findAll(){
		return user.findAll();
	}
	
	@GetMapping("/getUser/{user_num}")
	@ResponseBody
	public Optional<UserInfo> getUser(@PathVariable int user_num) {
		return user.getUser(user_num);
	}
	
}
