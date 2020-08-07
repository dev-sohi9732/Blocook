<template>
	<center>
		<div class="searchbar">
			<select v-model="selected">
  				<option disabled value="">선택</option>
				<option value="recipe_nm" >요리</option>
				<option value="irdnt">재료</option>
			</select>
			<form v-if="selected=='irdnt'" class="s-form" >
                <multiselect style="width:280px; margin-top:1px;" v-model="multi" tag-placeholder="해당 재료를 사용하는 레시피가 없습니다." placeholder="검색어를 입력해주세요." :options="irdnts" :multiple="true" :taggable="true"></multiselect>
				<button  @click.prevent="searchirdnt" style="width:60px;"><i class="fa fa-search" ></i></button>
			</form>
			<form  v-else class="s-form">
				<input class="title-input" type="text" v-model="query" placeholder="검색어를 입력하세요" autofocus>
				<button @click.prevent="searchtitle" ><i class="fa fa-search" ></i></button>
			</form>
		</div>
		<div style="margin-top:15px;">
			<h2>Today's Recipe&nbsp;</h2>
			<img class="recipebook" src="@/assets/img/recipeicon.png">
		</div>

		<div id="carouselExampleInterval" class="carousel slide" data-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active" data-interval="10000">
					<center>
						<div class="container">
							<div class="row">
								<div class="col-6" style="padding:0px;">
									<div class="card" id="mcard" style=" width: 160px;">
										<center class="foodimg">
											<img :src="recipes[0].imgUrl" class="card-img-top" alt="..." style="width:140px;">
										</center>
										<div class="card-body" style="padding:5px;">
											<h5 class="card-title" style="margin-bottom:3px;">{{ recipes[0].recipeNmKo }}</h5>
											<i class="fa fa-apple" style="font-size:0.8rem; margin-right: 10px;">&nbsp; {{recipes[0].calorie}}kcal </i>
											<span style="font-size:0.8rem;"><i class="fa fa-heart" style="color:red;font-weight:bold;"></i>&nbsp; {{ likeCnt[0] }}</span>
											<p class="card-text">{{ recipes[0].sumry }}</p>
											<center>
												<router-link :to="'recipe?Id=' +recipes[0].recipeId" class="btn" style="width:150px; background-color:#B3D662;">레시피&nbsp;<img style="width:25px; height: 25px;" src="@/assets/img/recipeicon.png"></router-link>
											</center>
										</div>
									</div>
								</div>
								<div class="col-6" style="padding:0px;">
									<div class="card" id="mcard" style="width: 160px;">
										<center class="foodimg">
											<img :src="recipes[1].imgUrl" class="card-img-top" alt="..." style="width:140px;">
										</center>
										<div class="card-body" style="padding:5px;">
											<h5 class="card-title" style="margin-bottom:3px;">{{ recipes[1].recipeNmKo }}</h5>
											<i class="fa fa-apple" style="font-size:0.8rem; margin-right: 10px;">&nbsp; {{recipes[1].calorie}}kcal </i>
											<span style="font-size:0.8rem;"><i class="fa fa-heart" style="color:red;font-weight:bold;"></i>&nbsp; {{ likeCnt[1] }}</span>
											<p class="card-text">{{ recipes[1].sumry }}</p>
											<center>
												<router-link :to="'recipe?Id=' +recipes[1].recipeId" class="btn" style="width:150px; background-color:#B3D662;">레시피&nbsp;<img style="width:25px; height: 25px;" src="@/assets/img/recipeicon.png"></router-link>
											</center>
										</div>
									</div>
								</div>
							</div>
						</div>
					</center>
				</div>
				<div class="carousel-item" data-interval="2000">
					<center>
						<div class="container">
							<div class="row">
								<div class="col-6" style="padding:0px;">
									<div class="card"  id="mcard" style=" width: 160px;">
										<center class="foodimg">
											<img :src="recipes[2].imgUrl" class="card-img-top" alt="..." style="width:140px;">
										</center>
										<div class="card-body" style="padding:5px;">
											<h5 class="card-title" style="margin-bottom:3px;">{{ recipes[2].recipeNmKo }}</h5>
											<i class="fa fa-apple" style="font-size:0.8rem; margin-right: 10px;">&nbsp; {{recipes[2].calorie}}kcal </i>
											<span style="font-size:0.8rem;"><i class="fa fa-heart" style="color:red;font-weight:bold;"></i>&nbsp; {{ likeCnt[2] }}</span>
											<p class="card-text">{{ recipes[2].sumry }}</p>
											<center>
												<router-link :to="'recipe?Id=' +recipes[2].recipeId" class="btn" style="width:150px; background-color:#B3D662;">레시피&nbsp;<img style="width:25px; height: 25px;" src="@/assets/img/recipeicon.png"></router-link>
											</center>
										</div>
									</div>
								</div>
								<div class="col-6" style="padding:0px;">
									<div class="card" id="mcard" style="width: 160px;">
										<center class="foodimg">
											<img :src="recipes[3].imgUrl" class="card-img-top" alt="..." style="width:140px;">
										</center>
										<div class="card-body" style="padding:5px;">
											<h5 class="card-title" style="margin-bottom:3px;">{{ recipes[3].recipeNmKo }}</h5>
											<i class="fa fa-apple" style="font-size:0.8rem; margin-right: 10px;">&nbsp; {{recipes[3].calorie}}kcal </i>
											<span style="font-size:0.8rem;"><i class="fa fa-heart" style="color:red;font-weight:bold;"></i>&nbsp; {{ likeCnt[3] }}</span>
											<p class="card-text">{{ recipes[3].sumry }}</p>
											<center>
												<router-link :to="'recipe?Id=' +recipes[3].recipeId" class="btn" style="width:150px; background-color:#B3D662;">레시피&nbsp;<img style="width:25px; height: 25px;" src="@/assets/img/recipeicon.png"></router-link>
											</center>
										</div>
									</div>
								</div>
							</div>
						</div>
					</center>
				</div>
        <div class="carousel-item">
					<center>
						<div class="container">
							<div class="row">
								<div class="col-6" style="padding:0px;">
									<div class="card" id="mcard" style=" width: 160px;">
										<center class="foodimg">
											<img :src="recipes[4].imgUrl" class="card-img-top" alt="..." style="width:140px;">
										</center>
										<div class="card-body" style="padding:5px;">
											<h5 class="card-title" style="margin-bottom:3px;">{{ recipes[4].recipeNmKo }}</h5>
											<i class="fa fa-apple" style="font-size:0.8rem; margin-right: 10px;">&nbsp; {{recipes[4].calorie}}kcal </i>
											<span style="font-size:0.8rem;"><i class="fa fa-heart" style="color:red;font-weight:bold;"></i>&nbsp; {{ likeCnt[4] }}</span>
											<p class="card-text">{{ recipes[4].sumry }}</p>
											<center>
												<router-link :to="'recipe?Id=' +recipes[4].recipeId" class="btn" style="width:150px; background-color:#B3D662;">레시피&nbsp;<img style="width:25px; height: 25px;" src="@/assets/img/recipeicon.png"></router-link>
											</center>
										</div>
									</div>
								</div>
								<div class="col-6" style="padding:0px;">
									<div class="card" id="mcard" style="width: 160px;">
										<center class="foodimg">
											<img :src="recipes[5].imgUrl" class="card-img-top" alt="..." style="width:140px;">
										</center>
										<div class="card-body" style="padding:5px;">
											<h5 class="card-title" style="margin-bottom:3px;">{{ recipes[5].recipeNmKo }}</h5>
											<i class="fa fa-apple" style="font-size:0.8rem; margin-right: 10px;">&nbsp; {{recipes[5].calorie}}kcal </i>
											<span style="font-size:0.8rem;"><i class="fa fa-heart" style="color:red;font-weight:bold;"></i>&nbsp; {{ likeCnt[5] }}</span>
											<p class="card-text">{{ recipes[5].sumry }}</p>
											<center>
												<router-link :to="'recipe?Id=' +recipes[5].recipeId" class="btn" style="width:150px; background-color:#B3D662;">레시피&nbsp;<img style="width:25px; height: 25px;" src="@/assets/img/recipeicon.png"></router-link>
											</center>
										</div>
									</div>
								</div>
							</div>
						</div>
					</center>
                </div>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleInterval" role="button" data-slide="prev">
				<span style="background-color:gray; border-radius:10px;" class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a>
			<a class="carousel-control-next" href="#carouselExampleInterval" role="button" data-slide="next">
				<span style="background-color:gray; border-radius:10px;" class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</center>
