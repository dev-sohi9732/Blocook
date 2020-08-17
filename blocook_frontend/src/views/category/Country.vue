<template>
    <div>
        <NavBackgroundCategory></NavBackgroundCategory>
				<CategoryArea></CategoryArea>
    </div>
</template>
<script>
import CategoryArea from "@/components/category/CategoryArea";
import NavBackgroundCategory from "@/components/main/NavBackgroundCategory";

export default {
  name: "country",
  components: {
		CategoryArea,
    NavBackgroundCategory
  }
};
</script>
