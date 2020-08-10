package com.ssafy.blog.dto;

import java.time.LocalDateTime;

public class PostDto {

	private int postId;
	private int uid;
	private int recipe_id;
	private String title;
	private String content;
	private int view_cnt;
	private LocalDateTime createDate;
	public int getPostId() {
		return postId;
	}
	public void setPostId(int id) {
		this.postId = id;
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
	public int getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "PostDto [postId=" + postId + ", uid=" + uid + ", recipe_id=" + recipe_id + ", title=" + title + ", content="
				+ content + ", view_cnt=" + view_cnt + ", createDate=" + createDate + "]";
	}

}
