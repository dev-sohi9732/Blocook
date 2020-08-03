<template>
  <div class="row">
      <CategoryItem v-for="recipe in recipes" :key="recipe.id" :recipe="recipe" />
  </div>
</template>

<script>
import CategoryItem from '@/components/recipe/CategoryItem.vue'
export default {
    name: 'CategoryArea',
    components: {
        CategoryItem,
    },
    methods: {
        onRecipeGet(recipes) {
            this.$emit('recipe-get', recipes)
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
</style>