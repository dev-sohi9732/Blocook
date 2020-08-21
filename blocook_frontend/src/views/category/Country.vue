<template>
  <section class="section-hero section-shaped my-0">
    <NavBackgroundCategory></NavBackgroundCategory>
    <CategoryArea></CategoryArea>
  </section>
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

<style scoped>
@media(max-width: 991px) {
	.section-shaped {
		padding-top: 55px;
	}
}
@media(min-width: 991px) {
	.section-shaped {
		padding-top: 90px;
	}
}
</style>
