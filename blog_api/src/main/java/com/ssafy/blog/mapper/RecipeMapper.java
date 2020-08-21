package com.ssafy.blog.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.blog.dto.CookingDto;
import com.ssafy.blog.dto.IrdntDto;
import com.ssafy.blog.dto.RecipeDto;
import com.ssafy.blog.dto.UserDto;

@Mapper
public interface RecipeMapper {
	public List<RecipeDto> selectAllRecipes();
	
	public List<RecipeDto> selectByNationCode(String nationCode);
	
	public List<RecipeDto> selectByIrdnts(String[] irdntArr);
	
	public List<RecipeDto> selectByTitle(String title);
	
	public List<RecipeDto> selectByUid(String userUid);
	
	public RecipeDto selectRecipeById(String recipeId);
	
	public List<CookingDto> selectCookingById(String recipeId);
	
	public List<IrdntDto> selectIrdntById(String recipeId);
	
	public List<String> selectAllIrdnts();
	
	public int bookmarkRecipe(Map<String, String> map) throws Exception ;
	
	public int unBookmarkRecipe(Map<String, String> map) throws Exception;
	
	public int selectBookmarkCount(String recipeId);
	
	public int isBookmarkedRecipe(Map<String, String> map) throws Exception;
	
	public int addRecipe(RecipeDto dto);
	
	public int addCooking(CookingDto dto);
	
	public int addIrdnt(IrdntDto dto);
	
	public int selectMaxId();
	
	public int selectMaxIrdntSn();
	
	public List<RecipeDto> mybookmarkedrecipe(String userUid);
	
	public int deleteRecipe(String recipeId);
	
	public int deleteRecipeCooking(String recipeId);
	
	public int deleteRecipeIrdnt(String recipeId);
	
	public List<RecipeDto> selectTypes();
}
