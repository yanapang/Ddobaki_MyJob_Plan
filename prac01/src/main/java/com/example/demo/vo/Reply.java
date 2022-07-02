package com.example.demo.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Entity
@Data
@Table(name="reply")
public class Reply {
	@Id
	private int	reply_num;
	
	
	@ManyToOne
	@JoinColumn(name="post_num", insertable = true, updatable = true, nullable=false)
	private Board board;
	
	@OneToOne
	@JoinColumn(name="user_num", insertable = true, updatable = true, nullable=false)
	private UserInfo userinfo;	
	
	@Column(nullable=false)
	private int	reply_group;
	
	@Column(nullable=false)
	private int	reply_level;
	
	@Column(nullable=false)
	private int	reply_step;
	
	@Column(length=4000)
	private String reply_content;	
}
