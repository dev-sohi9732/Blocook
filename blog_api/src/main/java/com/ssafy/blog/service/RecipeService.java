package com.ssafy.blog.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.blog.dto.CookingDto;
import com.ssafy.blog.dto.IrdntDto;
import com.ssafy.blog.dto.RecipeDto;
import com.ssafy.blog.dto.UserDto;

public interface RecipeService {
	public List<RecipeDto> searchAll() throws Exception;
	
	public List<RecipeDto> searchByNationCode(String nationCode) throws Exception;
	
	public List<RecipeDto> searchByIrdnts(String irdntStr) throws Exception;
	
	public List<RecipeDto> searchByTitle(String title) throws Exception;
	
	public RecipeDto getRecipeDetail(String recipeId);
	
	public List<CookingDto> searchCookingsByRecipe(String recipeId);
	
	public List<IrdntDto> searchIrdntsByRecipe(String recipeId);
	
	public List<String> getAllIrdnts();
	
	public int bookmarkRecipe(Map<String, String> map) throws Exception;
	
	public int unBookmarkRecipe(Map<String, String> map) throws Exception;
	
	public int getBookmarkCount(String recipeId);
	
	public int writeRecipe(RecipeDto recipe) throws Exception;
	
	public int writeCooking(CookingDto cooking) throws Exception;
	
	public int writeIrdnt(IrdntDto irdnt) throws Exception;
	
	public int searchMaxId() throws Exception;
	
}
