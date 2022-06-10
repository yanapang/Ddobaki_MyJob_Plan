package com.example.demo.vo;

import java.util.Date;

import lombok.Data;

@Data
public class Payment_VO {
	private int p_num;
	private int r_num;
	private int user_num;
	private Date payment_date;
}
