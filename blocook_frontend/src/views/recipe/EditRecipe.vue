<template>
    <div>
        <NavBackgroundMypage></NavBackgroundMypage>
				<EditRecipe></EditRecipe>
    </div>
</template>
<script>
import EditRecipe from "@/components/recipe/EditRecipe";
import NavBackgroundMypage from "@/components/main/NavBackgroundMypage";

export default {
  name: "editrecipe",
  components: {
		EditRecipe,
    NavBackgroundMypage
  }
};
</script>
