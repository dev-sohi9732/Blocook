package com.ssafy.blog.dto;

public class RecipeDto {
	private int recipeId;		// 레시피 ID
	private String recipeNmKo;	// 레시피 이름
	private String sumry;		// 레시피 요약
	private int nationCode;		// 나라 코드	
	private String nationNm;	// 나라 이름
	private int tyCode;			// 유형 코드
	private String tyNm;		// 유형 이름
	private int cookingTime;	// 조리 시간(분)
	private int calorie;		// 칼로리
	private int qnt;			// n인분
	private String levelNm;		// 난이도
	private String irdntCode;	// 재료 코드
	private int pcNm;			// 가격
	private String imgUrl;		// 레시피 이미지 URL
	private String detUrl;		// 상세 페이지 URL
	public int getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
	public String getRecipeNmKo() {
		return recipeNmKo;
	}
	public void setRecipeNmKo(String recipeNmKo) {
		this.recipeNmKo = recipeNmKo;
	}
	public String getSumry() {
		return sumry;
	}
	public void setSumry(String sumry) {
		this.sumry = sumry;
	}
	public int getNationCode() {
		return nationCode;
	}
	public void setNationCode(int nationCode) {
		this.nationCode = nationCode;
	}
	public String getNationNm() {
		return nationNm;
	}
	public void setNationNm(String nationNm) {
		this.nationNm = nationNm;
	}
	public int getTyCode() {
		return tyCode;
	}
	public void setTyCode(int tyCode) {
		this.tyCode = tyCode;
	}
	public String getTyNm() {
		return tyNm;
	}
	public void setTyNm(String tyNm) {
		this.tyNm = tyNm;
	}
	public int getCookingTime() {
		return cookingTime;
	}
	public void setCookingTime(int cookingTime) {
		this.cookingTime = cookingTime;
	}
	public int getCalorie() {
		return calorie;
	}
	public void setCalorie(int calorie) {
		this.calorie = calorie;
	}
	public int getQnt() {
		return qnt;
	}
	public void setQnt(int qnt) {
		this.qnt = qnt;
	}
	public String getLevelNm() {
		return levelNm;
	}
	public void setLevelNm(String levelNm) {
		this.levelNm = levelNm;
	}
	public String getIrdntCode() {
		return irdntCode;
	}
	public void setIrdntCode(String irdntCode) {
		this.irdntCode = irdntCode;
	}
	public int getPcNm() {
		return pcNm;
	}
	public void setPcNm(int pcNm) {
		this.pcNm = pcNm;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getDetUrl() {
		return detUrl;
	}
	public void setDetUrl(String detUrl) {
		this.detUrl = detUrl;
	}
	@Override
	public String toString() {
		return "RecipeDto [recipeId=" + recipeId + ", recipeNmKo=" + recipeNmKo + ", sumry=" + sumry + ", nationCode="
				+ nationCode + ", nationNm=" + nationNm + ", tyCode=" + tyCode + ", tyNm=" + tyNm + ", cookingTime="
				+ cookingTime + ", calorie=" + calorie + ", qnt=" + qnt + ", levelNm=" + levelNm + ", irdntCode="
				+ irdntCode + ", pcNm=" + pcNm + ", imgUrl=" + imgUrl + ", detUrl=" + detUrl + "]";
	}
	
}
