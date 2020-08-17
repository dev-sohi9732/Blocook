<template>
    <div class="container">
        <div class="text-center row" style="margin-top:20px;">
            검색창
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