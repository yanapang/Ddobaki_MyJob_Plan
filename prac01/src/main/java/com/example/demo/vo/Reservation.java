package com.example.demo.vo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "reservation")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reservation_num;
	
	
	@Column(columnDefinition = "number default 0", nullable = false)
	private int reservation_park;
	
	@Column(columnDefinition = "number default 0", nullable = false)
	private int reservation_spa;
	
	@Column(columnDefinition = "number default 0", nullable = false)
	private int reservation_meal_cnt;
	
	@Column(nullable = false)
	private int reservation_price;
	
	@Column(nullable = false)
	private Date reservation_checkin_date;
	
	@Column(nullable = false)
	private Date reservation_checkout_date;
	
	@Column(columnDefinition = "number default 1", nullable = false)
	private int reservation_people_cnt;
	
	@Column(columnDefinition = "number default 0", nullable = false)
	private int reservation_s_dog_cnt;
	
	@Column(columnDefinition = "number default 0", nullable = false)
	private int reservation_m_dog_cnt;
	
	@Column(columnDefinition = "number default 0", nullable = false)
	private int reservation_l_dog_cnt;
	
	
	@ManyToOne
	@JoinColumn(name = "user_num", insertable = true, updatable = true)
	private UserInfo userinfo;
	
	@ManyToOne
	@JoinColumn(name = "place_num", insertable = true, updatable = true)
	private Place place;
	
	@ManyToOne
	@JoinColumn(name = "room_num", insertable = true, updatable = true)
	private Room room;
	
	@OneToOne(mappedBy = "reservation", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Payment payment; 
	
	
	//참조 받아야 하는 키
	//user_num, place_num, room_num
}
