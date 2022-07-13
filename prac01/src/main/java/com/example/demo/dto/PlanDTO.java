package com.example.demo.dto;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PlanDTO {
	private int plan_group_num;
	private String plan_name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date plan_date;
	ArrayList<RoutDTO> list;
}
