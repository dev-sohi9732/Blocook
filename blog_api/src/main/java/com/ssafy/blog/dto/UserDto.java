package com.ssafy.blog.dto;

import java.time.LocalDateTime;

public class UserDto {
    private String uid;
    private String password;
    private String nickname;
    private String email;
    private String valid;
    private String image;
    private String image_url;
    private LocalDateTime createDate;
    private int salty;
    private int sweet;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public int getSalty() {
		return salty;
	}
	public void setSalty(int salty) {
		this.salty = salty;
	}
	public int getSweet() {
		return sweet;
	}
	public void setSweet(int sweet) {
		this.sweet = sweet;
	}
	@Override
	public String toString() {
		return "UserDto [uid=" + uid + ", password=" + password + ", email=" + email + ", valid=" + valid + ", image="
				+ image + ", image_url=" + image_url + ", createDate=" + createDate + ", salty=" + salty + ", sweet="
				+ sweet + "]";
	}
	
	
}
