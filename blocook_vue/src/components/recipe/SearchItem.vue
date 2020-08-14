<template>
    <div class="card" style=" width: 160px;">
        <center class="foodimg">
            <img :src="recipe.imgUrl" class="card-img-top" alt="..." style="width:140px;">
        </center>
        <div class="card-body" style="padding:5px;">
            <h5 class="card-title" style="margin-bottom:0px;">{{ recipe.recipeNmKo }}</h5>
            <i class="fa fa-apple" style="font-size:0.8rem; margin-right: 10px;">&nbsp; {{recipe.calorie}}kcal </i>
            <span style="font-size:0.8rem;"><i class="fa fa-heart" style="color:red;font-weight:bold;"></i>&nbsp; {{ likeCnt }}</span>
            <p class="card-text">{{ recipe.sumry }}</p>
            <center class="recipebook">
                <router-link :to="'recipe?Id=' +recipe.recipeId" class="btn" style="width:150px; background-color:#B3D662;">레시피&nbsp;<img src="@/assets/img/recipeicon.png"></router-link>
            </center>
        </div>
    </div>
</template>

<script>
import http from "@/util/http-common.js";
export default {
    name: 'SearchItem',
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
        console.log(res.data)
			})
			.catch((error) => {
				console.dir(error);
			});
    },
    updated() {
      http.get(`/recipes/${this.recipe.recipeId}/bookmark-count`)
			.then((res) => {
        this.likeCnt = res.data
        console.log(res.data)
			})
			.catch((error) => {
				console.dir(error);
			});
    },
}
</script>

<style>
.foodimg img {
	width: 120px;
	height: 120px;
}
.card {
	height: 296px;
	margin-bottom: 9px;
}
.card-body {
	/* height: 200px; */
	/* display: inline-block;   */
	/* // inline-block으로 설정 필요 */
}
.card-title {
  height: 22px;
  background-image: url("../../assets/img/highlight.png");
	background-size: 85%;
	background-repeat: no-repeat;
}
.card-text {
	height: 80px;
	width: 140px;
	
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
    -webkit-line-clamp: 3; /* 라인수 */
    -webkit-box-orient: vertical;
    word-wrap:break-word; 
}
.recipebook img {
    width:25px;
    height: 25px;
    margin-top: 0px;
}
</style>