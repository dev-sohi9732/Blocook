<template>
	<center>
		<div style="margin-top: 20px;">
			<router-link to="addrecipe">
				<button class="button btn" style="width: 82%; max-width:470px; color: purple;">
					<i class="fa fa-spoon">&nbsp;새 레시피 작성</i>
				</button>
			</router-link>
		</div>

		<div class="myrecipe">
			<div class="container" style="padding: 10px 25px 30px 25px;">
				<div class="row">
					<div class="col-6" v-for="myRecipe in myRecipes" :key="myRecipe.recipeId">
						<div class="card" id="recipecard" style="font-family: 'Poor Story', cursive;">
							<img class="card-img-top" :src="myRecipe.imgUrl" alt="Card image cap" style="padding: 0.2rem;width: 100%;height: 150px;">
							<div class="card-body" style="padding: 0.5rem;">
								<h5 class="card-title" style="margin-bottom: 0.5rem;">{{ myRecipe.recipeNmKo }}</h5>
								<span style="font-size:0.8rem; margin-right:10px;"><i class="fa fa-apple" style="color:orange;font-weight:bold;"></i>&nbsp; {{myRecipe.calorie}}kcal</span>
								<span style="font-size:0.8rem;"><i class="fa fa-heart" style="color:red;font-weight:bold;"></i>&nbsp; {{ myRecipe.likeCnt }}</span>
								<p class="card-text">{{ myRecipe.sumry }}</p>
								<router-link :to="'recipe?Id=' +myRecipe.recipeId" class="btn btn-primary" style="padding-left: 10px;padding-right: 10px;margin-left: .1rem;">레시피 보기&nbsp;<i class="fa fa-book"></i></router-link>
								<router-link :to="'editrecipe?Id='+myRecipe.recipeId" class="btn" id="editbtn" style="background-color:rgb(241, 196, 15);font-weight:bold;color:white;">수정</router-link>
								<button class="button btn" id="delbtn" @click="deleteRecipe(myRecipe.recipeId)">삭제</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</center>
</template>

<script>
import http from "@/util/http-common.js";

export default {
	data() {
		return {
			myRecipes: [],
		}
	},
	created() {
		http
		.post("recipes/search/userUid", this.$store.state.user.uid)
    .then(({ data }) => {
			this.myRecipes = data;
    })
    .catch((error) => {
			alert('결과 요청에 실패하였습니다.')
			console.log(error)
    })
	},
	methods: {
		editRecipe() {
			this.$router.push("/editrecipe");
		},
		deleteRecipe(delRecipeId) {
			console.log(delRecipeId);
			http
      .delete(`/recipes/delete/recipe/${delRecipeId}`) // 레시피 삭제
      .then(({ data }) => {
				alert("레시피가 삭제되었습니다.");
				// 로그인 새로고침 해제 후 적용 this.$router.reload();
      })
      .catch((error) => {
          console.dir(error);
      });
		}
	}
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Poor+Story&display=swap');
button {
	margin-bottom: 20px;
}
.btn {
	width: 98%;
}
.col-6 {
	margin-bottom: 10px;
}
img {
	width: 100px;
	height: 100px;
}
.card {
	height: 368px;
}
.card-body {
	height: 200px;
	/* display: inline-block;   */
	/* // inline-block으로 설정 필요 */
}
.card-title {
	height: 30px;
	text-align: center;
	background-repeat: no-repeat;
}
.card-text {
	font-size: 14px;
  line-height: 16px;
	text-align: left;
	height: 48px;
	width: 120px;
	margin-bottom: 10px;
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
    -webkit-line-clamp: 3; /* 라인수 */
    -webkit-box-orient: vertical;
    word-wrap:break-word; 
}
.foodimg img {
	margin: 7px;
	width: 120px;
	height: 110px;
}
.txt_line {
	overflow:hidden;
	text-overflow:ellipsis;
	white-space:nowrap;
}
#editbtn {
	margin: 5px 2.74px 0px 0px;
	background-color: rgb(241, 196, 15);
	width:47%;
	font-weight:bold;
	height: 34px;
	padding: 5px;
}
#delbtn {
	margin: 5px 0px 0px 2.74px;
	background-color: rgb(255, 93, 72);
	color: white;
	width:47%;
	font-weight:bold;
	height: 34px;
	padding: 5px;
}
#recipecard {
  margin-bottom: 15px;
	text-align: center;
}
@media(min-width: 768px) {
    .myrecipe {
				width:600px;
				
    }
}
.myrecipe {
	margin: 0 auto;
}
</style>