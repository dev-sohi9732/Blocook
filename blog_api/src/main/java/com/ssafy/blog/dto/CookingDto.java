package com.ssafy.blog.dto;

public class CookingDto {
	private int id;						// ID
	private int recipeId;				// 레시피 ID
	private int cookingNo;				// 레시피 순서 번호
	private String cookingDc;			// 단계 설명
	private String streStepImageUrl;	// 단계 이미지 URL
	private String stepTip;				// 단계별 팁
	private String timerYN;				// 타이머 유무
	private int timerM;					// 타이머 분
	private int timerS;					// 타이머 초
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
	public int getCookingNo() {
		return cookingNo;
	}
	public void setCookingNo(int cookingNo) {
		this.cookingNo = cookingNo;
	}
	public String getCookingDc() {
		return cookingDc;
	}
	public void setCookingDc(String cookingDc) {
		this.cookingDc = cookingDc;
	}
	public String getStreStepImageUrl() {
		return streStepImageUrl;
	}
	public void setStreStepImageUrl(String streStepImageUrl) {
		this.streStepImageUrl = streStepImageUrl;
	}
	public String getStepTip() {
		return stepTip;
	}
	public void setStepTip(String stepTip) {
		this.stepTip = stepTip;
	}
	public String getTimerYN() {
		return timerYN;
	}
	public void setTimerYN(String timerYN) {
		this.timerYN = timerYN;
	}
	public int getTimerM() {
		return timerM;
	}
	public void setTimerM(int timerM) {
		this.timerM = timerM;
	}
	public int getTimerS() {
		return timerS;
	}
	public void setTimerS(int timerS) {
		this.timerS = timerS;
	}
	
	@Override
	public String toString() {
		return "CookingDto [id=" + id + ", recipeId=" + recipeId + ", cookingNo=" + cookingNo + ", cookingDc="
				+ cookingDc + ", streStepImageUrl=" + streStepImageUrl + ", stepTip=" + stepTip + ", timerYN=" + timerYN
				+ ", timerM=" + timerM + ", timerS=" + timerS + "]";
	}
	
}
