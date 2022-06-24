package com.example.demo.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="plan")
public class Plan {
	@Id //PK
	private int	plan_num;
	
	//FK
	@ManyToOne
	@JoinColumn(name="user_num", insertable=true, updatable=true)
	private UserInfo userinfo;
	
	//FK
	@ManyToOne
	@JoinColumn(name="place_num", insertable=true, updatable=true)
	private Place place;
	
	private String plan_name;	
	private Date plan_date;
	private int plan_flow_num;	
	private String plan_flow_name;	
	private String plan_place_addr;
	private int plan_group_num;	
	
}
