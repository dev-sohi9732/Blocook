package com.ssafy.blog.dto;

import java.time.LocalDateTime;

public class CommentDto {
	public int id;
	public int uid;
	public int postId;
	public String content;
	public LocalDateTime createDate;
	public String nickname;
	
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
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", uid=" + uid + ", postId=" + postId + ", content=" + content + ", createDate="
				+ createDate + ", nickname=" + nickname + "]";
	}
	
}
