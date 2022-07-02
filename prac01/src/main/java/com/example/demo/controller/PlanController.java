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
	@GetMapping("/listPlan") //전체 플랜 리스트 반환. 현재 json형식으로 반환됨. 향후 뷰페이지에서보이게 수정예정.
	@ResponseBody
	public List<Plan> findAll(){
		return planS.findAll();
	}
	
	@GetMapping("/insertPlan/{user_num}") //플랜 입력  
	public String insert(Model model, @PathVariable int user_num) {
		//유저 번호를 함께 입력 받아 해당 유저 정보 model에유지.
		//로그인 구현 후 httpSession에 담긴 값을 활용할 예정. 
		model.addAttribute("user", userS.getUser(user_num)); 
		model.addAttribute("plan_list", planS.findAll());  //플랜과 플레이스 전체값 model로 유지.
		model.addAttribute("place_list", placeS.findAll()); //플랜은 향후 해당 유저의 플랜만 입력 뷰페이지로 보내는 방식으로 수정 예정?
		model.addAttribute("plannum", planS.getNextPlanNum());
		return "/insertPlan"; //model에값들 담고 insertPlan페이지로 입력 받으러 리디렉션!
	}

	
	@PostMapping("/savePlan") //플랜 저장 
	public ModelAndView save(Plan p) {
		planS.save(p); //insert & update
		ModelAndView mav = new ModelAndView(); //save 메소드 실행 후 listPlan으로 일단 리디렉션 설정.
		mav.setView(new RedirectView("/listPlan")); //향후 해당 user_num에 따른 listPlan만 보여주게 설정할예정!
		return mav;
	}
	
}
