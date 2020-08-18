<template>
    <section class="section-hero section-shaped my-0">
        <div class="shape shape-style-1 gradient">
            <span class="span-150"></span>
            <span class="span-50"></span>
            <span class="span-50"></span>
            <span class="span-75"></span>
            <span class="span-100"></span>
            <span class="span-75"></span>
            <span class="span-50"></span>
            <span class="span-100"></span>
            <span class="span-50"></span>
            <span class="span-100"></span>
        </div>
        <div class="container shape-container d-flex align-items-center">
            <div class="col px-0">
                <div class="row justify-content-center align-items-center">
                    <div class="col-lg-7 text-center">
                        <!------------------------------------- 검색창 start ------------------------------------->
                        <!-- 재료 검색 -->
                        <div class="searchbar" v-if="selected=='irdnt'">
                            <div style="margin-left:31px;margin-right:30px;">
                                <select v-model="selected" style="float:left;">
                                    <option value="recipe_nm" >요리</option>
                                    <option value="irdnt">재료</option>
                                </select>
                                <button @click.prevent="searchirdnt" id="irdntbtn" style="float:right;"><i class="fa fa-search" ></i></button>
                            </div>
                            <div style="padding-top:40px;">
                                <form class="s-form">
                                    <multiselect v-model="multi" 
                                                tag-placeholder="해당 재료를 사용하는 레시피가 없습니다."
                                                placeholder="검색할 재료를 입력하세요." 
                                                :selectLabel="'Press enter'" 
                                                :options="irdnts" 
                                                :multiple="true" 
                                                :taggable="true" 
                                                :max-height="200" />
                                </form>
                            </div>
                        </div>
                        
                        <!-- 요리 검색 -->
                        <div class="searchbar" v-else>
                            <select v-model="selected">
                                <option value="recipe_nm" >요리</option>
                                <option value="irdnt">재료</option>
                            </select>
                            <form class="s-form">
                                <input class="title-input" type="text" v-model="query" placeholder="검색어를 입력해주세요." autofocus>
                                <button @click.prevent="searchtitle" ><i class="fa fa-search" ></i></button>
                            </form>
                        </div>
                        <!------------------------------------- 검색창 end ------------------------------------->

                        <img src="img/blocook/todaysrecipe.png" style="width: 300px;margin-top: 20px;" class="img-fluid">
                        <p class="lead text-white mt-2 mb-1"></p>

                        <!------------------------------------- Carousel ------------------------------------->
                        <div id="carousel">
                            <carousel :loop="true" :autoplay="true" :navigationEnabled="true" :centerMode="true" >
                                <slide>
                                    <div class="card maincard" id="recipecard">
                                        <img class="card-img-top" :src="recipes[0].imgUrl" alt="Card image cap" style="padding: 0.2rem;width: 100%;height: 43%;">
                                        <div class="card-body maincardbody" style="padding: 0.5rem;">
                                            <h5 class="card-title" style="margin-bottom: 0.5rem;">{{ recipes[0].recipeNmKo }}</h5>
                                            <span style="font-size:0.8rem; margin-right:10px;"><i class="fa fa-apple" style="color:orange;font-weight:bold;"></i>&nbsp; {{recipes[0].calorie}}kcal</span>
                                            <span style="font-size:0.8rem;"><i class="fa fa-heart" style="color:red;font-weight:bold;"></i>&nbsp; {{ likeCnt[0] }}</span>
											<p class="card-text">{{ recipes[0].sumry }}</p>
                                            <router-link :to="'recipe?Id=' +recipes[0].recipeId" class="btn btn-primary" style="width:100%;">레시피 보기&nbsp;<i class="fa fa-book"></i></router-link>
                                        </div>
                                    </div>
                                </slide>
                                <slide>
                                    <div class="card maincard" id="recipecard" style="float:right;">
                                        <img class="card-img-top" :src="recipes[1].imgUrl" alt="Card image cap" style="padding: 0.2rem;width: 100%;height: 43%;">
                                        <div class="card-body maincardbody" style="padding: 0.5rem;">
                                            <h5 class="card-title" style="margin-bottom: 0.5rem;">{{ recipes[1].recipeNmKo }}</h5>
                                            <span style="font-size:0.8rem; margin-right:10px;"><i class="fa fa-apple" style="color:orange;font-weight:bold;"></i>&nbsp; {{recipes[1].calorie}}kcal</span>
											<span style="font-size:0.8rem;"><i class="fa fa-heart" style="color:red;font-weight:bold;"></i>&nbsp; {{ likeCnt[1] }}</span>
											<p class="card-text">{{ recipes[1].sumry }}</p>
                                            <router-link :to="'recipe?Id=' +recipes[1].recipeId" class="btn btn-primary" style="width:100%;">레시피 보기&nbsp;<i class="fa fa-book"></i></router-link>
                                        </div>
                                    </div>
                                </slide>
                                <slide>
                                    <div class="card maincard" id="recipecard">
                                        <img class="card-img-top" :src="recipes[2].imgUrl" alt="Card image cap" style="padding: 0.2rem;width: 100%;height: 43%;">
                                        <div class="card-body maincardbody" style="padding: 0.5rem;">
                                            <h5 class="card-title" style="margin-bottom: 0.5rem;">{{ recipes[2].recipeNmKo }}</h5>
                                            <span style="font-size:0.8rem; margin-right:10px;"><i class="fa fa-apple" style="color:orange;font-weight:bold;"></i>&nbsp; {{recipes[2].calorie}}kcal</span>
                                            <span style="font-size:0.8rem;"><i class="fa fa-heart" style="color:red;font-weight:bold;"></i>&nbsp; {{ likeCnt[2] }}</span>
											<p class="card-text">{{ recipes[2].sumry }}</p>
                                            <router-link :to="'recipe?Id=' +recipes[2].recipeId" class="btn btn-primary" style="width:100%;">레시피 보기&nbsp;<i class="fa fa-book"></i></router-link>
                                        </div>
                                    </div>
                                </slide>
                                <slide>
                                    <div class="card maincard" id="recipecard">
                                        <img class="card-img-top" :src="recipes[3].imgUrl" alt="Card image cap" style="padding: 0.2rem;width: 100%;height: 43%;">
                                        <div class="card-body maincardbody" style="padding: 0.5rem;">
                                            <h5 class="card-title" style="margin-bottom: 0.5rem;">{{ recipes[3].recipeNmKo }}</h5>
                                            <span style="font-size:0.8rem; margin-right:10px;"><i class="fa fa-apple" style="color:orange;font-weight:bold;"></i>&nbsp; {{recipes[3].calorie}}kcal</span>
                                            <span style="font-size:0.8rem;"><i class="fa fa-heart" style="color:red;font-weight:bold;"></i>&nbsp; {{ likeCnt[3] }}</span>
											<p class="card-text">{{ recipes[3].sumry }}</p>
                                            <router-link :to="'recipe?Id=' +recipes[3].recipeId" class="btn btn-primary" style="width:100%;">레시피 보기&nbsp;<i class="fa fa-book"></i></router-link>
                                        </div>
                                    </div>
                                </slide>
                                <slide>
                                    <div class="card maincard" id="recipecard">
                                        <img class="card-img-top" :src="recipes[4].imgUrl" alt="Card image cap" style="padding: 0.2rem;width: 100%;height: 43%;">
                                        <div class="card-body maincardbody" style="padding: 0.5rem;">
                                            <h5 class="card-title" style="margin-bottom: 0.5rem;">{{ recipes[4].recipeNmKo }}</h5>
                                            <span style="font-size:0.8rem; margin-right:10px;"><i class="fa fa-apple" style="color:orange;font-weight:bold;"></i>&nbsp; {{recipes[4].calorie}}kcal</span>
                                            <span style="font-size:0.8rem;"><i class="fa fa-heart" style="color:red;font-weight:bold;"></i>&nbsp; {{ likeCnt[4] }}</span>
											<p class="card-text">{{ recipes[4].sumry }}</p>
                                            <router-link :to="'recipe?Id=' +recipes[4].recipeId" class="btn btn-primary" style="width:100%;">레시피 보기&nbsp;<i class="fa fa-book"></i></router-link>
                                        </div>
                                    </div>
                                </slide>
                                <slide>
                                    <div class="card maincard" id="recipecard">
                                        <img class="card-img-top" :src="recipes[5].imgUrl" alt="Card image cap" style="padding: 0.2rem;width: 100%;height: 43%;">
                                        <div class="card-body maincardbody" style="padding: 0.5rem;">
                                            <h5 class="card-title" style="margin-bottom: 0.5rem;">{{ recipes[5].recipeNmKo }}</h5>
                                            <span style="font-size:0.8rem; margin-right:10px;"><i class="fa fa-apple" style="color:orange;font-weight:bold;"></i>&nbsp; {{recipes[5].calorie}}kcal</span>
                                            <span style="font-size:0.8rem;"><i class="fa fa-heart" style="color:red;font-weight:bold;"></i>&nbsp; {{ likeCnt[5] }}</span>
											<p class="card-text">{{ recipes[5].sumry }}</p>
                                            <router-link :to="'recipe?Id=' +recipes[5].recipeId" class="btn btn-primary" style="width:100%;">레시피 보기&nbsp;<i class="fa fa-book"></i></router-link>
                                        </div>
                                    </div>
                                </slide>
                            </carousel>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
