package com.example.demo.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="dog")
public class Dog {
	@Id //PK
	private int dog_num;
	
	//FK
	@ManyToOne
	@JoinColumn(name="user_num", insertable=true, updatable=true)
	private UserInfo userinfo;
	
	private String dog_name;	
	private int dog_age;
	private int dog_gender;	
	private int dog_desexed;	
	private int dog_weight;
}
