package com.example.demo.vo;

import java.util.Date;

import lombok.Data;

@Data 
//예약 테이블 VO
public class ReservationVO {
	private int reservation_num;
	private int user_num;
	private int place_num;
	private int room_num;
	private int reservation_park;
	private int reservation_spa;
	private int reservation_meal_cnt;
	private int reservation_price;
	private Date reservation_checkin_date;
	private Date reservation_checkout_date;
	private int reservation_people_cnt;
	private int reservation_s_dog_cnt;
	private int reservation_m_dog_cnt;
	private int reservation_l_dog_cnt;
}
