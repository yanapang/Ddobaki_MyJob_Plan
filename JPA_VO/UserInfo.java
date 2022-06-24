package com.example.demo.vo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="userinfo")
public class UserInfo {
	@Id
	private int user_num;
	
	private String user_id;
	private String user_pwd;
	private String user_nick;
	private String user_phone;	
	private String user_file;
	
	@OneToMany(mappedBy="userinfo", fetch=FetchType.EAGER)
	private List<Dog> dog;
	
	@OneToMany(mappedBy="userinfo", fetch=FetchType.EAGER)
	private List<Board> board;
	
	@OneToMany(mappedBy="userinfo", fetch=FetchType.EAGER)
	private List<Reply> reply;
	
	@OneToMany(mappedBy="userinfo", fetch=FetchType.EAGER)
	private List<Plan> plan;
	
	@OneToMany(mappedBy="userinfo", fetch=FetchType.EAGER)
	private List<Dibs> dibs;
	
	@OneToMany(mappedBy="userinfo", fetch=FetchType.EAGER)
	private List<Reservation> reservation;
	
	@OneToMany(mappedBy="userinfo", fetch=FetchType.EAGER)
	private List<Payment> payment;
	
	/* Service 사용시 주석 풀어서 사용!
	@OneToMany(mappedBy="userinfo", fetch=FetchType.EAGER)
	private List<Service> service;
	*/
	
	
}
