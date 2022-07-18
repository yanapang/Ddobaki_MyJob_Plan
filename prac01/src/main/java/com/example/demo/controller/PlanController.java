package com.example.demo.controller;

import java.util.ArrayList;
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

import com.example.demo.dto.PlanDTO;
import com.example.demo.dto.RouteDTO;
import com.example.demo.service.DibsService;
import com.example.demo.service.PlaceService;
import com.example.demo.service.PlanService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.UserInfoService;
import com.example.demo.vo.Dibs;
import com.example.demo.vo.Place;
import com.example.demo.vo.Plan;
import com.example.demo.vo.Reservation;
import com.example.demo.vo.UserInfo;

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
	
	@GetMapping("/findByUserNum")
	//@GetMapping("/findByUserNum/{user_num}")
	@ResponseBody
	public List<Plan> findByUserNum(@RequestParam int user_num){
		return planS.findByUserNum(user_num);
	}
	@GetMapping("/findByGroupNum")
	@ResponseBody
	public List<Plan> findByGroupNum(@RequestParam int plan_group_num){
		return planS.findByGroupNum(plan_group_num);
	}
	@GetMapping("/findByPlanDate")
	@ResponseBody
	public List<Plan> findByPlanDate(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Date plan_date){
		return planS.findByPlanDate(plan_date);
	}
	@GetMapping("/findByAll")
	@ResponseBody
	public List<Plan> findByUserNumAndGroupNumAndPlanDate(@RequestParam int user_num, 
			@RequestParam int plan_group_num, @DateTimeFormat(pattern = "yyyy-MM-dd") 
			@RequestParam Date plan_date){
		return planS.findByUserNumAndGroupNumAndPlanDate(user_num, plan_group_num, plan_date);
	}
	
	@GetMapping("/findByDidsUserNum")
	@ResponseBody
	public List<Dibs> findByDibsUserNum(@RequestParam int user_num){
		return dibsS.findByUserNum(user_num);
	}
	
	@GetMapping("/findByReservationUserNum")
	@ResponseBody
	public List<Reservation> findByReservationUserNum(@RequestParam int user_num){
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
	@GetMapping("/getNextFlowNum")
	@ResponseBody
	public int getNextFlowNum(@RequestParam int plan_group_num, @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Date plan_date) {
		return planS.getNextFlowNum(plan_group_num, plan_date);
	}//주소창 요청 방법 ex) => http://localhost:8080/getNextFlowNum/1/2022-07-23
	
	//------------------------- 입력 및 수정 --------------------------------
	
	@GetMapping("/plan/insertPlan/{user_num}") //플랜 입력창으로 이동
	public String insert(Model model, @PathVariable int user_num) {
		//유저번호로 유저 정보 상태유지, 로그인 구현후 session 저장값 사용예정 
		model.addAttribute("user", userS.getUser(user_num)); 
		//DB내 모든 장소리스트 
		model.addAttribute("place_list", placeS.findAll()); 
		
		//사용자별 여행계획 리스트 
		model.addAttribute("user_plan_list", planS.findDistinctByUserNum(user_num));

		//사용자별 찜, 예약리스트
		model.addAttribute("dibs", dibsS.findByUserNum(user_num));
		model.addAttribute("reservation", resvS.findByUserNum(user_num));
		
		return "/insertPlan"; //model에값들 담고 insertPlan페이지로 입력 받으러 리디렉션!
	}

	
	@PostMapping("/savePlan/{user_num}") //플랜 저장 
	public ModelAndView save(PlanDTO pDTO, RouteDTO rDTO, @PathVariable int user_num) {
		//System.out.println(pDTO);
		
		//날짜별동선을 입력받을 리스트 생성 
		ArrayList<RouteDTO> route_list = pDTO.getList();
		
		//새로운 pk 값을 리턴받아 저장하기 위해 getNextNum을 통해 값을 받아오
		int nxtNum = planS.getNextPlanNum();
		//입력 받은 날짜별 동선 DTO와, pk값을 갖고 Plan 엔티티에 매핑 시킨 후 리스트로 반환받아 저장.
		ArrayList<Plan> plan_list = pDTO.toPlan(route_list);
		for(Plan i : plan_list ) {
			System.out.println("Save에서 받은 Plan객체 : "+i+"\n");
		}
		
		for (Plan p: plan_list) {//반환 받은 플랜 리스트만큼 save 돌기 
			if( p.getPlan_num() != 0) {
				planS.save(p);
			}
		}
		
		ModelAndView mav = new ModelAndView(); //save 후 다시 insertPlan페이지로 이동!
		mav.setView(new RedirectView("/plan/insertPlan/"+user_num)); 
		return mav;
	}
	
	
	//---------------------------- 삭제 --------------------------------
	@RequestMapping("/deleteByPlanNum")
	@ResponseBody
	public void deleteById(@RequestParam int plan_num) {
		planS.deleteById(plan_num);
		//return "deleteByPlanNum_OK";
	}
	
	@GetMapping("/deleteByGroupNum")
	@ResponseBody
	public void deleteByGroupNum(@RequestParam int plan_group_num) {
		planS.deleteByGroupNum(plan_group_num);
		//return "deleteByPlanNum_OK";
	}
	
	@GetMapping("/deleteByPlanDate")
	@ResponseBody
	public void deleteByPlanDate(@RequestParam int plan_group_num, @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Date plan_date) {
		planS.deleteByPlanDate(plan_group_num, plan_date);
	}
}
