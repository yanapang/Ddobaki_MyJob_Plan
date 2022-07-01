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
@Table(name="boardimage")
public class BoardImage {
	@Id
	private int board_image_num;
	
	@ManyToOne
	@JoinColumn(name="post_num", insertable=true, updatable=true, nullable = false)
	private Board board;
	
	@Column(length = 4000, nullable = false)
	private String board_image_file;
}
