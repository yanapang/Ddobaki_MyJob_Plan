package com.example.demo.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "placeimage")
public class PlaceImage {
	@Id
	private int place_image_num;
	
	@Column(nullable = false)
	private String place_image_file;
	
	@ManyToOne
	@JoinColumn(name="place_num", insertable=true, updatable=true)
	private Place place;
}

