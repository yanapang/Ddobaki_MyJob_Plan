package com.example.demo.vo;

import java.util.Date;

import lombok.Data;

@Data
public class PaymentVO {
	private int payment_num;
	private int reservation_num;
	private Date payment_date;
	private int user_num;
}
