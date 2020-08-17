<template>
    <div>
        <NavBackgroundRecipe></NavBackgroundRecipe>
				<SearchRecipeArea></SearchRecipeArea>
    </div>
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
