package com.ssafy.blog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.ssafy.blog.dto.CookingDto;
import com.ssafy.blog.dto.IrdntDto;
import com.ssafy.blog.dto.RecipeDto;
import com.ssafy.blog.service.RecipeService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/recipes")
public class RecipeController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private RecipeService recipeService;
	
	@ApiOperation(value = "모든 레시피들을 반환한다.", response = List.class)
	@GetMapping(value = "/search/all")
	public ResponseEntity<List<RecipeDto>> searchAll() throws Exception {
		List<RecipeDto> recipes = recipeService.searchAll();

		return new ResponseEntity<List<RecipeDto>>(recipes, HttpStatus.OK);
	}
	
	@ApiOperation(value = "콤마로 구분된 재료 리스트 문자열을 받아 해당 국가 카테고리의 모든 레시피들을 반환한다.", response = List.class)
	@GetMapping(value = "/search/nation/{nationCode}")
	public ResponseEntity<List<RecipeDto>> searchByNationCode(@PathVariable String nationCode) throws Exception {
		List<RecipeDto> recipes = recipeService.searchByNationCode(nationCode);

		return new ResponseEntity<List<RecipeDto>>(recipes, HttpStatus.OK);
	}
	
	@ApiOperation(value = "콤마로 구분된 재료 리스트 문자열을 받아 해당 재료가 포함된 모든 레시피들을 반환한다.", response = List.class)
	@GetMapping(value = "/search/irdnts/{irdntStr}")
	public ResponseEntity<List<RecipeDto>> searchByIrdnts(@PathVariable String irdntStr) throws Exception {
		// 일치도 계산 필요..?
		// 더 많은 재료를 포함할 수록 일치도가 높아지고 우선순위가 높아지는 것을 추후에 반영
		// 일단은 모든 재료를 포함하는 레시피만 반환한다.
		List<RecipeDto> result = recipeService.searchByIrdnts(irdntStr);
		
		if (result == null) {
			return new ResponseEntity<List<RecipeDto>>(result, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<RecipeDto>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "요리명 문자열을 받아 해당 요리명과 일치하는 모든 레시피들을 반환한다.", response = List.class)
	@GetMapping(value = "/search/title/{title}")
	public ResponseEntity<List<RecipeDto>> searchByTitle(@PathVariable String title) throws Exception {
		
		List<RecipeDto> result = recipeService.searchByTitle(title);
		
		if (result == null) {
			return new ResponseEntity<List<RecipeDto>>(result, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<RecipeDto>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자 아이디(UID)를 받아 해당 사용자가 등록한 모든 레시피들을 반환한다.", response = List.class)
	@PostMapping(value = "/search/userUid")
	public ResponseEntity<List<RecipeDto>> searchByUid(@RequestBody String userUid) throws Exception {
		
		List<RecipeDto> result = recipeService.searchByUid(userUid);
		
		if (result == null) {
			return new ResponseEntity<List<RecipeDto>>(result, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<RecipeDto>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "레시피 ID에 해당하는 레시피 정보를 반환한다.", response = RecipeDto.class)
	@GetMapping(value = "/{recipeId}")
	public ResponseEntity<RecipeDto> getRecipeDetail(@PathVariable String recipeId) throws Exception {
		
		RecipeDto result = recipeService.getRecipeDetail(recipeId);
		
		if (result == null) {
			return new ResponseEntity<RecipeDto>(result, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<RecipeDto>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "레시피 ID에 해당하는 모든 과정 정보를 반환한다.", response = List.class)
	@GetMapping(value = "/{recipeId}/cookings")
	public ResponseEntity<List<CookingDto>> searchCookingsByRecipe(@PathVariable String recipeId) throws Exception {
		
		List<CookingDto> result = recipeService.searchCookingsByRecipe(recipeId);
		
		if (result == null) {
			return new ResponseEntity<List<CookingDto>>(result, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<CookingDto>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "레시피 ID에 해당하는 모든 재료 정보를 반환한다.", response = List.class)
	@GetMapping(value = "/{recipeId}/irdnts")
	public ResponseEntity<List<IrdntDto>> searchIrdntsByRecipe(@PathVariable String recipeId) throws Exception {
		
		List<IrdntDto> result = recipeService.searchIrdntsByRecipe(recipeId);
		
		if (result == null) {
			return new ResponseEntity<List<IrdntDto>>(result, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<IrdntDto>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "모든 재료를 String 리스트 형태로 반환한다.", response = List.class)
	@GetMapping(value = "/irdnts")
	public ResponseEntity<List<String>> getAllIrdnts() throws Exception {
		
		List<String> result = recipeService.getAllIrdnts();
		
		if (result == null) {
			return new ResponseEntity<List<String>>(result, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<String>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "레시피 즐겨찾기 등록", response = String.class)
	@PostMapping(value = "/bookmark")
	public ResponseEntity<String> bookmarkRecipe(@RequestBody Map<String,String> params) throws Exception {
		
		int result = 0;
		try {
			result = recipeService.bookmarkRecipe(params); // uid와 recipeId가 들어 있는 map
		} catch (Exception e) {
			System.out.println(e.getMessage()); // 중복 키 등 제약조건 예외 처리
		}
		
		if (result == 0) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "레시피 즐겨찾기 해제", response = String.class)
	@DeleteMapping(value = "/unbookmark/{uid}/{recipeId}")
	public ResponseEntity<String> unBookmarkRecipe(@PathVariable Map<String, String> params) throws Exception {

		int result = 0;
		try {
			result = recipeService.unBookmarkRecipe(params); // uid와 recipeId가 들어 있는 map
		} catch (Exception e) {
			System.out.println(e.getMessage()); // 예외 처리
		}
		
		if (result == 0) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "레시피 즐겨찾기 카운트", response = Integer.class)
	@GetMapping(value = "/{recipeId}/bookmark-count")
	public ResponseEntity<Integer> getBookmarkCount(@PathVariable String recipeId) {
		
		int result = recipeService.getBookmarkCount(recipeId);
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "좋아요 누른 레시피인지 판단", response = String.class)
	@PostMapping(value = "/isbookmarked")
	public ResponseEntity<String> isBookmarkedRecipe(@RequestBody Map<String,String> params) throws Exception {
		
		int result = 0;
		try {
			result = recipeService.isBookmarkedRecipe(params); // uid와 recipeId가 들어 있는 map
		} catch (Exception e) {
			System.out.println(e.getMessage()); // 중복 키 등 제약조건 예외 처리
		}
		
		if (result == 0) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "레시피 기본정보, 재료 등록하기", response = Integer.class)
	@PostMapping(value = "/add/recipe")
	public ResponseEntity<String> addRecipeDetail(@RequestBody String params) throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		//새로 등록할 레시피 id
		int recipeId = recipeService.searchMaxId() + 1;
//		System.out.println("===========recipeId : "+recipeId);
		
		//레시피 기본 정보 등록
		JsonNode recipeNode = objectMapper.readTree(params).findPath("recipe");
		System.out.println(recipeNode.toString());
		RecipeDto recipe = objectMapper.readValue(recipeNode.toString(), RecipeDto.class);
		System.out.println(recipe);
		recipe.setRecipeId(recipeId);
		recipeService.writeRecipe(recipe);
		String result = Integer.toString(recipeId);
		
		//레시피 재료 등록
		JsonNode irdntsNode = objectMapper.readTree(params).findPath("irdnts");
		CollectionType collectionTypeI = objectMapper.getTypeFactory().constructCollectionType(List.class, IrdntDto.class);
		List<IrdntDto> irdntList = objectMapper.readValue(irdntsNode.toString(), collectionTypeI);
		System.out.println(irdntList);
		IrdntDto irdnt;
		int irdntSn = 0; //새로 등록할 재료 id(irdnt_sn)
		for(int i = 1; i <= irdntList.size(); i++) {
			//레시피 id 셋팅
			irdnt = irdntList.get(i-1);
			irdnt.setRecipeId(recipeId);
			irdntSn = recipeService.searchMaxIrdntSn() + 1;
			irdnt.setIrdntSn(irdntSn);
			recipeService.writeIrdnt(irdntList.get(i-1));
		}
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "레시피 과정 등록하기", response = String.class)
	@PostMapping(value = "/add/cookings")
	public ResponseEntity<String> addRecipeCookings(@RequestBody String params) throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		String result = "true";
		
		//새로 등록할 레시피 id
		JsonNode recipeIdNode = objectMapper.readTree(params).findPath("recipeId");
		int recipeId = objectMapper.readValue(recipeIdNode.toString(), Integer.class);
		System.out.println("===========recipeId : "+recipeId);
		
		//레시피 과정 등록
		JsonNode cookingsNode = objectMapper.readTree(params).findPath("cookings");
		CollectionType collectionTypeC = objectMapper.getTypeFactory().constructCollectionType(List.class, CookingDto.class);
		List<CookingDto> cookingList = objectMapper.readValue(cookingsNode.toString(), collectionTypeC);
		System.out.println(cookingList);
		CookingDto cooking;
		for(int i = 1; i <= cookingList.size(); i++) {
			//필요하면 과정 번호 넣는거 추가
			//레시피 id 셋팅
			cooking = cookingList.get(i-1);
			cooking.setRecipeId(recipeId);
			recipeService.writeCooking(cookingList.get(i-1));
		}
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "레시피 목록 얻기", response = String.class)
	@PostMapping(value = "/get/irdnts")
	public ResponseEntity<List> getRecipes() throws Exception {
		List<String> irdnts = recipeService.getAllIrdnts();
//		System.out.println("=================== : "+ irdnts);
		return new ResponseEntity<List>(irdnts, HttpStatus.OK);
	}
	
}
