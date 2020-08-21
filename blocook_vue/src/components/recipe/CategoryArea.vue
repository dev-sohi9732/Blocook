<template>
    <div class="container">
        <select class="sorting" v-model="sorting">
            <!-- <option disabled value="">정렬방법</option> -->
            <option value="calorie">칼로리순</option>
            <option value="like">좋아요순</option>
        </select>
        <div class="row" v-if="sorting=='calorie'">
            <CategoryItem v-for="recipe in sortcalories" :key="recipe.id" :recipe="recipe" />
        </div>
        <div class="row" v-else>
            <CategoryItem v-for="recipe in recipes" :key="recipe.id" :recipe="recipe" />
        </div>
    </div>
</template>

<script>
import CategoryItem from '@/components/recipe/CategoryItem.vue'
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
    methods: {
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

<style>
.card-body {
    padding:0.4rem;
}
.sorting {
	margin: 15px 24px 5px;
	height: 28px;
	width: 100px;
	border-radius: 5px;
	border: 4px solid rgb(40, 167, 69);
	background-color:white;
	font-weight:bolder;
}
</style>