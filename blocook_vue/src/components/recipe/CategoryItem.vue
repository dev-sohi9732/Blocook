<template>
  <div class="col-6 mt-3">
    <div class="card" style="width:165px;">
      <center>
      <img :src="imgUrl" class="card-img-top" alt="recipe_img" style="height:100px;width:100%; width:150px;">
      <div class="card-body">
        <p class="card-title" style="font-weight:bold;">{{ recipe.recipeNmKo }}</p>
        <p class="card-text" >{{ recipe.sumry}}</p>
        <router-link :to="'recipe?Id=' +recipe.recipeId" class="btn" style="width:150px; background-color:#B3D662;">레시피&nbsp;<img style="margin-top:0px;width:25px; height: 25px;" src="@/assets/img/recipeicon.png"></router-link>
      </div>
      </center>
    </div>
  </div>

</template>

<script>
export default {
    name: 'CategoryItem',
    props: {
        recipe: {
            type: Object,
        },
    },
    computed : {
        imgUrl() {
            return this.recipe.imgUrl
        },
    }
}
</script>

<style>
img {
	margin-top: 10px;
	width: 100px;
	height: 100px;
}
.container {
    width: 100%;
}
.card {
}
.card-body {
	/* display: inline-block;   */
	/* // inline-block으로 설정 필요 */
}
.card-text {
	height: 80px;
  width: 140px;
  margin-bottom: 10px;
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
    -webkit-line-clamp: 3; /* 라인수 */
    -webkit-box-orient: vertical;
    word-wrap:break-word; 
}
.card-title {
  height: 20px;
  background-image: url("../../assets/img/highlight.png");
	background-size: 85%;
	background-repeat: no-repeat;
}
</style>