import http from "@/util/http-common.js";
import Multiselect from 'vue-multiselect'

export default {
    components: {
        Multiselect
    },
    data() {
      return {
        query: '',
        selected:"recipe_nm",
        recipes: [],
        irdnts: [],
        slide: 0,
        sliding: null,
        multi: [],
        likeCnt: [],
      }
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

        http
        .get('recipes/search/irdnts/소금')
        .then(({ data }) => {
        this.recipes = data;
        for (var i=0; i<6; i++ ) {
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
            alert('검색 결과 요청에 실패하였습니다.');
            console.log(error);
        })  
    },
    methods: {
        searchtitle() {
            if (this.query== "") alert("검색어를 입력해주세요.");
            else this.$router.push("/search?title=" + this.query );
        },
        searchirdnt() {
            this.$router.push("/search?irdnt=" + this.multi );
        },
        onSlideStart(slide) {
            this.sliding = true;
        },
        onSlideEnd(slide) {
            this.sliding = false;
        },
        onSubmit() {
        }
    }
};
</script>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Poor+Story&display=swap');
.gradient {
    background: linear-gradient(to bottom, rgb(205, 100, 100) 10%, #8f6ed5 70%);
}
.multiselect {
	border: 4px solid rgb(255, 204, 0);
    background-color:white;
	margin:0px;
    height: 43px;
    width: 80%;
    border-radius: 5px;
    margin: 5px auto;
}
.searchbar select {
    display:inline;
    width: 60px;
    height: 40px;
    border-radius: 5px;
    border: 4px solid rgb(255, 204, 0);
    background-color:white;
    font-weight:bolder;
}
.s-form {
    display:inline;
}
.title-input {
    border:4px solid rgb(255, 204, 0);
    height: 40px;
    width: 60%;
    border-radius: 5px;
	margin: auto 2.5px;
}
#irdntbtn {
    background-color :rgb(255, 204, 0);
    border-radius: 5px;
    border: none;
    height: 40px;
    width: 40px;
    color:white;
}
.s-form button {
    background-color :rgb(255, 204, 0);
    border-radius: 5px;
    border: none;
    height: 40px;
    color:white;
}
#carousel {
    padding: 0 20px;
    margin-bottom: 40px;
}
#recipecard {
    width:96%;
}
@media(max-width: 413px) {
    #recipecard {
        width:144px;
    }
}
@media(max-width: 320px) {
    #recipecard {
        width:120px;
    }
}
.maincard {
    font-family: 'Poor Story', cursive;
    height: 315px;
}
.maincardbody {
	height: 200px;
}
.card-title {
    text-align: center;
    font-weight: bold;
    font-size: 22px;
    background-image: url("/img/blocook/highlight-blue.png");
    background-position: center;
	background-size: 100%;
	background-repeat: no-repeat;
	overflow:hidden;
	text-overflow:ellipsis;
	white-space:nowrap;
}
.card-text {
	font-size: 16px;
    line-height: 18px;
	text-align: left;
	height: 54px;
	margin-top: 6px;
	margin-bottom: 10px;
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
    -webkit-line-clamp: 3; /* 라인수 */
    -webkit-box-orient: vertical;
    word-wrap:break-word; 
}
</style>
