package com.ssafy.blog.dto;

import java.time.LocalDateTime;

public class PostDto {

	private int id;
	private int uid;
	private int recipe_id;
	private String title;
	private String content;
	private int viewCnt;
	private LocalDateTime createDate;
	private String nickname;
	private int likeCnt;
	
	public int getId() {
		return id;
	}
	public void setPostId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLike_cnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	
	@Override
	public String toString() {
		return "PostDto [id=" + id + ", uid=" + uid + ", recipe_id=" + recipe_id + ", title=" + title + ", content="
				+ content + ", viewCnt=" + viewCnt + ", createDate=" + createDate + ", nickname=" + nickname
				+ ", likeCnt=" + likeCnt + "]";
	}
	

}
