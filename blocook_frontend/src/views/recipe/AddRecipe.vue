<template>
    <div>
        <NavBackgroundMypage></NavBackgroundMypage>
				<AddRecipe></AddRecipe>
    </div>
</template>
<script>
import AddRecipe from "@/components/recipe/AddRecipe";
import NavBackgroundMypage from "@/components/main/NavBackgroundMypage";

export default {
  name: "addrecipe",
  components: {
		AddRecipe,
    NavBackgroundMypage
  }
};
</script>
