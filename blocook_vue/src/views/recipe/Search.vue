<template>
  <center>
        <div class="searchbar" style="margin-top:100px;">
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
        <div>
            <select class="sorting">
              <!-- v-model="option" -->
              <!-- v-on:change="filter" -->
              <option value="see">조회순</option>
              <option value="calorie">칼로리순</option>
              <option value="like">좋아요순</option>
            </select>
        </div>
        <div>
            <div class="container" style="margin-top:40px;">
                <div class="row">
                    <div v-for="(recipe, index) in recipes" :key="index + '_items'" class="col-6" style="padding:0px;">
                        <div class="card" style=" width: 160px;">
                            <center class="foodimg">
                                <img :src="recipe.imgUrl" class="card-img-top" alt="..." style="width:140px;">
                            </center>
                            <div class="card-body" style="padding:5px;">
                                <h5 class="card-title" style="margin-bottom:3px;">{{ recipe.recipeNmKo }}</h5>
                                <p class="card-text">{{ recipe.sumry }}</p>
                                <center class="recipebook">
                                    <router-link :to="'recipe?Id=' +recipe.recipeId" class="btn" style="width:150px; background-color:#B3D662;">레시피&nbsp;<img src="@/assets/img/recipeicon.png"></router-link>
                                </center>
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
        selected:'',
        recipes: [],
        irdnts: [],
        query:'',
        multi:'',
      }
    },
  methods: {
    appendmulti: function () {
		if (this.query== "")
			alert("검색어를 입력해주세요.");
		else
			this.multi += this.query + ","
			this.query = ''
    },
    deletemulti() {
		this.multi = ''
	},
    searchtitle() {
        if (this.query== "")
			alert("검색어를 입력해주세요.");
		else
            this.$router.push("/search?title=" + this.query ).catch(()=>{});
            
            const params = new URL(document.location).searchParams;
            http.get(`/recipes/search/title/${params.get('title')}`)
                .then(response => {
                this.recipes = response.data
                })
                .catch(error => {
                console.log(error)
                })
	},
	searchirdnt() {
        this.$router.push("/search?irdnt=" + this.multi ).catch(()=>{});
        const params = new URL(document.location).searchParams;
        http.get(`/recipes/search/irdnts/${params.get('irdnt')}`)
            .then(response => {
            this.recipes = response.data
            })
            .catch(error => {
            console.log(error)
            })
    }
  },
  created(){
        const params = new URL(document.location).searchParams;
        var titleParam = params.get('title');
        var irdntsParam = params.get('irdnt');
        if(titleParam != null){
            http.get(`/recipes/search/title/${params.get('title')}`)
                .then(response => {
                this.recipes = response.data
                })
                .catch(error => {
                console.log(error)
                })
        } else if(irdntsParam != null){
            http.get(`/recipes/search/irdnts/${params.get('irdnt')}`)
                .then(response => {
                this.recipes = response.data
                })
                .catch(error => {
                console.log(error)
                })
        }
        
        //재료 데이터 가져오기
        http.post("/recipes/get/irdnts")
            .then(({ data }) => {
                this.irdnts = Array.from(data);
            })
            .catch((error) => {
                console.dir(error);
            });
  }
};
</script>
<style scoped>
.sorting {
	margin: 5px 24px 5px;
    float: right;
	height: 30px;
	width: 100px;
	border-radius: 5px;
	border: 4px solid rgb(40, 167, 69);
	background-color:white;
	font-weight:bolder;
}
.searchbar button {
    width: 48px;
}
.foodimg img {
	width: 120px;
	height: 120px;
}
.container {
    width: 90%;
    margin-top:20px;
}
.card {
	height: 296px;
	margin-bottom: 9px;
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
.recipebook img {
    width:25px;
    height: 25px;
    margin-top: 0px;
}
</style>

