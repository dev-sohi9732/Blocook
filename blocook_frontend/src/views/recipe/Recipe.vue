<template>
    <section class="section-hero section-shaped my-0">
        <NavBackgroundRecipe></NavBackgroundRecipe>
				<Recipe></Recipe>
    </section>
</template>
<script>
import Recipe from "@/components/recipe/Recipe";
import NavBackgroundRecipe from "@/components/main/NavBackgroundRecipe";

export default {
  name: "searchrecipe",
  components: {
		Recipe,
    NavBackgroundRecipe
  }
};
</script>