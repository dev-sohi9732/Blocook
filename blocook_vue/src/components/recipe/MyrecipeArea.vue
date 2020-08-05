<template>
	<center>
		<div class="user">
			<router-link to="addrecipe">
				<button class="button btn" style="width: 82%">
					<i class="fa fa-spoon">&nbsp;새 레시피 작성</i>
				</button>
			</router-link>
		</div>

		<div class="container" style="width: 90%">
			<div class="row">
				<div class="col-6" v-for="myRecipe in myRecipes" :key="myRecipe.recipeId">
					<div class="card">
						<div class="foodimg">
							<img :src="myRecipe.imgUrl" class="card-img-top" alt="...">
						</div>
						<div class="card-body" style="padding:5px;">
							<h5 class="card-title" style="margin-bottom:3px;">{{ myRecipe.recipeNmKo }}</h5>
							<p class="card-text">{{ myRecipe.sumry }}</p>
							<div>
								<router-link :to="'recipe?Id='+myRecipe.recipeId" class="btn" style="background-color:#B3D662;">레시피&nbsp;<img style="width:25px; height: 25px; margin-top:0px;" src="@/assets/img/recipeicon.png"></router-link>
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
	}
}
</script>

<style scoped>
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
	margin-top: 10px;
	width: 100px;
	height: 100px;
}
.card {
	height: 306px;
}
.card-body {
	height: 200px;
	/* display: inline-block;   */
	/* // inline-block으로 설정 필요 */
}
.card-title {
  height: 30px;
  background-image: url("../../assets/img/highlight.png");
	background-size: 85%;
	background-repeat: no-repeat;
}
.card-text {
	text-align: left;
	height: 80px;
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
	margin-top: 10px;
	width: 120px;
	height: 120px;
}
.recipebook {
	width:40px;
	height: 43px;
}
</style>