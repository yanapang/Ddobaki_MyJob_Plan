package com.example.demo.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.vo.Place;
import com.example.demo.vo.Plan;
import com.example.demo.vo.UserInfo;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PlanDTO {
	
	//추가 
	private int user_num;
	private int plan_num;
	
	private int plan_group_num;
	private String plan_name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date plan_date;
	
	// 날짜별 동선을 동선DTO 배열로 입력받는다. 
	ArrayList<RouteDTO> list;

	
	//DTO를 입력받아 Plan으로 반환하는 메소드 
	public ArrayList<Plan> toPlan(ArrayList<RouteDTO> route_list, int nxtNum ) {
		
		ArrayList<Plan> plan_list = new ArrayList<>();
		
		for (RouteDTO r:route_list) {
			Plan p = new Plan();
			System.out.println("r: "+ r +'\n');
			
			p.setPlan_num(nxtNum++);
			p.setPlan_group_num(plan_group_num);
			p.setPlan_name(plan_name);
			p.setPlan_date(plan_date);
			
			//fk userInfo-user_num
			UserInfo userInfo = new UserInfo();
			userInfo.setUser_num(user_num);
			p.setUserinfo(userInfo);
			
			//fk place-place_num
			Place place = new Place();
			place.setPlace_num(r.getPlace_num());
			p.setPlace(place);
			
			p.setPlan_flow_name(r.getPlan_flow_name());
			p.setPlan_flow_num(r.getPlan_flow_num());
			
			plan_list.add(p);
		}

		return plan_list;
	}
}
