<template>
    <div class="container">
        <div class="text-center" style="margin-top:20px;">
            <!------------------------------------- 검색창 start ------------------------------------->
            <!-- 재료 검색 -->
            <div class="searchbar" v-if="selected=='irdnt'">
                <div style="margin-left:31px;margin-right:30px;">
                    <select v-model="selected" style="float:left;">
                        <option value="recipe_nm" >요리</option>
                        <option value="irdnt">재료</option>
                    </select>
                    <button @click.prevent="searchirdnt" id="irdntbtn" style="float:right;width:52px;"><i class="fa fa-search" ></i></button>
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
        </div>

        <hr style="margin-top: 16px; margin-bottom: 16px;">

        <select class="sorting" v-model="sorting">
            <!-- v-model="option" -->
            <!-- v-on:change="filter" -->
            <!-- <option disabled value="">정렬방법</option> -->
            <option value="see">정확도순</option>
            <option value="calorie">칼로리 낮은순</option>
            <option value="like">좋아요순</option>
        </select>

        <!------------------------------------ 검색 레시피 ------------------------------------>
        <div style="margin-top:20px;">
            <div class="row" v-if="sorting=='see'">
                <div v-for="(recipe, index) in recipes" :key="index + '_items'" class="col-6" style="padding:0px;">
                    <SearchRecipeItem :recipe="recipe" />
                </div>
            </div>
            <div class="row" v-else-if="sorting=='calorie'">
                <div v-for="(recipe, index) in sortcalories" :key="index + '_items'" class="col-6" style="padding:0px;">
                    <SearchRecipeItem :recipe="recipe" />
                </div>
            </div>
            <div class="row" v-else>
                <div v-for="(recipe, index) in sortlikes" :key="index + '_items'" class="col-6" style="padding:0px;">
                    <SearchRecipeItem :recipe="recipe" />
                </div>
            </div>
        </div>
        <a style="display:scroll;position:fixed;bottom:12px;right:12px;" href="#" title="맨 위로"><img style="width:45px; height:45px; opacity: 0.5;" src="@/assets/blocookImg/top.png"></a> 
    </div>
</template>

<script>
import http from "@/util/http-common.js";
import Multiselect from 'vue-multiselect'
import SearchRecipeItem from "@/components/recipe/SearchRecipeItem.vue"
import VueLodash from 'vue-lodash'
import lodash from 'lodash'

export default {
    name: 'SearchRecipeArea',
    components: {
        Multiselect,
        SearchRecipeItem
    },
    data() {
      return {
        recipelist:[],
      }
    },
    data() {
        return {
        selected:'recipe_nm',
        recipes: [],
        irdnts: [],
        query:'',
        multi:[],
        sorting:'see',
        }
    },
    computed: {
        sortcalories: function () {
            return _.sortBy(this.recipes, 'calorie')
        },
        sortlikes: function () {
            return _.orderBy(this.recipes, ['likeCnt'],['desc'])
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
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Poor+Story&display=swap');
h5 {
    cursor: pointer;
    font-family: 'Poor Story', cursive;
    font-size: 18px;
}
span {
    font-family: 'Poor Story', cursive;
    font-size: 18px;
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
.s-form button {
    background-color :rgb(255, 204, 0);
    border-radius: 5px;
    border: none;
    height: 40px;
    color:white;
}
img {
    cursor: pointer;
    width: 60px;
}
.card-body {
    padding:0.4rem;
}
.sorting {
    cursor: pointer;
    margin-left: 3vw;
	height: 28px;
	width: 120px;
	border-radius: 5px;
	border: 4px solid rgb(40, 167, 69);
	background-color:white;
	font-weight:bolder;
}
@media(min-width: 992px) {
    .sorting {
		margin-left: 8vw;
    }
}
@media(max-width: 991px) {
	.container {
		padding-top: 55px;
	}
}
@media(min-width: 991px) {
	.container {
		padding-top: 80px;
	}
}
</style>