<template>
	<!-- 좋아요 표시한 레시피 -->
	<div class="container" style="width: 90%;margin-top:10px;margin-bottom:30px;">
		<div class="row">
			<div class="col-6" v-for="recipe in myBookmarkedRecipes" :key="recipe.recipeId">
				<div class="card">
					<div class="foodimg">
						<img :src="recipe.imgUrl" class="card-img-top" alt="...">
					</div>
					<div class="card-body" style="padding:5px;">
						<h5 class="card-title txt_line" style="margin-bottom:3px;">{{ recipe.recipeNmKo }}</h5>
						<p class="card-text">{{ recipe.sumry }}</p>
						<div>
							<router-link :to="'recipe?Id='+recipe.recipeId" class="btn" style="background-color:#B3D662;font-weight:bold;">
								레시피&nbsp;<img style="width:25px; height: 25px; margin-top:0px;" src="@/assets/img/recipeicon-yellow.png">
							</router-link>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import http from "@/util/http-common.js";

export default {
	data() {
		return {
			myBookmarkedRecipes: [],
		}
	},
	created() {
		http
		.post("recipes/myBookmark/userUid", this.$store.state.user.uid)
    .then(({ data }) => {
			this.myBookmarkedRecipes = data;
			for(var i = 0; i < this.myBookmarkedRecipes.length; i++) {
				console.log(this.myBookmarkedRecipes[i].imgUrl)
				/*if(this.myBookmarkedRecipes[i].imgUrl == null) {
					this.myBookmarkedRecipes[i].imgUrl = "@/assets/img/addimage.jpg";
					console.log(this.myBookmarkedRecipes[i].imgUrl)
				}*/
			}
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
img:before {
	background-image: url("../../assets/img/addimage.jpg");
}
.card {
	height: 270px;
}
.card-body {
	height: 200px;
	/* display: inline-block;   */
	/* // inline-block으로 설정 필요 */
}
.card-title {
	height: 30px;
	text-align: center;
  background-image: url("../../assets/img/highlight.png");
	background-size: 85%;
	background-repeat: no-repeat;
}
.card-text {
	font-size: 14px;
  line-height: 16px;
	text-align: left;
	height: 50px;
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
	width: 110px;
	height: 110px;
}
.recipebook {
	width:40px;
	height: 43px;
}
.txt_line {
	overflow:hidden;
	text-overflow:ellipsis;
	white-space:nowrap;
}
</style>