</template>

<script>
import http from "@/util/http-common.js";
import Multiselect from 'vue-multiselect'

export default {
  components: {
    Multiselect
  },
  props: [],
  data() {
    return {
	  query: '',
	  selected:'',
	  recipes: [],
	  irdnts: [],
	  slide: 0,
	  sliding: null,
	  multi: [],
	  likeCnt: [],
    };
  },
  created() {
    for (var i = 0; i < 6; i++) {
        this.recipes.push({
            "recipeId": -1,
            "recipeNmKo": "",
            "sumry": "",
            "nationCode": -1,
            "nationNm": "",
            "tyCode": -1,
            "tyNm": "",
            "cookingTime": -1,
            "calorie": -1,
            "qnt": -1,
            "levelNm": "",
            "irdntCode": "",
            "pcNm": -1,
            "imgUrl": "",
            "detUrl": ""
        })
	}
	//재료 데이터 가져오기
	http
		.post("/recipes/get/irdnts")
		.then(({ data }) => {
			this.irdnts = Array.from(data);
		})
		.catch((error) => {
			console.dir(error);
		});

    http.get('recipes/search/irdnts/소금')
    .then(({ data }) => {
	  this.recipes = data;
	  for (var i=0; i<this.recipes.length; i++ ) {
	    http.get(`/recipes/${this.recipes[i].recipeId}/bookmark-count`)
			.then((res) => {
				this.likeCnt.push(res.data)
			})
			.catch((error) => {
				console.dir(error);
			});
	}
    })
    .catch((error) => {
        alert('검색 결과 요청에 실패하였습니다.')
        console.log(error)
    })  
  },
  mounted() {
  },
  computed: {
  },
  watch: {
  },
  methods: {
    searchtitle() {
		if (this.query== "")
			alert("검색어를 입력해주세요.");
		else
        	this.$router.push("/search?title=" + this.query );
	},
	searchirdnt() {
        this.$router.push("/search?irdnt=" + this.multi );
	},
	onSlideStart(slide) {
		this.sliding = true
	},
	onSlideEnd(slide) {
		this.sliding = false
	},
	onSubmit() {
	  	console.log(eeeee)
	},
	// addTag (newTag) {
    //   const tag = {
    //     newTag
    //   }
    //   this.irdnts.push(tag)
    //   this.multi.push(tag)
    // },
  },
}
</script>

<style scoped>
h2 {
	vertical-align: middle;
	font-weight:bolder; 
	display:inline;
}
.multiselect {
	border: 4px solid rgb(241, 196, 15);
	margin:0px;
}
.searchbar button {
    width: 48px;
}
.carousel-item img {
	width:25px;
	height: 25px;
}
.container {
	width: 90%;
	margin-top:20px;
}
.card {
	height: 306px;
}
#mcard {
	height: 330px;
}
.card-body {
	height: 200px;
	/* display: inline-block;   */
	/* // inline-block으로 설정 필요 */
}
.card-title {
  height: 30px;
  background-image: url("../assets/img/highlight.png");
	background-size: 85%;
	background-repeat: no-repeat;
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
.foodimg img {
	margin-top: 10px;
	width: 120px;
	height: 120px;
}
.recipebook {
	width:40px;
	height: 43px;
}
#carouselExampleInterval {
	margin-bottom: 30px;
}
</style>