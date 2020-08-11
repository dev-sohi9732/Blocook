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
						<!-- 
						<div class="foodimg" v-if="myRecipe.imgUrl.length < 5">
							<img src="@/assets/img/addimage.jpg" class="card-img-top" alt="...">
						</div>
						<div class="foodimg" v-else>
							<img :src="myRecipe.imgUrl" class="card-img-top" alt="...">
						</div>
						-->
						<div class="card-body" style="padding:5px;">
							<h5 class="card-title txt_line" style="margin-bottom:3px;">{{ myRecipe.recipeNmKo }}</h5>
							<p class="card-text">{{ myRecipe.sumry }}</p>
							<div>
								<router-link :to="'recipe?Id='+myRecipe.recipeId" class="btn" style="background-color:#B3D662;font-weight:bold;">
									레시피&nbsp;<img style="width:25px; height: 25px; margin-top:0px;" src="@/assets/img/recipeicon-yellow.png">
								</router-link>
								<router-link :to="'editrecipe?Id='+myRecipe.recipeId" class="btn" id="editbtn" style="background-color:rgb(241, 196, 15);font-weight:bold;">수정</router-link>
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
	height: 310px;
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
	width: 120px;
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
#editbtn {
	margin: 3px 1.5px 0px 0px;
	background-color: rgb(241, 196, 15);
	width:48%;
	font-weight:bold;
	height: 36px;
}
#delbtn {
	margin: 3px 0px 0px 1.5px;
	background-color: rgb(255, 93, 72);
	width:48%;
	font-weight:bold;
	height: 36px;
}
</style>