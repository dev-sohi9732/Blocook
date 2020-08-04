<template>
	<center>
		<div class="searchbar">
			<select v-model="selected">
  				<option disabled value="">선택</option>
				<option value="recipe_nm" >요리</option>
				<option value="irdnt">재료</option>
			</select>
			<form v-if="selected=='irdnt'" class="s-form" >
				<input v-model="query" list="my-list-id" size="sm" placeholder="검색어를 입력하세요" autofocus/>
				<datalist id="my-list-id">
					<option v-for="(irdnt, index) in irdnts" :key="index">{{ irdnt }}</option>
				</datalist>
				<button @click.prevent="appendmulti" style="font-weight:bold"><p>추가</p></button>
				<br>
				<input id="multiinput" style="margin-left:62px; height:30px; border: 2px solid gray;" v-model="multi">
				<button @click.prevent="deletemulti" style="font-weight:bold;margin-left:-30px; height:32px; width:28px; background-color:gray;">X</button>
                <button  @click.prevent="searchirdnt" style="width:48px; margin-left:2px;"><i class="fa fa-search" ></i></button>
				
			</form>
			<form  v-else class="s-form">
				<input type="text" v-model="query" placeholder="검색어를 입력하세요" autofocus>
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
									<div class="card" style=" width: 160px;">
										<center class="foodimg">
											<img :src="recipes[0].imgUrl" class="card-img-top" alt="..." style="width:140px;">
										</center>
										<div class="card-body" style="padding:5px;">
											<h5 class="card-title" style="margin-bottom:3px;">{{ recipes[0].recipeNmKo }}</h5>
											<p class="card-text">{{ recipes[0].sumry }}</p>
											<center>
												<router-link :to="'recipe?Id=' +recipes[0].recipeId" class="btn" style="width:150px; background-color:#B3D662;">레시피&nbsp;<img style="width:25px; height: 25px;" src="@/assets/img/recipeicon.png"></router-link>
											</center>
										</div>
									</div>
								</div>
								<div class="col-6" style="padding:0px;">
									<div class="card" style="width: 160px;">
										<center class="foodimg">
											<img :src="recipes[1].imgUrl" class="card-img-top" alt="..." style="width:140px;">
										</center>
										<div class="card-body" style="padding:5px;">
											<h5 class="card-title" style="margin-bottom:3px;">{{ recipes[1].recipeNmKo }}</h5>
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
									<div class="card" style=" width: 160px;">
										<center class="foodimg">
											<img :src="recipes[2].imgUrl" class="card-img-top" alt="..." style="width:140px;">
										</center>
										<div class="card-body" style="padding:5px;">
											<h5 class="card-title" style="margin-bottom:3px;">{{ recipes[2].recipeNmKo }}</h5>
											<p class="card-text">{{ recipes[2].sumry }}</p>
											<center>
												<router-link :to="'recipe?Id=' +recipes[2].recipeId" class="btn" style="width:150px; background-color:#B3D662;">레시피&nbsp;<img style="width:25px; height: 25px;" src="@/assets/img/recipeicon.png"></router-link>
											</center>
										</div>
									</div>
								</div>
								<div class="col-6" style="padding:0px;">
									<div class="card" style="width: 160px;">
										<center class="foodimg">
											<img :src="recipes[3].imgUrl" class="card-img-top" alt="..." style="width:140px;">
										</center>
										<div class="card-body" style="padding:5px;">
											<h5 class="card-title" style="margin-bottom:3px;">{{ recipes[3].recipeNmKo }}</h5>
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
									<div class="card" style=" width: 160px;">
										<center class="foodimg">
											<img :src="recipes[4].imgUrl" class="card-img-top" alt="..." style="width:140px;">
										</center>
										<div class="card-body" style="padding:5px;">
											<h5 class="card-title" style="margin-bottom:3px;">{{ recipes[4].recipeNmKo }}</h5>
											<p class="card-text">{{ recipes[4].sumry }}</p>
											<center>
												<router-link :to="'recipe?Id=' +recipes[4].recipeId" class="btn" style="width:150px; background-color:#B3D662;">레시피&nbsp;<img style="width:25px; height: 25px;" src="@/assets/img/recipeicon.png"></router-link>
											</center>
										</div>
									</div>
								</div>
								<div class="col-6" style="padding:0px;">
									<div class="card" style="width: 160px;">
										<center class="foodimg">
											<img :src="recipes[5].imgUrl" class="card-img-top" alt="..." style="width:140px;">
										</center>
										<div class="card-body" style="padding:5px;">
											<h5 class="card-title" style="margin-bottom:3px;">{{ recipes[5].recipeNmKo }}</h5>
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

export default {
  components: {},
  props: [],
  data() {
    return {
	  query: '',
	  selected:'',
	  recipes: [],
	  irdnts: [],
	  slide: 0,
	  sliding: null,
	  multi: '',
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
	deletemulti() {
		this.multi = ''
	},
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
	appendmulti: function () {
		if (this.query== "")
			alert("검색어를 입력해주세요.");
		else
			this.multi += this.query + ","
			this.query = ''
	}
  },
}
</script>

<style scoped>
h2 {
	vertical-align: middle;
	font-weight:bolder; 
	display:inline;
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