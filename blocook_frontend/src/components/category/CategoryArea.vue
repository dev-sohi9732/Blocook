<template>
    <div class="container">
        <div class="text-center row" style="margin-top:20px;">
            <div class="col" @click.prevent="categoryallGet">
                <img src="img/blocook/category/earth.png" style="width:45px;">
                <h5 class="display-3">전체
                    <span>All</span>
                </h5>
            </div>
            <div class="col" @click.prevent="categoryGet(3020001)">
                <img src="img/blocook/category/rice.png" style="width:45px;">
                <h5 class="display-3">한식
                    <span>Korea</span>
                </h5>
            </div>
            <div class="col" @click.prevent="categoryGet(3020003)">
                <img src="img/blocook/category/sushi.png" style="width:45px;">
                <h5 class="display-3">일식
                    <span>Japan</span>
                </h5>
            </div>
            <div class="col" @click.prevent="categoryGet(3020004)">
                <img src="img/blocook/category/dumpling.png" style="width:45px;">
                <h5 class="display-3">중식
                    <span>China</span>
                </h5>
            </div>
        </div>
        <div class="text-center row">
            <div class="col" @click.prevent="categoryGet(3020002)">
                <img src="img/blocook/category/burger.png" style="width:45px;">
                <h5 class="display-3">서양
                    <span>Western</span>
                </h5>
            </div>
            <div class="col" @click.prevent="categoryGet(3020006)">
                <img src="img/blocook/category/pizza.png" style="width:45px;">
                <h5 class="display-3">이탈리아
                    <span>Italy</span>
                </h5>
            </div>
            <div class="col" @click.prevent="categoryGet(3020005)">
                <img src="img/blocook/category/ramen.png" style="width:45px;">
                <h5 class="display-3">아시아
                    <span>Asia</span>
                </h5>
            </div>
            <div class="col" @click.prevent="categoryGet(3020009)">
                <img src="img/blocook/category/indian.png" style="width:45px;">
                <h5 class="display-3">퓨전
                    <span>Fusion</span>
                </h5>
            </div>
        </div>
        <hr style="margin-top: 16px; margin-bottom: 16px;">
        <select class="sorting" v-model="sorting">
            <!-- <option disabled value="">정렬방법</option> -->
            <option value="calorie">칼로리순</option>
            <option value="like">좋아요순</option>
        </select>
        <div class="row" v-if="sorting=='calorie'" style="margin-top:20px;">
            <CategoryItem v-for="recipe in sortcalories" :key="recipe.id" :recipe="recipe" />
        </div>
        <div class="row" v-else style="margin-top:20px;">
            <CategoryItem v-for="recipe in recipes" :key="recipe.id" :recipe="recipe" />
        </div>
        <a style="display:scroll;position:fixed;bottom:12px;right:12px;" href="#" title="맨 위로">
            <img style="width:45px; height:45px; opacity: 0.5;" src="@/assets/blocookImg/top.png">
        </a> 
    </div>
</template>

<script>
import http from "@/util/http-common.js";
import CategoryItem from '@/components/category/CategoryItem.vue'
import VueLodash from 'vue-lodash'
import lodash from 'lodash'

export default {
    name: 'CategoryArea',
    data() {
      return {
        recipelist:[],
        sorting:'calorie'
      }
    },
    components: {
        CategoryItem,
    },
    created() {
        const params = new URL(document.location).searchParams;
        if(params.get('country') == "all") {
            http
            .get("/recipes/search/all" )
            .then(response => {
                this.recipes = response.data
            })
            .catch(error => {
                console.log(error)
            })
        }
        else {
            if(params.get('country') == "korea") var code = 3020001;
            else if(params.get('country') == "japan") var code = 3020003;
            else if(params.get('country') == "china") var code = 3020004;
            else if(params.get('country') == "western") var code = 3020002;
            else if(params.get('country') == "italy") var code = 3020006;
            else if(params.get('country') == "asia") var code = 3020005;
            else if(params.get('country') == "fusion") var code = 3020009;

            http
            .get("/recipes/search/nation/" + code )
            .then(response => {
            this.recipes = response.data
            // this.$router.push('/categoryresult')
            })
            .catch(error => {
            console.log(error)
            })
        }
    },
    methods: {
        categoryallGet () {
            http
            .get("/recipes/search/all" )
            .then(response => {
                this.recipes = response.data
            })
            .catch(error => {
                console.log(error)
            })
        },
        categoryGet (nationCode) {
            http
            .get("/recipes/search/nation/" + nationCode )
            .then(response => {
            this.recipes = response.data
            // this.$router.push('/categoryresult')
            })
            .catch(error => {
            console.log(error)
            })
        },
        onRecipeGet(recipes) {
            this.$emit('recipe-get', recipes)
        }
    },
    computed: {
        sortcalories: function () {
            return _.sortBy(this.recipes, 'calorie')
        }
    },
    props: {
        recipes: {
            type: Array,
        },
    }
}
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
	width: 100px;
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
</style>