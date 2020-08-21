<template>
    <section class="section-hero section-shaped my-0">
        <NavBackgroundRecipe></NavBackgroundRecipe>
				<SearchRecipeArea></SearchRecipeArea>
    </section>
</template>
<script>
import SearchRecipeArea from "@/components/recipe/SearchRecipeArea";
import NavBackgroundRecipe from "@/components/main/NavBackgroundRecipe";

export default {
  name: "searchrecipe",
  components: {
		SearchRecipeArea,
    NavBackgroundRecipe
  }
};
</script>