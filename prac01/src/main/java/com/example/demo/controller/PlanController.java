package com.example.demo.controller;

import java.util.Date;
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

import com.example.demo.service.DibsService;
import com.example.demo.service.PlaceService;
import com.example.demo.service.PlanService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.UserInfoService;
import com.example.demo.vo.Dibs;
import com.example.demo.vo.Plan;
import com.example.demo.vo.Reservation;

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
	
	@Autowired
	private DibsService dibsS;
	
	@Autowired
	private ReservationService resvS;
	
	// ----------------- 메소드 -----------
	// ----------------- 리스트 ---------------------
	@GetMapping("/listPlan") //전체 플랜 리스트 반환. 현재 json형식으로 반환됨. 향후 뷰페이지에서보이게 수정예정.
	@ResponseBody
	public List<Plan> findAll(){
		return planS.findAll();
	}
	
	@RequestMapping("/findByUserNum")
	//@GetMapping("/findByUserNum/{user_num}")
	@ResponseBody
	public List<Plan> findByUserNum(@RequestParam("user_num") int user_num){
		return planS.findByUserNum(user_num);
	}
	@GetMapping("/findByGroupNum/{plan_group_num}")
	@ResponseBody
	public List<Plan> findByGroupNum(@PathVariable int plan_group_num){
		return planS.findByGroupNum(plan_group_num);
	}
	@GetMapping("/findByPlanDate/{plan_date}")
	@ResponseBody
	public List<Plan> findByPlanDate(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable Date plan_date){
		return planS.findByPlanDate(plan_date);
	}
	@GetMapping("/findByAll/{user_num}/{plan_group_num}/{plan_date}")
	@ResponseBody
	public List<Plan> findByUserNumAndGroupNumAndPlanDate(@PathVariable int user_num, @PathVariable int plan_group_num, @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable Date plan_date){
		return planS.findByUserNumAndGroupNumAndPlanDate(user_num, plan_group_num, plan_date);
	}
	
	@GetMapping("/findByDidsUserNum/{user_num}")
	@ResponseBody
	public List<Dibs> findByDibsUserNum(@PathVariable int user_num){
		return dibsS.findByUserNum(user_num);
	}
	
	@GetMapping("/findByReservationUserNum/{user_num}")
	@ResponseBody
	public List<Reservation> findByReservationUserNum(@PathVariable int user_num){
		return resvS.findByUserNum(user_num);
	}
	
	// ----------------- GetNextNum ---------------------
	@GetMapping("/getNextPlanNum")
	@ResponseBody
	public int getNextPlanNum() {
		return planS.getNextPlanNum();
	}
	@GetMapping("/getNextGroupNum")
	@ResponseBody
	public int getNextGroupNum() {
		return planS.getNextGroupNum();
	}
	@GetMapping("/getNextFlowNum/{plan_group_num}/{plan_date}")
	@ResponseBody
	public int getNextFlowNum(@PathVariable int plan_group_num, @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable Date plan_date) {
		return planS.getNextFlowNum(plan_group_num, plan_date);
	}//주소창 요청 방법 ex) => http://localhost:8080/getNextFlowNum/1/2022-07-23
	
	//------------------------- 입력 및 수정 --------------------------------
	
	@GetMapping("/insertPlan/{user_num}") //플랜 입력창으로 이동
	public String insert(Model model, @PathVariable int user_num) {
		//유저 번호를 함께 입력 받아 해당 유저 정보 model에유지.
		//로그인 구현 후 httpSession에 담긴 값을 활용할 예정. 
		model.addAttribute("user", userS.getUser(user_num)); 
		
		//플랜과 플레이스 전체값 model로 유지.
		model.addAttribute("plan_list", planS.findByUserNum(user_num));  
		
		//distinct 먹인플랜그룹리스
//		model.addAttribute("plan_group_list", planS.findPlanGroupNum(user_num));
		
		//플랜은 향후 해당 유저의 플랜만 입력 뷰페이지로 보내는 방식으로 수정 예정?
		model.addAttribute("place_list", placeS.findAll()); 
		
		 //pk값 갖고 감!
		model.addAttribute("plannum", planS.getNextPlanNum());
		//새로운 groupnum
		model.addAttribute("groupnum", planS.getNextGroupNum());
		
		//찜, 예약리스트 실어주기
		model.addAttribute("dibs", dibsS.findByUserNum(user_num));
		
		model.addAttribute("reservation", resvS.findByUserNum(user_num));
		
		
		return "/insertPlan"; //model에값들 담고 insertPlan페이지로 입력 받으러 리디렉션!
	}

	
	@PostMapping("/savePlan") //플랜 저장 
	public ModelAndView save(Plan p) {
		planS.save(p); //insert & update
		ModelAndView mav = new ModelAndView(); //save 메소드 실행 후 listPlan으로 일단 리디렉션 설정.
		mav.setView(new RedirectView("/listPlan")); //향후 해당 user_num에 따른 listPlan만 보여주게 설정할예정!
		return mav;
	}
	
	
	//---------------------------- 삭제 --------------------------------
	@GetMapping("/deleteByPlanNum/{plan_num}")
	@ResponseBody
	public void deleteById(@PathVariable int plan_num) {
		planS.deleteById(plan_num);
		//return "deleteByPlanNum_OK";
	}
	
	@GetMapping("/deleteByGroupNum/{plan_group_num}")
	@ResponseBody
	public void deleteByGroupNum(@PathVariable int plan_group_num) {
		planS.deleteByGroupNum(plan_group_num);
		//return "deleteByPlanNum_OK";
	}
	
	@GetMapping("/deleteByPlanDate/{plan_group_num}/{plan_date}")
	@ResponseBody
	public void deleteByPlanDate(@PathVariable int plan_group_num, @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable Date plan_date) {
		planS.deleteByPlanDate(plan_group_num, plan_date);
	}
}
