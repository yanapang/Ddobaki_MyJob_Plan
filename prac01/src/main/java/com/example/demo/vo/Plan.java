package com.example.demo.vo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
@Table(name="plan")
public class Plan {
	@Id //PK
	private int	plan_num;
	
	//FK
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="user_num", insertable=true, updatable=true, nullable = false)
	private UserInfo userinfo;
	
	//FK
	@ManyToOne
	@JoinColumn(name="place_num", insertable=true, updatable=true, nullable = false)
	private Place place;
	
	@Column(columnDefinition = "varchar2(3000)", nullable = false)
	private String plan_name;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date plan_date;
	@Column(nullable = false)
	private int plan_flow_num;	
	@Column(columnDefinition = "varchar2(3000)", nullable = false)
	private String plan_flow_name;	
	@Column(nullable = false)
	private int plan_group_num;	
	
}
