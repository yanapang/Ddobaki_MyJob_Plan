package com.example.demo.vo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "room")
public class Room {
	
	
	@Id
	private int room_num;
	
	@Column(nullable = false)
	private String room_name;
	
	@Column(nullable = false)
	private int room_price;

	@ManyToOne
	@JoinColumn(name="place_num", insertable=true, updatable=true)
	private Place place;
	
	@OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
	private List<Reservation> reservation;
	
	//참조받아야 하는 키
	//place_num
}
