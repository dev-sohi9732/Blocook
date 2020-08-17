<template>
	<div class="likerecipe">
		<div class="container" style="padding: 30px 25px 30px 25px;">
			<div class="row">
				<div class="col-6" v-for="recipe in myBookmarkedRecipes" :key="recipe.recipeId">
					<div class="card" id="recipecard">
						<img class="card-img-top" :src="recipe.imgUrl" alt="Card image cap" style="padding: 0.2rem;width: 100%;height: 150px;">
						<div class="card-body" style="padding: 0.5rem;">
							<h5 class="card-title" style="margin-bottom: 0.5rem;">{{ recipe.recipeNmKo }}</h5>
							<span style="font-size:0.8rem; margin-right:10px;"><i class="fa fa-apple" style="color:orange;font-weight:bold;"></i>&nbsp; {{recipe.calorie}}kcal</span>
							<span style="font-size:0.8rem;"><i class="fa fa-heart" style="color:red;font-weight:bold;"></i>&nbsp; {{ recipe.likeCnt }}</span>
							<p class="card-text">{{ recipe.sumry }}</p>
							<router-link :to="'recipe?Id=' +recipe.recipeId" class="btn btn-primary" style="width:100%;">레시피 보기&nbsp;<i class="fa fa-book"></i></router-link>
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
			}
    })
    .catch((error) => {
			alert('결과 요청에 실패하였습니다.')
			console.log(error)
    })
	}
}
</script>

<style>
#recipecard {
  margin-bottom: 15px;
	text-align: center;
}
@media(min-width: 768px) {
    .likerecipe {
				width:600px;
				
    }
}
.likerecipe {
	margin: 0 auto;
}
</style>