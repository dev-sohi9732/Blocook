<template>
	<div class="searchitem">
		<div class="container" style="padding-left: 5vw;">
			<div class="card" id="recipecard" style="font-family: 'Poor Story', cursive;">
				<img class="card-img-top" :src="recipe.imgUrl" alt="Card image cap" style="padding: 0.2rem;width: 100%;height: 43%;">
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
</template>

<script>
import http from "@/util/http-common.js";
export default {
    name: 'SearchRecipeItem',
    data() {
        return {
            likeCnt:0
        }
    },
    props: {
        recipe: {
            type: Object,
        },
    },
    created() {
      http.get(`/recipes/${this.recipe.recipeId}/bookmark-count`)
			.then((res) => {
        this.likeCnt = res.data
			})
			.catch((error) => {
				console.dir(error);
			});
    },
    updated() {
      http.get(`/recipes/${this.recipe.recipeId}/bookmark-count`)
			.then((res) => {
        this.likeCnt = res.data
			})
			.catch((error) => {
				console.dir(error);
			});
    },
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Poor+Story&display=swap');
#recipecard {
  margin-bottom: 15px;
	text-align: center;
	max-width: 300px;
	padding: 0 auto;
}
@media(min-width: 992px) {
    .searchitem {
				width:600px;
				padding-left: 7vw;
    }
}
.searchitem {
	margin: 0 auto;
}
.card {
	height: 315px;
}
.card-body {
	height: 200px;
}
</style>