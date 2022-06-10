package com.example.demo.vo;

import lombok.Data;

@Data
public class ReplyVO {
	private int reply_num;
	private int post_num;
	private int ref_reply_num;
	private int user_num;
	private int ref_user_num;
	private int reply_group;
	private int reply_level;
	private int reply_step;
	private String reply_content;
}
