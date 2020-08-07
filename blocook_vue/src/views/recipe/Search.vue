<template>
  <center>
        <div class="searchbar" style="margin-top:100px;">
            <select v-model="selected">
  				<option disabled value="">선택</option>
				<option value="recipe_nm" >요리</option>
				<option value="irdnt">재료</option>
			</select>
			<form v-if="selected=='irdnt'" class="s-form" >
                <multiselect style="width:280px; margin-top:1px;" v-model="multi" tag-placeholder="해당 재료를 사용하는 레시피가 없습니다." placeholder="검색어를 입력해주세요." :options="irdnts" :multiple="true" :taggable="true"></multiselect>
				<button  @click.prevent="searchirdnt" style="width:60px; "><i class="fa fa-search" ></i></button>
			</form>
			<form  v-else class="s-form">
				<input class="title-input" type="text" v-model="query" placeholder="검색어를 입력하세요" autofocus>
				<button @click.prevent="searchtitle" ><i class="fa fa-search" ></i></button>
			</form>
        </div>
        <div>
            <select class="sorting" v-model="sorting">
              <!-- v-model="option" -->
              <!-- v-on:change="filter" -->
              <option disabled value="">정렬방법</option>
              <option value="calorie">칼로리순</option>
              <option value="see">정확도순</option>
              <option value="like">좋아요순</option>
            </select>
        </div>
        <div>
            <div class="container" style="margin-top:40px;">
                <div class="row" v-if="sorting=='see'">
                    <div v-for="(recipe, index) in recipes" :key="index + '_items'" class="col-6" style="padding:0px;">
                        <div class="card" style=" width: 160px;">
                            <center class="foodimg">
                                <img :src="recipe.imgUrl" class="card-img-top" alt="..." style="width:140px;">
                            </center>
                            <div class="card-body" style="padding:5px;">
                                <h5 class="card-title" style="margin-bottom:0px;">{{ recipe.recipeNmKo }}</h5>
                                <i class="fa fa-apple" style="font-size:0.8rem; margin-right: 10px;">&nbsp; {{recipe.calorie}}kcal </i>
                                <p class="card-text">{{ recipe.sumry }}</p>
                                <center class="recipebook">
                                    <router-link :to="'recipe?Id=' +recipe.recipeId" class="btn" style="width:150px; background-color:#B3D662;">레시피&nbsp;<img src="@/assets/img/recipeicon.png"></router-link>
                                </center>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" v-else-if="sorting=='calorie'">
                    <div v-for="(recipe, index) in sortcalories" :key="index + '_items'" class="col-6" style="padding:0px;">
                        <div class="card" style=" width: 160px;">
                            <center class="foodimg">
                                <img :src="recipe.imgUrl" class="card-img-top" alt="..." style="width:140px;">
                            </center>
                            <div class="card-body" style="padding:5px;">
                                <h5 class="card-title" style="margin-bottom:0px;">{{ recipe.recipeNmKo }}</h5>
                                <i class="fa fa-apple" style="font-size:0.8rem; margin-right: 10px;">&nbsp; {{recipe.calorie}}kcal </i>
                                <p class="card-text">{{ recipe.sumry }}</p>
                                <center class="recipebook">
                                    <router-link :to="'recipe?Id=' +recipe.recipeId" class="btn" style="width:150px; background-color:#B3D662;">레시피&nbsp;<img src="@/assets/img/recipeicon.png"></router-link>
                                </center>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" v-else>
                    <div v-for="(recipe, index) in recipes" :key="index + '_items'" class="col-6" style="padding:0px;">
                        <div class="card" style=" width: 160px;">
                            <center class="foodimg">
                                <img :src="recipe.imgUrl" class="card-img-top" alt="..." style="width:140px;">
                            </center>
                            <div class="card-body" style="padding:5px;">
                                <h5 class="card-title" style="margin-bottom:0px;">{{ recipe.recipeNmKo }}</h5>
                                <i class="fa fa-apple" style="font-size:0.8rem; margin-right: 10px;">&nbsp; {{recipe.calorie}}kcal </i>
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
import Multiselect from 'vue-multiselect'
import VueLodash from 'vue-lodash'
import lodash from 'lodash'
export default {
  components: {
    Multiselect
  },
  data() {
      return {
        selected:'',
        recipes: [],
        irdnts: [],
        query:'',
        multi:[],
        sorting:'',
      }
    },
  computed: {
    sortcalories: function () {
        return _.sortBy(this.recipes, 'calorie')
    }
  },
  methods: {
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
        var query = encodeURIComponent(params.get('irdnt'));
        http.get(`/recipes/search/irdnts/${query}`)
            .then(response => {
            this.recipes = response.data
            })
            .catch(error => {
            console.log(error)
            })
    
    },
    // addTag (newTag) {
    //   const tag = {
    //     name: newTag,
    //   }
    //   this.irdnts.push(tag)
    //   this.multi.push(tag)
    // },
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
            var query = encodeURIComponent(params.get('irdnt'));
            http.get(`/recipes/search/irdnts/${query}`)
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
.multiselect {
	border: 4px solid rgb(241, 196, 15);
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

