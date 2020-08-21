<template>
    <div>
        <NavBackgroundMypage></NavBackgroundMypage>
				<MyRecipe></MyRecipe>
    </div>
</template>
<script>
import MyRecipe from "@/components/recipe/MyRecipe";
import NavBackgroundMypage from "@/components/main/NavBackgroundMypage";

export default {
  name: "myrecipe",
  components: {
		MyRecipe,
    NavBackgroundMypage
  }
};
</script>
