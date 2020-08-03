package com.ssafy.blog.dto;

public class IrdntDto {
	private int id;					// ID
	private int recipeId;			// 레시피 ID
	private int irdntSn;			// 재료 Serial Number
	private String irdntNm;			// 재료 이름
	private String irdntCpcty;		// 재료의 양
	private int irdntTyCode;		// 재료 유형 코드
	private String irdntTyNm;		// 재료 유형 이름 (주재료/양념 ..)
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
	public int getIrdntSn() {
		return irdntSn;
	}
	public void setIrdntSn(int irdntSn) {
		this.irdntSn = irdntSn;
	}
	public String getIrdntNm() {
		return irdntNm;
	}
	public void setIrdntNm(String irdntNm) {
		this.irdntNm = irdntNm;
	}
	public String getIrdntCpcty() {
		return irdntCpcty;
	}
	public void setIrdntCpcty(String irdntCpcty) {
		this.irdntCpcty = irdntCpcty;
	}
	public int getIrdntTyCode() {
		return irdntTyCode;
	}
	public void setIrdntTyCode(int irdntTyCode) {
		this.irdntTyCode = irdntTyCode;
	}
	public String getIrdntTyNm() {
		return irdntTyNm;
	}
	public void setIrdntTyNm(String irdntTyNm) {
		this.irdntTyNm = irdntTyNm;
	}
	@Override
	public String toString() {
		return "IrdntDto [id=" + id + ", recipeId=" + recipeId + ", irdntSn=" + irdntSn + ", irdntNm=" + irdntNm
				+ ", irdntCpcty=" + irdntCpcty + ", irdntTyCode=" + irdntTyCode + ", irdntTyNm=" + irdntTyNm + "]";
	}
	
}
