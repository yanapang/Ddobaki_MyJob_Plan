package com.example.demo.vo;

import java.util.Date;

import lombok.Data;
@Data
public class PlanVO {
	private int plan_num;
	private int user_num;
	private int place_num;
	private String plan_name;
	private Date plan_date;
	private int flow_num;
	private String flow_name;
	private String place_addr;
	private int plan_id;
}
