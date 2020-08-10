package com.ssafy.blog.dto;

import java.time.LocalDateTime;

public class CommentDto {
	public int id;
	public int uid;
	public int post_id;
	public String content;
	public LocalDateTime create_date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getCreate_date() {
		return create_date;
	}
	public void setCreate_date(LocalDateTime create_date) {
		this.create_date = create_date;
	}
	
	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", uid=" + uid + ", post_id=" + post_id + ", content=" + content
				+ ", create_date=" + create_date + "]";
	}
	
}
