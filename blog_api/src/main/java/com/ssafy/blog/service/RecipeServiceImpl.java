package com.ssafy.blog.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.blog.dto.CookingDto;
import com.ssafy.blog.dto.IrdntDto;
import com.ssafy.blog.dto.RecipeDto;
import com.ssafy.blog.mapper.RecipeMapper;

@Service
public class RecipeServiceImpl implements RecipeService{
	
	@Autowired
	RecipeMapper recipeMapper;

	@Override
	public List<RecipeDto> searchAll() throws Exception {
		return recipeMapper.selectAllRecipes();
	}
	
	@Override
	public List<RecipeDto> searchByNationCode(String nationCode) throws Exception {
		return recipeMapper.selectByNationCode(nationCode);
	}
	
	@Override
	public List<RecipeDto> searchByIrdnts(String irdntStr) throws Exception {
		// irdntStr 은  "재료1, 재료2, 재료3" 과 같은 형태
		String[] irdntsArr = URLDecoder.decode(irdntStr, "UTF-8").split(",");
		for (String i : irdntsArr) {
			System.out.print(i+" ");			
		}
		System.out.println();
		return recipeMapper.selectByIrdnts(irdntsArr);
	}

	@Override
	public List<RecipeDto> searchByTitle(String title) throws Exception {
		System.out.println(URLDecoder.decode(title, "UTF-8"));
		return recipeMapper.selectByTitle(URLDecoder.decode(title, "UTF-8"));
	}

	@Override
	public RecipeDto getRecipeDetail(String recipeId) {
		return recipeMapper.selectRecipeById(recipeId);
	}

	@Override
	public List<CookingDto> searchCookingsByRecipe(String recipeId) {
		return recipeMapper.selectCookingById(recipeId);
	}

	@Override
	public List<IrdntDto> searchIrdntsByRecipe(String recipeId) {
		return recipeMapper.selectIrdntById(recipeId);
	}

	@Override
	public List<String> getAllIrdnts() {
		return recipeMapper.selectAllIrdnts();
	}

	@Override
	public int bookmarkRecipe(Map<String, String> map) throws Exception {
		return recipeMapper.bookmarkRecipe(map);
	}

	@Override
	public int unBookmarkRecipe(Map<String, String> map) throws Exception {
		return recipeMapper.unBookmarkRecipe(map);
	}

	@Override
	public int getBookmarkCount(String recipeId) {
		return recipeMapper.selectBookmarkCount(recipeId);
	}

	@Override
	public int writeRecipe(RecipeDto recipe) throws Exception {
		return recipeMapper.addRecipe(recipe);
	}

	@Override
	public int writeCooking(CookingDto cooking) throws Exception {
		return recipeMapper.addCooking(cooking);
	}

	@Override
	public int writeIrdnt(IrdntDto irdnt) throws Exception {
		return recipeMapper.addIrdnt(irdnt);
	}

	@Override
	public int searchMaxId() throws Exception {
		return recipeMapper.selectMaxId();
	}
	
}
