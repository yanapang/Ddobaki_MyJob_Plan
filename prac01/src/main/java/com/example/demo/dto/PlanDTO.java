package com.example.demo.dto;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PlanDTO {
	private int plan_group_num;
	private String plan_name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date plan_date;
	ArrayList<RoutDTO> list;
	
	
	//Plan
//	public Plan toPlan() {
//		
//	}
}
