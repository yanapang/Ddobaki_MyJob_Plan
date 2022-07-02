package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
	
	@GetMapping("/insertPlan/{user_num}")
	public String insert(Model model, @PathVariable int user_num) {
		model.addAttribute("user", userS.getUser(user_num));
		model.addAttribute("plan_list", planS.findAll());
		model.addAttribute("place_list", placeS.findAll());
		model.addAttribute("plannum", planS.getNextPlanNum());
		return "/insertPlan";
	}

	
	@PostMapping("/savePlan")
	//@ResponseBody
	public ModelAndView save(Plan p) {
		//System.out.println("ok");
		planS.save(p);
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView("/listPlan"));
		return mav;
	}
	
}
