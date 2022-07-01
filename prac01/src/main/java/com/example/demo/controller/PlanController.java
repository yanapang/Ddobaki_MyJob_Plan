package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.PlaceService;
import com.example.demo.service.PlanService;
import com.example.demo.service.UserInfoService;
import com.example.demo.vo.Plan;

import lombok.Setter;

@Controller
@Setter
public class PlanController {
	// ----  service 객체 생성. ------
	@Autowired
	private UserInfoService userS;
	
	@Autowired
	private PlaceService placeS;
	
	@Autowired
	private PlanService planS;
	
	//----------------- 메소드 -----------
	@GetMapping("/listPlan")
	@ResponseBody
	public List<Plan> findAll(){
		return planS.findAll();
	}
	
	@GetMapping("/insertPlan")
	public void insert(Model model) {
		model.addAttribute("user_list", userS.findAll());
		model.addAttribute("place_list", placeS.findAll());
		model.addAttribute("plan_list", planS.findAll());
	}
	
	@PostMapping("/insertPlan")
	@ResponseBody
	public String insertOK(Plan p) {
		planS.save(p);
		return "insert Plan OK";
	}
	
}
