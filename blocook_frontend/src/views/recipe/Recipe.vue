<template>
    <div>
        <NavBackgroundRecipe></NavBackgroundRecipe>
				<Recipe></Recipe>
    </div>
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
